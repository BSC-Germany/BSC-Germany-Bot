package bot.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import bot.pojo.MapMetadata;
import bot.pojo.SongDifficulties;

public class BeatSaver {

	HttpMethods http;
	Gson gson;

	public BeatSaver() {
		http = new HttpMethods();
		gson = new Gson();
	}

	public MapMetadata retrieveMapMetaByHash(String hash) {
		String infoUrl = ApiConstants.BS_MAP_BY_HASH_URL + hash;
		JsonObject response = http.fetchJsonObject(infoUrl);
		return getSongFromJson(response);
	}

	private MapMetadata getSongFromJson(JsonObject json) {
		try {
			String hash = json.get("hash").getAsString();
			String songName = json.get("metadata").getAsJsonObject().get("songName").getAsString();
			String songKey = json.get("key").getAsString();
			String coverURL = ApiConstants.BS_PRE_URL + json.get("coverURL").getAsString();
			JsonObject diffJson = json.get("metadata").getAsJsonObject().get("difficulties").getAsJsonObject();

			SongDifficulties difficulties = new SongDifficulties();
			difficulties.setEasy(diffJson.get("easy").getAsBoolean());
			difficulties.setNormal(diffJson.get("normal").getAsBoolean());
			difficulties.setHard(diffJson.get("hard").getAsBoolean());
			difficulties.setExpert(diffJson.get("expert").getAsBoolean());
			difficulties.setExpertPlus(diffJson.get("expertPlus").getAsBoolean());

			MapMetadata song = new MapMetadata();
			song.setHash(hash);
			song.setSongName(songName);
			song.setSongKey(songKey);
			song.setDifficulties(difficulties);
			song.setCoverURL(coverURL);
			return song;
		} catch (NullPointerException e) {
			return null;
		}
	}
}
