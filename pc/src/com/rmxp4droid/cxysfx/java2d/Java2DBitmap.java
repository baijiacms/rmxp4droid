/**
 * 
 */
package com.rmxp4droid.cxysfx.java2d;



import com.rmxp4droid.cxysfx.core.control.DateLoaderControl;
import com.rmxp4droid.cxysfx.java2d.component.AWTImage;
import com.rmxp4droid.pub.component.Bitmap;
import com.rmxp4droid.pub.component.Color;
import com.rmxp4droid.pub.component.Rect;
import com.rmxp4droid.pub.interfaces.IBitmap;


/**
 * 
 */
public class Java2DBitmap extends IBitmap {

	protected AWTImage data;

	/**
	 * @param width
	 * @param height
	 */
	public Java2DBitmap(int width, int height) {
		data = new AWTImage(width, height);
		// data = new BufferedImage(width, height,
		// BufferedImage.TYPE_4BYTE_ABGR);
	}

	/**
	 * @param filename
	 */
	public Java2DBitmap(String filename) {
			data =  new AWTImage(DateLoaderControl.getINSTANCEOF().getImageInputStream(filename));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.yaams.jrgss.core.render.core.IBitmap#stretch_blt(de.yaams.jrgss.core
	 * .java.Rect, de.yaams.jrgss.core.java.Bitmap,
	 * de.yaams.jrgss.core.java.Rect, int)
	 */
	@Override
	public void stretch_blt(Rect dest_rect, Bitmap src_bitmap, Rect src_rect,
			int opacity) {
		if( src_bitmap.getData()!=null)
		{
		data.drawImage(((Java2DBitmap) src_bitmap.getData()).getData(),
						dest_rect.x, dest_rect.y,
						dest_rect.x + dest_rect.width,
						dest_rect.y + dest_rect.height, src_rect.x, src_rect.y,
						src_rect.x + src_rect.width,
						src_rect.y + src_rect.height);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.yaams.jrgss.core.render.core.IBitmap#width()
	 */
	@Override
	public int width() {
		if(data!=null)
		{
		return data.getWidth();
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.yaams.jrgss.core.render.core.IBitmap#height()
	 */
	@Override
	public int height() {
		if(data!=null)
		{
		return data.getHeight();
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.yaams.jrgss.core.render.core.IBitmap#fill_rect(int, int, int,
	 * int, de.yaams.jrgss.core.java.Color)
	 */
	@Override
	public void fill_rect(int x, int y, int width, int height, Color color) {
		data.fill_rect( x,  y,  width,  height,  color);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.yaams.jrgss.core.render.core.IBitmap#get_pixel(int, int)
	 */
	@Override
	public Color get_pixel(int x, int y) {
		int c = data.getRGB(x, y);
		int alpha = (c & 0xff000000) >> 32;
		int red = (c & 0x00ff0000) >> 16;
		int green = (c & 0x0000ff00) >> 8;
		int blue = c & 0x000000ff;
		// and the Java Color is ...
		return new Color(red, green, blue, alpha);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.yaams.jrgss.core.render.core.IBitmap#draw_text(int, int, int,
	 * int, java.lang.String, int)
	 */
	@Override
	public void draw_text(int x, int y, int width, int height, String str,
			int align) {
		data.draw_text( x,  y,  width,  height,  str,
				align);
		
	}




	

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.yaams.jrgss.core.render.core.IBitmap#dispose()
	 */
	@Override
	public void dispose() {
		if(data!=null)
		{
		data.bitDispose();
		data = null;
		}
	}

	/**
	 * @return the data
	 */
	public AWTImage getData() {
		return data;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.yaams.jrgss.core.render.core.IBitmap#text_size(java.lang.String)
	 */
	@Override
	public Rect text_size(String str) {
		return data.text_size(str);
	}
}
