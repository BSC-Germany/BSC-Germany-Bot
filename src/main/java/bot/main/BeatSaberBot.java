package bot.main;

import java.util.List;

import bot.api.BeatSaver;
import bot.api.ScoreSaber;
import bot.pojo.MapMetadata;
import bot.pojo.Player;
import bot.pojo.PlayerScore;

public class BeatSaberBot {

	public static void main(String[] args) {
		ScoreSaber sApi = new ScoreSaber();
		BeatSaver bApi = new BeatSaver();
		
		final long testPlayerId = 76561198095099240L;		
		Player player = sApi.retrievePlayerProfile(testPlayerId);
		System.out.println("Player name: "+player.getPlayerName());
		System.out.println("Country rank: "+player.getCountryRank());
		
		int maxTopPages = 50; //Nach Belieben erhöhen, wartet alle 80 Pages 1 Minute
		List<PlayerScore> topScores = sApi.retrieveAllTopScoresByPlayerId(testPlayerId, maxTopPages);
		System.out.println("Size: "+topScores.size());
		
		PlayerScore topScore = topScores.get(0);
		System.out.println("Top Song: "+topScore.getSongName());
		System.out.println("Accuracy: "+topScore.getAccuracyString());
		System.out.println("PP: "+topScore.getPpString());
		
		final String testSongHash = "92C7490D903F3E676069B92B7DE9E56B03A9677A";
		MapMetadata mapData = bApi.retrieveMapMetaByHash(testSongHash);
		System.out.println(mapData.getSongName());
	}
}
