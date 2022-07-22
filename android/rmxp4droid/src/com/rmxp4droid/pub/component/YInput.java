/**
 * 
 */
package com.rmxp4droid.pub.component;



/**
 * A module that handles input data from a gamepad or keyboard.
 * 
 * 
 */
public class YInput {

	/**
	 * Numbers corresponding to the directions down, left, right, and up.
	 */
	public static int DOWN = 2, LEFT = 4, RIGHT = 6, UP = 8;

	/**
	 * Numbers corresponding to the various controller buttons.
	 */
	public static int A = 11, B = 12, C = 13, X = 14, Y = 15, Z = 16, L = 17,
			R = 18;

	/**
	 * Numbers directly corresponding to the keyboard's SHIFT, CTRL, and ALT
	 * keys.
	 */
	public static int SHIFT = 21, CTRL = 22, ALT = 23;

	/**
	 * Numbers corresponding to the keyboard's function keys. The other function
	 * keys are reserved by the system and cannot be obtained.
	 */
	public static int F5 = 25, F6 = 26, F7 = 27, F8 = 28, F9 = 29;

	/**
	 * If the key pressed?
	 */
	private static final Boolean[] keyDown = new Boolean[F9 + 1];

	/**
	 * The Key will be used, next update time
	 */
	private static final Boolean[] keyCanSetToUsed = new Boolean[F9 + 1];

	/**
	 * If the key was pressed and now is used?
	 */
	private static final Boolean[] keyUsed = new Boolean[F9 + 1];

	/**
	 * How long wait, for repeat it again
	 */
	private static final int repeatWaitTime = 100;

	/**
	 * When was the key pressed?
	 */
	private static final long[] keyTime = new long[F9 + 1];

	/**
	 * Updates input data. As a rule, this method is called once per frame.
	 */
	public  static void update() {
		// reset all updates
		for (int i = keyCanSetToUsed.length - 1; i >= 0; i--) {
			if (keyCanSetToUsed!=null&&keyCanSetToUsed[i]!=null&&keyCanSetToUsed[i]) {
				keyCanSetToUsed[i] = false;
				keyUsed[i] = true;
			}
		}

	}

	/**
	 * Determines whether the button num is being pressed again. Unlike
	 * trigger?, takes into account the repeat input of a button being held down
	 * continuously. If the button is being pressed, returns TRUE. If not,
	 * returns FALSE.
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isRepeated(final int num) {
		// exist?
		if (keyDown[num] == null) {
			return false;
		}
	
		// return isPressed(num);
		// has?
		// System.out
		// .println(keyDown[num]
		// + " "
		// + keyUsed[num]
		// + " "
		// + keyTime[num]
		// + " "
		// + repeatWaitTime
		// + " "
		// + System.currentTimeMillis()
		// + " "
		// + (keyTime[num] + repeatWaitTime <= System
		// .currentTimeMillis()));

		if (keyDown[num]
				&& keyTime[num] + repeatWaitTime <= System.currentTimeMillis()) {
			keyTime[num] = System.currentTimeMillis();
			return true;
		}

		// TODO Implement method
		return false;// isPressed(num);
	}

	/**
	 * Determines whether the button num is currently being pressed. If the
	 * button is being pressed, returns TRUE. If not, returns FALSE.
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isPressed(final int num) {
		// exist?
		if (keyDown[num] == null) {
			return false;
		}

		return keyDown[num];
	}

	/**
	 * Determines whether the button num is being pressed again. "Pressed again"
	 * is seen as time having passed between the button being not pressed and
	 * being pressed. If the button is being pressed, returns TRUE. If not,
	 * returns FALSE.
	 * 
	 * @param num
	 * @return
	 */
	public  static boolean isTrigger(final int num) {
		// exist?
		if (keyDown[num] == null) {
			return false;
		}
	
		
			
	
	
		// has?
		if (keyDown[num] && (keyUsed[num] == null || !keyUsed[num])) {
			keyCanSetToUsed[num] = true;
			return true;
		}

		return false;
	}

	/**
	 * Checks the status of the directional buttons, translates the data into a
	 * specialized 4-direction input format, and returns the number pad
	 * equivalent (2, 4, 6, 8). If no directional buttons are being pressed (or
	 * the equivalent), returns 0.
	 * 
	 * @return
	 */
	public static int dir4() {
		if (isPressed(DOWN))
			return 2;
		if (isPressed(LEFT))
			return 4;
		if (isPressed(UP))
			return 8;
		if (isPressed(RIGHT))
			return 6;

		return 0;
	}

	/**
	 * Checks the status of the directional buttons, translates the data into a
	 * specialized 8-direction input format, and returns the number pad
	 * equivalent (1, 2, 3, 4, 6, 7, 8, 9). If no directional buttons are being
	 * pressed (or the equivalent), returns 0.
	 * 
	 * @return
	 */
	public static int dir8() {

		if (isPressed(DOWN) && isPressed(LEFT))
			return 1;
		if (isPressed(LEFT) && isPressed(UP))
			return 7;
		if (isPressed(UP) && isPressed(RIGHT))
			return 9;
		if (isPressed(RIGHT) && isPressed(DOWN))
			return 3;

		return dir4();
	}

	/**
	 * @return the keysrepeat
	 */
	public static void setKeyDown(int code) {
		keyDown[code] = true;
	
	}

	/**
	 * @return the keysrepeat
	 */
	public static void setKeyUp(int code) {
		keyDown[code] = false;
		keyUsed[code] = false;
	}
}
