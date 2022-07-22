/**
 * 
 */
package com.rmxp4droid.cxysfx.core.control;


import com.rmxp4droid.BaseView;
import com.rmxp4droid.pub.interfaces.IRenderer;


public class RenderControl {
	private  static RenderControl INSTANCEOF=new RenderControl();
	private RenderControl(){}
	
	public static RenderControl getINSTANCEOF() {
		return INSTANCEOF;
	}

	protected  IRenderer renderer;
	protected  BaseView window;

	/**
	 * 
	 */
	public  void set(IRenderer renderer,BaseView baseView) {
	
		this.renderer = renderer;
		this.window = baseView;
	}

	/**
	 * @return the renderer
	 */
	public  IRenderer er() {
		return renderer;
	}

	/**
	 * @return the window
	 */
	public  BaseView getWindow() {
		return window;
	}

	public void setWindow(BaseView window) {
		this.window = window;
	}


}
