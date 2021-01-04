package bot.api;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import bot.pojo.Player;
import bot.pojo.PlayerScore;

public class ScoreSaber {

	HttpMethods http;
	Gson gson;

	public ScoreSaber() {
		http = new HttpMethods();
		gson = new Gson();
	}

	public Player retrievePlayerProfile(long playerId) {
		String playerUrl = ApiConstants.SS_PLAYER_PRE_URL + playerId + ApiConstants.SS_PLAYER_POST_URL;
		JsonObject response = http.fetchJsonObject(playerUrl);

		if (response == null) {
			System.out.println("Could not find player with url \"" + playerUrl + "\".");
			return null;
		}
		JsonObject playerInfo = response.getAsJsonObject("playerInfo");
		Player ssPlayer = gson.fromJson(playerInfo.toString(), Player.class);
		List<Integer> historyValues = Arrays.asList(ssPlayer.getHistory().split(",")).stream()
				.map(val -> Integer.parseInt(val)).collect(Collectors.toList());
		ssPlayer.setHistoryValues(historyValues);
		return ssPlayer;
	}

	public List<PlayerScore> retrieveAllTopScoresByPlayerId(long playerId, int maxPages) {
		List<PlayerScore> topScores = new ArrayList<PlayerScore>();
		
		for (int i = 1; i < maxPages; i++) {
			List<PlayerScore> scores = retrieveTopScoresByPlayerId(playerId, i);
			if (scores.size() == 0) {
				break;
			} else {
				topScores.addAll(scores);
			}
			
			if (i % 79 == 0) {
				System.out.println("Waiting one minute...");
				try {
					TimeUnit.MINUTES.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return topScores;

	}

	public List<PlayerScore> retrieveTopScoresByPlayerId(long playerId, int page) {
		String topScoresUrl = ApiConstants.SS_PLAYER_PRE_URL + playerId + ApiConstants.SS_PLAYER_TOP_SCORES_POST_URL
				+ page;
		JsonObject response = http.fetchJsonObject(topScoresUrl);
		if (response != null) {
			JsonArray topScores = response.getAsJsonArray("scores");

			Type listType = new TypeToken<List<PlayerScore>>() {
			}.getType();
			return gson.fromJson(topScores.toString(), listType);
		}
		return new ArrayList<PlayerScore>();
	}
}
