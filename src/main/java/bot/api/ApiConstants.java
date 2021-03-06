package bot.api;

public class ApiConstants {

	// ScoreSaber
	public static String USER_PRE_URL = "https://scoresaber.com/u/";

	public static String SS_PRE_URL = "https://new.scoresaber.com";
	public static String SS_PLAYER_PRE_URL = SS_PRE_URL + "/api/player/";
	public static String SS_PLAYER_POST_URL = "/full";
	public static String SS_PLAYER_TOP_SCORES_POST_URL = "/scores/top/";
	public static String SS_PLAYER_RECENT_SCORES_POST_URL = "/scores/recent";
	public static String SS_LEADERBOARD_PRE_URL = "https://scoresaber.com/leaderboard/";
	public static String SS_AVATAR_PRE_URL = "https://new.scoresaber.com/api/static/avatars/";

	// Beat Saver
	public static String BS_PRE_URL = "https://beatsaver.com";
	public static String BS_API_PRE_URL = BS_PRE_URL + "/api/";

	public static String BS_DOWNLOAD_URL = BS_API_PRE_URL + "download/key/";
	public static String BS_MAP_DETAILS_URL = BS_API_PRE_URL + "maps/detail/";
	public static String BS_MAP_BY_HASH_URL = BS_API_PRE_URL + "maps/by-hash/";

	public static String BS_DEFAULT_MAP_URL = BS_PRE_URL + "/beatmap/";
}
