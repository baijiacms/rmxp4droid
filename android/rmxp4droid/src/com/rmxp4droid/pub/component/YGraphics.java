/**
 * 
 */
package com.rmxp4droid.pub.component;

import com.rmxp4droid.BaseConf;








/**
 * The module that carries out graphics processing.
 * 
 * 
 */
public class YGraphics {


	/**
	 * The screen's refresh rate count. Set this property to 0 at game start and the game play time (in seconds) can be
	 * calculated by dividing this value by the frame_rate property value.
	 * 
	 * @return
	 */
	public static int frame_count;

	/**
	 * Refreshes the game screen and advances time by 1 frame. This method must be called at set intervals. If this
	 * method is not called in 10 seconds or more, the program will view the script as having run out of control and
	 * will force a quit.
	 */
	public  static void update() {

//		// restart?
//		if (restart) {
//			Thread.currentThread().interrupt();
//			restart = false;
//			return;
//		}

		// render
		Ycore.getiRenderControl().er().graphicsUpdate();
		frame_count++;

//		// restart?
//		if (restart) {
//			Thread.currentThread().interrupt();
//			restart = false;
//			return;
//		}

	
	}

	/**
	 * Fixes the current screen in preparation for transitions. Screen rewrites are prohibited until the transition
	 * method is called.
	 */
	public static void freeze() {
	}

	/**
	 * Gets the width and height of the game screen.
	 * 
	 * @RGSS1 640
	 * @RGSS2 544
	 */
	public static int width() {
		return BaseConf.WIDTH;
	}

	/**
	 * Gets the width and height of the game screen.
	 * 
	 * @RGSS1 480
	 * @RGSS2 416
	 */
	public static int height() {
		return BaseConf.HEIGHT;
	}

	/**
	 * Resets the screen refresh timing. After a time-consuming process, call this method to prevent extreme frame
	 * skips.
	 */
	public static void frame_reset() {
		// TODO Auto-generated method stub
	}

	/**
	 * Carries out a transition from the screen fixed in Graphics.freeze to the current screen.
	 */
	public static void transition() {
		transition(8, null, 40);
	}

	/**
	 * Carries out a transition from the screen fixed in Graphics.freeze to the current screen.
	 * 
	 * @param duration
	 *            , is the number of frames the transition will last. When omitted, this value is set to 8.
	 */
	public static void transition(int duration) {
		transition(duration, null, 40);
	}

	/**
	 * Carries out a transition from the screen fixed in Graphics.freeze to the current screen.
	 * 
	 * @param duration
	 *            , is the number of frames the transition will last. When omitted, this value is set to 8.
	 * @param filename
	 *            , specifies the transition graphic file name. When not specified, a standard fade will be used. Also
	 *            automatically searches files included in RGSS-RTP and encrypted archives. File extensions may be
	 *            omitted.
	 */
	public static void transition(int duration, String filename) {
		transition(duration, filename, 40);
	}

	/**
	 * Carries out a transition from the screen fixed in Graphics.freeze to the current screen.
	 * 
	 * @param duration
	 *            , is the number of frames the transition will last. When omitted, this value is set to 8.
	 * @param filename
	 *            , specifies the transition graphic file name. When not specified, a standard fade will be used. Also
	 *            automatically searches files included in RGSS-RTP and encrypted archives. File extensions may be
	 *            omitted.
	 * @param vague
	 *            , sets the ambiguity of the borderline between the graphic's starting and ending points. The larger
	 *            the value, the greater the ambiguity. When omitted, this value is set to 40.
	 */
	public static void transition(int duration, String filename, int vague) {
		
	
	}
	/**
	 * Waits for the specified number of frames. Equivalent to the following.
	 * 
	 * @param duration
	 */
	public static void wait(int duration) {
		for (int i = 0; i < duration; i++) {
			update();
		}
	}

	/**
	 * In [Smooth Mode], the number of times the screen is refreshed per second. The larger the value, the more CPU
	 * power is required. Normally set at 40. When not in [Smooth Mode], the refresh rate is halved, and graphics are
	 * drawn in every other frame. Changing this property is not recommended; however, it can be set anywhere from 10 to
	 * 120. Values out of range are automatically corrected.
	 * 
	 * @return
	 */
	public static int frame_rate() {
		// TODO implement right
		return BaseConf.FPS;
	}
}
