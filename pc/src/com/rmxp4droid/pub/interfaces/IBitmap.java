/**
 * 
 */
package com.rmxp4droid.pub.interfaces;

import com.rmxp4droid.pub.component.Bitmap;
import com.rmxp4droid.pub.component.Color;
import com.rmxp4droid.pub.component.Rect;

public abstract class IBitmap {

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
	public abstract void stretch_blt(Rect dest_rect, Bitmap src_bitmap,
			Rect src_rect, int opacity);

	/**
	 * Gets the bitmap width.
	 * 
	 * @return
	 */
	public abstract int width();

	/**
	 * Gets the bitmap height.
	 * 
	 * @return
	 */
	public abstract int height();

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
	public abstract void fill_rect(int x, int y, int width, int height,
			Color color);

	/**
	 * Gets the color (Color) at the specified pixel (x, y).
	 * 
	 * @param x
	 * @param y
	 */
	public abstract Color get_pixel(int x, int y);

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
	public abstract void draw_text(int x, int y, int width, int height,
			String str, int align);




	/**
	 * Frees the bitmap. If the bitmap has already been freed, does nothing.
	 */
	public abstract void dispose();
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
	public abstract Rect text_size(String str);
}
