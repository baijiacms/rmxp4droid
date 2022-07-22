/**
 * 
 */
package com.rmxp4droid.pub.component;










public class Audio {


	/**
	 * Starts BGM playback. Sets the file name, volume, and pitch in turn. Also
	 * automatically searches files included in RGSS-RTP. File extensions may be
	 * omitted.
	 * 
	 * @param filename
	 */
	public static void bgm_play(String filename) {
		bgm_play(filename, 100, 100);
	}

	/**
	 * Starts BGM playback. Sets the file name, volume, and pitch in turn. Also
	 * automatically searches files included in RGSS-RTP. File extensions may be
	 * omitted.
	 * 
	 * @param filename
	 * @param volume
	 */
	public static void bgm_play(String filename, int volume) {
		bgm_play(filename, volume, 100);
	}

	/**
	 * Starts BGM playback. Sets the file name, volume, and pitch in turn. Also
	 * automatically searches files included in RGSS-RTP. File extensions may be
	 * omitted.
	 * 
	 * @param filename
	 * @param volume
	 * @param pitch
	 */
	public static void bgm_play(String filename, int volume, int pitch) {
//		bgm_stop(); 
		Ycore.getiMusicControl().getMusicBgmPlayer().play(filename, volume);
	
	}
 


	/**
	 * Stops BGM playback.
	 */
	public static void bgm_stop() {
		Ycore.getiMusicControl().getMusicBgmPlayer().stop();
	}

	/**
	 * Starts BGM fadeout. time is the length of the fadeout in milliseconds.
	 * 
	 * @param time
	 */
	public static void bgm_fade(int time) {
	
	}

	/**
	 * Starts BGS playback. Sets the file name, volume, and pitch in turn. Also
	 * automatically searches files included in RGSS-RTP. File extensions may be
	 * omitted.
	 * 
	 * @param filename
	 */
	public static void bgs_play(String filename) {
		bgs_play(filename, 100, 100);
	}

	/**
	 * Starts BGS playback. Sets the file name, volume, and pitch in turn. Also
	 * automatically searches files included in RGSS-RTP. File extensions may be
	 * omitted.
	 * 
	 * @param filename
	 * @param volume
	 */
	public static void bgs_play(String filename, int volume) {
		bgs_play(filename, volume, 100);
	}

	/**
	 * Starts BGS playback. Sets the file name, volume, and pitch in turn. Also
	 * automatically searches files included in RGSS-RTP. File extensions may be
	 * omitted.
	 * 
	 * @param filename
	 * @param volume
	 * @param pitch
	 */
	public static void bgs_play(String filename, int volume, int pitch) {
//		bgs_stop();
		Ycore.getiMusicControl().getMusicBgsPlayer().play(filename, volume);
	}

	/**
	 * Stops BGS playback.
	 */
	public static void bgs_stop() {
		Ycore.getiMusicControl().getMusicBgsPlayer().stop();
	}

	/**
	 * Starts BGS fadeout. time is the length of the fadeout in milliseconds.
	 * 
	 * @param time
	 */
	public static void bgs_fade(int time) {
	
	}

	/**
	 * Starts ME playback. Sets the file name, volume, and pitch in turn. Also
	 * automatically searches files included in RGSS-RTP. File extensions may be
	 * omitted.
	 * 
	 * @param filename
	 */
	public static void me_play(String filename) {
		me_play(filename, 100, 100);
	}

	/**
	 * Starts ME playback. Sets the file name, volume, and pitch in turn. Also
	 * automatically searches files included in RGSS-RTP. File extensions may be
	 * omitted.
	 * 
	 * @param filename
	 * @param volume
	 */
	public static void me_play(String filename, int volume) {
		me_play(filename, volume, 100);
	}

	/**
	 * Starts ME playback. Sets the file name, volume, and pitch in turn. Also
	 * automatically searches files included in RGSS-RTP. File extensions may be
	 * omitted.
	 * 
	 * @param filename
	 * @param volume
	 * @param pitch
	 */
	public static void me_play(String filename, int volume, int pitch) {
//		me_stop();
		Ycore.getiMusicControl().getMusicMePlayer().play(filename, volume);
	}

	/**
	 * Stops ME playback.
	 */
	public static void me_stop() {
		Ycore.getiMusicControl().getMusicMePlayer().stop();
	}

	/**
	 * Starts ME fadeout. time is the length of the fadeout in milliseconds.
	 * 
	 * @param time
	 */
	public static void me_fade(int time) {
	
	}

	/**
	 * Starts SE playback. Sets the file name, volume, and pitch in turn. Also
	 * automatically searches files included in RGSS-RTP. File extensions may be
	 * omitted.
	 * 
	 * @param filename
	 */
	public static void se_play(String filename) {
		se_play(filename, 100, 100);
	}

	/**
	 * Starts SE playback. Sets the file name, volume, and pitch in turn. Also
	 * automatically searches files included in RGSS-RTP. File extensions may be
	 * omitted.
	 * 
	 * @param filename
	 * @param volume
	 */
	public static void se_play(String filename, int volume) {
		se_play(filename, volume, 100);
	}

	/**
	 * Starts SE playback. Sets the file nase, voluse, and pitch in turn. Also
	 * automatically searches files included in RGSS-RTP. File extensions may be
	 * omitted.
	 * 
	 * @param filename
	 * @param volume
	 * @param pitch
	 */
	public static void se_play(String filename, int volume, int pitch) {
		Ycore.getiMusicControl().getMusicSePlayer().play(filename, volume);
	}

	/**
	 * Stops SE playback.
	 */
	public static void se_stop() {
		Ycore.getiMusicControl().getMusicSePlayer().stop();
	}
}
