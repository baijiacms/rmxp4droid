/**
 * 
 */
package com.rmxp4droid.pub.component;





import com.rmxp4droid.pub.interfaces.IBitmap;







public class Bitmap implements java.io.Serializable,Cloneable {

	private static final long serialVersionUID = 1L;
	/**
	 * The font (Font) used to draw a string with the draw_text method.
	 */
	public Font font = Ycore.defaultFont;

	/**
	 * Slick Image Data
	 */
	protected IBitmap data;

	/**
	 * help flag to show, that the image is loaded
	 */
	protected Boolean isLoaded;

	/**
	 * Creates a bitmap object with the specified size.
	 * 
	 * @param width
	 * @param height
	 */
	public Bitmap(final int width, final int height) {
		data = Ycore.getiRenderControl().er().createBitmap(width, height);

	
	}

	/**
	 * Creates a bitmap object with the specified size.
	 * 
	 * @param filename
	 */
	public Bitmap(final String filename) {

		data = Ycore.getiRenderControl().er().createBitmap(filename);
		
	}

	

	/**
	 * Gets the bitmap width.
	 * 
	 * @return
	 */
	public int width() {
		return isDisposed() ? 0 : data.width();
	}

	/**
	 * Gets the bitmap height.
	 * 
	 * @return
	 */
	public int height() {
		return isDisposed() ? 0 : data.height();
	}

	/**
	 * Gets the bitmap rectangle (Rect).
	 * 
	 * @return
	 */
	private Rect rect1=new Rect(0, 0, 0, 0);
	public Rect rect() {
		if(isDisposed())
		{
			rect1.set(0, 0, 0, 0);
		}else
		{
			rect1.set(0, 0, width(), height());
		}
//		return isDisposed() ? new Rect(0, 0, 0, 0) : new Rect(0, 0, width(), height());
		return rect1;
	}

	/**
	 * Performs a block transfer from the src_bitmap box src_rect (Rect) to the
	 * specified bitmap coordinates (x, y).
	 * 
	 * @param x
	 * @param y
	 * @param src_bitmap
	 * @param src_rect
	 */
	public void blt(final int x, final int y, final Bitmap src_bitmap, final Rect src_rect) {
		blt(x, y, src_bitmap, src_rect, 255);
	}

	/**
	 * Clears the entire bitmap.
	 */
	public void clear() {
		fill_rect(0, 0, width(), height(),Ycore.YCORE_BLACK);
	}

	/**
	 * Clears this bitmap box or (x, y, width, height) or rect (Rect).
	 */
	public void clear_rect(Rect rect) {
		fill_rect(rect.x, rect.y, rect.x + rect.width, rect.y + rect.height, Ycore.YCORE_BLACK);
	}

	/**
	 * Clears this bitmap box or (x, y, width, height) or rect (Rect).
	 */
	public void clear_rect(int x, final int y, final int width, final int height) {
		fill_rect(x, y, x + width, y + height, Ycore.YCORE_BLACK);
	}

	/**
	 * Performs a block transfer from the src_bitmap box src_rect (Rect) to the
	 * specified bitmap coordinates (x, y).
	 * 
	 * @param x
	 * @param y
	 * @param src_bitmap
	 * @param src_rect
	 * @param opacity
	 *            can be set from 0 to 255.
	 */
	private Rect rect3= new Rect(0,0,0,0);
	public void blt(final int x, final int y, final Bitmap src_bitmap, final Rect src_rect, final int opacity) {
		rect3.set(x, y, src_rect.width, src_rect.height);
		stretch_blt(rect3, src_bitmap, src_rect, opacity);
	}

	/**
	 * Performs a block transfer from the src_bitmap box src_rect (Rect) to the
	 * specified bitmap box dest_rect (Rect).
	 * 
	 * @param dest_rect
	 * @param src_bitmap
	 * @param src_rect
	 * @param opacity
	 *            can be set from 0 to 255.
	 */
	public void stretch_blt(final Rect dest_rect, final Bitmap src_bitmap, final Rect src_rect) {
		stretch_blt(dest_rect, src_bitmap, src_rect, 255);
	}

	/**
	 * Performs a block transfer from the src_bitmap box src_rect (Rect) to the
	 * specified bitmap box dest_rect (Rect).
	 * 
	 * @param dest_rect
	 * @param src_bitmap
	 * @param src_rect
	 * @param opacity
	 *            can be set from 0 to 255.
	 */
	public void stretch_blt(final Rect dest_rect, final Bitmap src_bitmap, final Rect src_rect, final int opacity) {
		data.stretch_blt(dest_rect, src_bitmap, src_rect, opacity);

	}

	/**
	 * Fills the bitmap box (x, y, width, height) or rect (Rect) with color
	 * (Color).
	 * 
	 * @param rect
	 * @param color
	 */
	public void fill_rect(final Rect rect, final Color color) {
		fill_rect(rect.x, rect.y, rect.width, rect.height, color);
	}

	/**
	 * Fills the bitmap box (x, y, width, height) or rect (Rect) with color
	 * (Color).
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 */
	public void fill_rect(final int x, final int y, final int width, final int height, final Color color) {
		data.fill_rect(x, y, width, height, color);
		
	}

	/**
	 * Gets the color (Color) at the specified pixel (x, y).
	 * 
	 * @param x
	 * @param y
	 */
	public Color get_pixel(final int x, final int y) {
		return data.get_pixel(x, y);
	}

