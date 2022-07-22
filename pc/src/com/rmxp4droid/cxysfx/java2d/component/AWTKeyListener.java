package com.rmxp4droid.cxysfx.java2d.component;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.rmxp4droid.pub.component.YInput;


public class AWTKeyListener implements KeyListener {
	public static int historykey=0;
	@Override
	public  synchronized void keyPressed(KeyEvent e) {

//		// infomenu?
//		if (e.getKeyCode() == KeyEvent.VK_F1) {
//		
//			return;
//		}
//
//		// show fps?
//		if (e.getKeyCode() == KeyEvent.VK_F2) {
//			JFrame j = ((Java2DWindow) Render.getWindow()).getWindow();
//			if (j.getTitle().contains(" - ")) {
//				j.setTitle(Ycore.game.getName());
//			} else {
//				j.setTitle(Ycore.game.getName() + " - " + Render.getWindow().getFPS() + " FPS");
//			}
//			return;
//		}

		// toogle fullscreen?
//		if (e.getKeyCode() == KeyEvent.VK_F4 || e.isAltDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
//			Render.getWindow().setFullscreen(!Render.getWindow().isFullscreen());
//			return;
//		}

//		// restart?
//		if (e.getKeyCode() == KeyEvent.VK_F12) {
//			Render.restart();
//			return;
//		}
		if(YInput.C==keyTranslate(e.getKeyCode()))
		{
			if(historykey!=keyTranslate(e.getKeyCode())+1&&historykey!=keyTranslate(e.getKeyCode()))
			{
			historykey=keyTranslate(e.getKeyCode());
	
		
				set(e, true);
			}
		}else
		{
			set(e, true);
			
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		historykey=0;
		set(e, false);
	
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	/**
	 * Helpermethod to set
	 * 
	 * @param e
	 * @param type
	 */
	protected void set(KeyEvent e, boolean type) {
		// key code
		int key = keyTranslate(e.getKeyCode());
	
		if (key != -1) {
			if (type) {
				YInput.setKeyDown(key);
			} else {
				YInput.setKeyUp(key);
			}
		}
	}

	/**
	 * Translate the java to game keys
	 * 
	 * @param id
	 * @return
	 */
	protected int keyTranslate(int id) {
		switch (id) {
		case KeyEvent.VK_NUMPAD2:
		case KeyEvent.VK_DOWN:
			return YInput.DOWN;
		case KeyEvent.VK_NUMPAD8:
		case KeyEvent.VK_UP:
			return YInput.UP;
		case KeyEvent.VK_NUMPAD4:
		case KeyEvent.VK_LEFT:
			return YInput.LEFT;
		case KeyEvent.VK_NUMPAD6:
		case KeyEvent.VK_RIGHT:
			return YInput.RIGHT;
		case KeyEvent.VK_Z:
			return YInput.A;
		case KeyEvent.VK_X:
		case KeyEvent.VK_NUMPAD0:
		case KeyEvent.VK_ESCAPE:
			return YInput.B;
		case KeyEvent.VK_C:
		case KeyEvent.VK_SPACE:
		case KeyEvent.VK_ENTER:
			return YInput.C;
		case KeyEvent.VK_CONTROL:
			return YInput.CTRL;
		case KeyEvent.VK_ALT:
			return YInput.ALT;
		case KeyEvent.VK_SHIFT:
			return YInput.SHIFT;
			// case KeyEvent.VK_:
			// JInput.getKeysrepeat()[JInput.] = type; break;
		case KeyEvent.VK_F5:
			return YInput.F5;
		case KeyEvent.VK_F6:
			return YInput.F6;
		case KeyEvent.VK_F7:
			return YInput.F7;
		case KeyEvent.VK_F8:
			return YInput.F8;
		case KeyEvent.VK_F9:
			return YInput.F9;
		case KeyEvent.VK_A:
			return YInput.X;
		case KeyEvent.VK_S:
			return YInput.Y;
		case KeyEvent.VK_D:
			return YInput.Z;
		case KeyEvent.VK_PAGE_UP:
		case KeyEvent.VK_Q:
			return YInput.L;
		case KeyEvent.VK_PAGE_DOWN:
		case KeyEvent.VK_W:
			return YInput.R;
		}

		return -1;
	}
}
