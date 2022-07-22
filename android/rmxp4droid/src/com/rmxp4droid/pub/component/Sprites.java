/**
 * 
 */
package com.rmxp4droid.pub.component;

import java.util.Observable;



import com.rmxp4droid.pub.interfaces.ISprite;





public class Sprites implements java.util.Observer{



	/**
	 * Intern Data
	 */
	protected ISprite sprite;

	/**
	 * The sprite's X/Y-coordinate.
	 */
	public int x, y;

	/**
	 * The box (Rect) taken from a bitmap.
	 */
	public Rect src_rect;

	/**
	 * The sprite's angle of rotation. Specifies up to 360 degrees of
	 * counterclockwise rotation. However, drawing a rotated sprite is
	 * time-consuming, so avoid overuse.
	 */
	public int angle;

	/**
	 * Flag denoting the sprite has been flipped horizontally. If TRUE, the
	 * sprite will be drawn flipped.
	 */
	public boolean mirror;

	/**
	 * The Bush depth for that sprite. This is a pixel value denoting how much
	 * of the sprite's lower portion will be displayed as semitransparent. A
	 * simple way to convey a sense of a character's feet being obscured by
	 * foliage and the like.
	 */
	public int bush_depth;

	

	/**
	 * Refers to the bitmap (Bitmap) used in the plane.
	 */
	protected Bitmap bitmap;

	/**
	 * Gets the Viewport (Viewport) specified when the tilemap was created.
	 */
	protected Viewport viewport;

	/**
	 * The X/Y-coordinate of the sprite's starting point.
	 */
	public int ox, oy;

	/**
	 * Whether the plane can be seen. If TRUE, the plane is visible.
	 */
	public boolean visible;

	/**
	 * The sprite's X/Y-axis zoom level. 1.0 denotes actual pixel size.
	 */
	public float zoom_x, zoom_y;

	/**
	 * The sprite's opacity (0-255). Values out of range are automatically
	 * corrected.
	 */
	protected int opacity;

	/**
	 * Height value
	 */
	protected int z;

	/**
	 * The sprite's blending mode (0: normal, 1: addition, 2: subtraction).
	 */
	public int blend_type;

	/**
	 * The color (Color) to be blended with the sprite. Alpha values are used in
	 * the blending ratio.
	 * 
	 * Handled separately from the color blended into a flash effect. However,
	 * the color with the higher alpha value when displayed will have the higher
	 * priority when blended.
	 */
	public Color color=new Color(255, 255, 255);

	/** The sprite's color tone (Tone). */
	protected Tone tone;

	/**
	 * Creates a new Sprite object. Specifies a Viewport (Viewport) when
	 * necessary.
	 * 
	 */
	public Sprites() {
		this(null);
	}

	/**
	 * Creates a new Sprite object. Specifies a Viewport (Viewport) when
	 * necessary.
	 * 
	 * @param viewport
	 */
	public Sprites(Viewport viewport) {
		setViewport(viewport);
		opacity = 255;
		// register sprite
		Ycore.getiRenderControl().getWindow().registerSprite(this);
		
		visible = true;
		src_rect = new Rect(0, 0, 0, 0);
		sprite = Ycore.getiRenderControl().er().createSprite(this);
	}

	/**
	 * Refreshes the sprite flash. As a rule, this method is called once per
	 * frame.
	 * 
	 * It is not necessary to call this method if no flash effect is needed.
	 */
	public void update() {

	}

	/**
	 * Refers to the bitmap (Bitmap) used in the plane.
	 * 
	 * @param bitmap
	 *            the bitmap to set
	 */
	
	public void setBitmap(Bitmap bitmap) {
	
		this.bitmap = bitmap;

		int w = (bitmap == null) ? 0 : bitmap.width(), h = (bitmap == null) ? 0
				: bitmap.height();



		// set
		if (src_rect == null)
			src_rect = new Rect(0, 0, w, h);
		else
			src_rect.set(0, 0, w, h);
	}
	

	/**
	 * Gets the width of the sprite. Equivalent to src_rect.width.
	 * 
	 * @return
	 */
	public int width() {
		return src_rect == null ? 0 : src_rect.width;
	}