	/**
	 * Sets the specified pixel (x, y) to color (Color).
	 * 
	 * @param x
	 * @param y
	 */
	public void set_pixel(final int x, final int y, final Color color) {
		fill_rect(x, y, 1, 1, color);
	}

	/**
	 * Draws a string str in the bitmap box (x, y, width, height) or rect
	 * (Rect). If the text length exceeds the box's width, the text width will
	 * automatically be reduced by up to 60 percent. Horizontal text is
	 * left-aligned by default; set align to 1 to center the text and to 2 to
	 * right-align it. Vertical text is always centered. As this process is
	 * time-consuming, redrawing the text with every frame is not recommended.
	 * 
	 * @param rect
	 * @param str
	 */
	public void draw_text(final Rect rect, final Object str) {
		draw_text(rect.x, rect.y, rect.width, rect.height, str, 0);
	}

	/**
	 * Draws a string str in the bitmap box (x, y, width, height) or rect
	 * (Rect). If the text length exceeds the box's width, the text width will
	 * automatically be reduced by up to 60 percent. Horizontal text is
	 * left-aligned by default; set align to 1 to center the text and to 2 to
	 * right-align it. Vertical text is always centered. As this process is
	 * time-consuming, redrawing the text with every frame is not recommended.
	 * 
	 * @param rect
	 * @param str
	 * @param align
	 */
	public void draw_text(final Rect rect, final Object str, final int align) {
		draw_text(rect.x, rect.y, rect.width, rect.height, str, align);
	}

	/**
	 * Draws a string str in the bitmap box (x, y, width, height) or rect
	 * (Rect). If the text length exceeds the box's width, the text width will
	 * automatically be reduced by up to 60 percent. Horizontal text is
	 * left-aligned by default; set align to 1 to center the text and to 2 to
	 * right-align it. Vertical text is always centered. As this process is
	 * time-consuming, redrawing the text with every frame is not recommended.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param str
	 */
	public void draw_text(final int x, final int y, final int width, final int height, final Object str) {
		draw_text(x, y, width, height, str, 0);
	}

	/**
	 * Draws a string str in the bitmap box (x, y, width, height) or rect
	 * (Rect). If the text length exceeds the box's width, the text width will
	 * automatically be reduced by up to 60 percent. Horizontal text is
	 * left-aligned by default; set align to 1 to center the text and to 2 to
	 * right-align it. Vertical text is always centered. As this process is
	 * time-consuming, redrawing the text with every frame is not recommended.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param str
	 * @param align
	 */
	public void draw_text(final int x, final int y, final int width, final int height, final Object str, final int align) {
		// TODO strength to 60% Support
		data.draw_text(x, y, width, height, str.toString(), align);
	
	}



	/**
	 * Changes the bitmap's hue within 360 degrees of displacement. This process
	 * is time-consuming. Furthermore, due to conversion errors, repeated hue
	 * changes may result in color loss.
	 * 
	 * @param hue
	 */
	// public void hue_change(final int hue) {
	// data.hue_change(hue);
	// }

	/**
	 * Frees the bitmap. If the bitmap has already been freed, does nothing.
	 */
	public void dispose() {
	
		if (!isDisposed()) {
			data.dispose();
			data = null;
		}
	}

	/**
	 * Returns TRUE if the bitmap has been freed.
	 * 
	 * @return
	 */
	public boolean isDisposed() {
		return data == null;
	}

	/**
	 * @return the data
	 */
	public IBitmap getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(final IBitmap data) {
		this.data = data;
	}
	public void erase(Rect args)
	{
	    fill_rect(args, Ycore.YCORE_BLACK);
	}
	private Rect eraseRect=new Rect(0,0,0,0);
	public void erase(int args1,int args2,int args3,int args4)
	{
		eraseRect.set(args1,args2,args3,args4);
			
	
	    fill_rect(eraseRect, Ycore.YCORE_BLACK);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bitmap [font=" + font + ", data=" + data + ", isLoaded=" + isLoaded + "]";
	}

//	/**
//	 * Add Sprite
//	 * 
//	 * @param s
//	 */
//	public void addSprite(Sprites s) {
//	
//	}
//
//	/**
//	 * Delete Sprite
//	 * 
//	 * @param s
//	 */
//	public void delSprite(Sprites s) {
//		
//	}


	/**
	 * Gets the box (Rect) used when drawing a string str with the draw_text
	 * method. Does not include the angled portions of italicized text.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param str
	 * @param align
	 */
	public Rect text_size(final Object str) {
		return data.text_size(str.toString());
	}
	/**
	 * Code by Neo-Bahamut
	 */
	protected void hue_change(int hue) {
		for (int x = 0, l = width(); x < l; x++) {
			for (int y = 0, m = width(); y < m; y++) {
				Color c = get_pixel(x, y);
				// need update?
				if (c.alpha == 0) {
					continue;
				}

				// siehe Code aus der MACL
				Color hc = Color.to_hsb(c.red, c.green, c.blue);
				double h = (hc.red + hue) % 360;
				// siehe Code aus der MACL
				Color n = Color.hsb_to_rgb(h, hc.green, hc.blue);
				// siehe Code aus der MACL
				Color n2 = new Color(n.red, n.green, n.blue);
				n2.alpha = c.alpha;
				set_pixel(x, y, n2);
			}
		}
	}
} 
