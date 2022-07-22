/**
 * 
 */
package com.rmxp4droid.pub.component;






/**
 * The viewport class. Used when displaying sprites in one portion of the
 * screen, with no overflow into other regions.
 * 
 * extends Observable
 */
public class Viewport {
//	private static IRenderControl iRenderControl;
//	
//	public static void setiRenderControl(IRenderControl iRenderControl) {
//		Viewport.iRenderControl = iRenderControl;
//	}
	/**
	 * The box (Rect) defining the viewport.
	 */
	public Rect rect;

	/**
	 * The X/Y-coordinate of the viewport's starting point. Change this value to
	 * shake the screen, etc.
	 */
	public int ox, oy;

	/**
	 * The viewport's Z-coordinate. The larger this value, the closer to the
	 * player the viewport will be displayed. If multiple objects share the same
	 * Z-coordinate, the more recently created object will be displayed closest
	 * to the player.
	 */
	protected int z;

	/**
	 * The viewport's visibility. If TRUE, the viewport is visible.
	 */
	public boolean visible;

	/**
	 * The color (Color) to be blended with the viewport. Alpha values are used
	 * in the blending ratio. Handled separately from the color blended into a
	 * flash effect.
	 */
	public Color color;

	/**
	 * The viewport's color tone (Tone).
	 */
	public Tone tone;

	/**
	 * Creates a new viewport object.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Viewport(int x, int y, int width, int height) {
		this(new Rect(x, y, width, height));
	}

	/**
	 * Creates a viewport object.
	 * 
	 * @param rect
	 */
	public Viewport(Rect rect) {
		super();
		this.rect = rect;
		visible = true;
	}

	/**
	 * Frees the bitmap. If the bitmap has already been freed, does nothing.
	 */
	public void dispose() {
		this.color=null;
		// TODO Auto-generated method stub
	}

	/**
	 * Begins flashing the viewport. duration specifies the number of frames the
	 * flash will last. If color is set to nil, the viewport will disappear
	 * while flashing.
	 * 
	 * @param color
	 * @param duration
	 */
	public void flash(Color color, int duration) {
		// TODO Auto-generated method stub
	}

	/**
	 * Refreshes the viewport flash. As a rule, this method is called once per
	 * frame. It is not necessary to call this method if no flash effect is
	 * needed.
	 */
	public void update() {
//		if(this.color!=null&&this.color.alpha>0)
//		{//图像闪烁
//			iRenderControl.er().flashScreen(this.color);
//		}
	}

	/**
	 * Returns TRUE if the bitmap has been freed.
	 * 
	 * @return
	 */
	public boolean isDisposed() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Viewport [rect=" + rect + ", ox=" + ox + ", oy=" + oy + ", z="
				+ z + ", visible=" + visible + ", color=" + color + ", tone="
				+ tone + "]";
	}

	/**
	 * The viewport's Z-coordinate. The larger this value, the closer to the
	 * player the viewport will be displayed. If multiple objects share the same
	 * Z-coordinate, the more recently created object will be displayed closest
	 * to the player.
	 * 
	 * @return the z
	 */
	public int z() {
		return z;
	}

	/**
	 * The viewport's Z-coordinate. The larger this value, the closer to the
	 * player the viewport will be displayed. If multiple objects share the same
	 * Z-coordinate, the more recently created object will be displayed closest
	 * to the player.
	 * 
	 * @param z
	 *            the z to set
	 */
	public void setZ(int z) {
		if(this.z!=z||z==0)
		{
		Ycore.getiRenderControl().getWindow().setRefreshSort(true);
		this.z = z;
//		setChanged();
//		notifyObservers();
		}
	}

}