	/**
	 * Gets the height of the sprite. Equivalent to src_rect.height.
	 * 
	 * @return
	 */
	public int height() {
		return src_rect == null ? 0 : src_rect.height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sprite [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", src_rect=");
		builder.append(src_rect);
		builder.append(", angle=");
		builder.append(angle);
		builder.append(", mirror=");
		builder.append(mirror);
		builder.append(", bush_depth=");
		builder.append(bush_depth);
		builder.append(", viewport=");
		builder.append(viewport);
		builder.append(", ox=");
		builder.append(ox);
		builder.append(", oy=");
		builder.append(oy);
		builder.append(", visible=");
		builder.append(visible);
		builder.append(", zoom_x=");
		builder.append(zoom_x);
		builder.append(", zoom_y=");
		builder.append(zoom_y);
		builder.append(", opacity=");
		builder.append(opacity);
		builder.append(", z=");
		builder.append(z);
		builder.append(", blend_type=");
		builder.append(blend_type);
		builder.append(", color=");
		builder.append(color);
		builder.append(", tone=");
		builder.append(tone);
		builder.append("]");
		return builder.toString();
	}



	/**
	 * @return the sprite
	 */
	public ISprite getSprite() {
		return sprite;
	}

	/**
	 * @param opacity
	 *            the opacity to set
	 */
	public void setOpacity(int opacity) {
		this.opacity = opacity;
		
	}

	/**
	 * @param tone
	 *            the tone to set
	 */
	public void setTone(Tone tone) {
		this.tone = tone;
	
	}
	

	
	/**
	 * The viewport's Z-coordinate. The larger this value, the closer to the
	 * player the viewport will be displayed. If multiple objects share the same
	 * Z-coordinate, the more recently created object will be displayed closest
	 * to the player.
	 * 
	 * @return
	 */
	public int z() {
		return z;
	}
	public int getRealZ() {
		return z() + (viewport() == null ? 0 :viewport().z());
	}

	/**
	 * The viewport's Z-coordinate. The larger this value, the closer to the
	 * player the viewport will be displayed. If multiple objects share the same
	 * Z-coordinate, the more recently created object will be displayed closest
	 * to the player.
	 * 
	 * @return
	 */
	public void setZ(int z) {
		if(this.z!=z||z==0)
		{
		this.z = z;
		// change position
		Ycore.getiRenderControl().getWindow().unregisterSprite(this);
		Ycore.getiRenderControl().getWindow().registerSprite(this);
		}
	}

	/**
	 * @return the bitmap
	 */
	public Bitmap bitmap() {
		return bitmap;
	}

	/**
	 * If the viewport change his z, he will call update
	 */

	public void update(Observable arg0, Object arg1) {
		// update u
		setZ(z);
	}

	/**
	 * Gets the Viewport (Viewport) specified when the tilemap was created.
	 * 
	 * @return the viewport
	 */
	public Viewport viewport() {
		return viewport;
	}

	/**
	 * Gets the Viewport (Viewport) specified when the tilemap was created.
	 * 
	 * @param viewport
	 *            the viewport to set
	 */
	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
//		if (viewport != null)
//			viewport.addObserver(this);
	}

	

	/**
	 * Frees the bitmap. If the bitmap has already been freed, does nothing.
	 */
	public void dispose() {
		if (!isDisposed()) {

			// delete image?
			if (bitmap != null ) {
				bitmap.dispose();
				bitmap = null;
			}

			// unregister sprite
			Ycore.getiRenderControl().getWindow().unregisterSprite(this);
		}
	}

	/**
	 * Returns TRUE if the bitmap has been freed.
	 * 
	 * @return
	 */
	public boolean isDisposed() {
		return !Ycore.getiRenderControl().getWindow().isRegisterSprited(this);
	}

	/**
	 * @return the opacity
	 */
	public int opacity() {
		return opacity;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * @return the tone
	 */
	public Tone tone() {
		return tone;
	}

	public int getZ() {
		return z;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getOx() {
		return ox;
	}

	public void setOx(int ox) {
		this.ox = ox;
	}

	public int getOy() {
		return oy;
	}
	public Rect getSrc_rect() {
		return src_rect;
	}
	
	public void setSrc_rect(Rect srcRect) {
		src_rect = srcRect;
	}

	public void setOy(int oy) {
		this.oy = oy;
	}

	public float getZoom_x() {
		return zoom_x;
	}

	public void setZoom_x(float zoomX) {
		zoom_x = zoomX;
	}

	public float getZoom_y() {
		return zoom_y;
	}

	public void setZoom_y(float zoomY) {
		zoom_y = zoomY;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public boolean isMirror() {
		return mirror;
	}

	public void setMirror(boolean mirror) {
		this.mirror = mirror;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getBush_depth() {
		return bush_depth;
	}

	public void setBush_depth(int bushDepth) {
		bush_depth = bushDepth;
	}

	public int getBlend_type() {
		return blend_type;
	}

	public void setBlend_type(int blendType) {
		blend_type = blendType;
	}
	
}
