/**
 * 
 */
package com.rmxp4droid.cxysfx.java2d;




import android.graphics.Canvas;

import com.rmxp4droid.BaseConf;
import com.rmxp4droid.BaseView;
import com.rmxp4droid.cxysfx.java2d.component.AWTImage;
import com.rmxp4droid.pub.component.Sprites;
import com.rmxp4droid.pub.component.Tone;
import com.rmxp4droid.pub.interfaces.ISprite;


/**
 * 
 */
public class Java2DSprite extends ISprite {
	
	// protected GraphicsConfiguration gc;
	protected Tone toneClone;
	protected Sprites s;
	/**
	 * @param container
	 */
	public Java2DSprite(final Sprites container) {
		this.s = container;
	}
	/**
	 * @return the container
	 */
	public Sprites getSprite() {
		return s;
	}

	/**
	 * @param container
	 *            the container to set
	 */
	public void setSprite(Sprites s) {
		this.s = s;
	}
	
	/**
	 * Draw a sprite on the window
	 * 
	 * @param o
	 * @param s
	 */
	public void renderSprite(Canvas g) {
		// has bitmap and visible??
		if (s.bitmap() == null || !s.visible || s.zoom_x < 0 || s.zoom_y < 0 || s.opacity() <= 0
				|| s.viewport() != null
				&& (!s.viewport().visible || s.viewport().rect.width <= 0 || s.viewport().rect.height <= 0))
			return;
		
	
		
		// dest
		int x = s.x - s.ox;
		int y = s.y - s.oy;
		int width = (int) (s.bitmap().width());
		int height = (int) (s.bitmap().width() );
		
		// source
		int sX = 0;
		int sY = 0;
		
		// has source rect?
		if (s.src_rect != null) {
			sX += s.src_rect.x;
			sY += s.src_rect.y;
			width = s.src_rect.width > s.bitmap().width() ? s.bitmap().width() : s.src_rect.width;
			height = s.src_rect.height > s.bitmap().height() ? s.bitmap().height() : s.src_rect.height;
		}
		
		// has viewport?
		if (s.viewport() != null) {
			// g.setClip(s.viewport().ox + s.viewport().rect.x, s.viewport().oy
			// + s.viewport().rect.y,
			// s.viewport().rect.width, s.viewport().rect.height);
			final int oX = x;
			final int oY = y;
			x = x - s.viewport().ox + s.viewport().rect.x;
			y = y - s.viewport().oy + s.viewport().rect.y;
			// resize width&height
			width = x + width >= s.viewport().rect.x + s.viewport().rect.width ? s.viewport().rect.x
					+ s.viewport().rect.width - oX : width;
			height = y + height >= s.viewport().rect.y + s.viewport().rect.height ? s.viewport().rect.y
					+ s.viewport().rect.height - oY : height;
		}
		
		// usefull to render?
		if (x + width < 0 || x > BaseConf.WIDTH || y + height < 0 || y > BaseConf.HEIGHT)
				return;
		int oldAlpha=-1; 
		if (s.opacity() < 255) {
		oldAlpha=BaseView.paint.getAlpha();
		BaseView.paint.setAlpha((int)(s.opacity() ));
		}
		if( s.bitmap().getData()!=null)
		{
//			g.drawImage(needCache ? cache : ((Java2DBitmap) s.bitmap().getData()).getData(), x, y, x + width, y + height,
//					sX, sY, sX + width, sY + height, null);
			drawImage(g,((Java2DBitmap) s.bitmap().getData()).getData(), x, y,(int)((x + width)* (s.zoom_x==0?1f:s.zoom_x)), 	(int)((y + height)*( s.zoom_y==0?1f:s.zoom_y)),
					sX, sY, 	(int)((sX + width)* (s.zoom_x==0?1f:s.zoom_x)),	(int)((sY + height)*( s.zoom_y==0?1f:s.zoom_y)) );
			

		}
		if(oldAlpha!=-1)
		{
		BaseView.paint.setAlpha(oldAlpha);
		}
	}
	private android.graphics.Rect r1 = new android.graphics.Rect(0,0,0,0);
	private android.graphics.Rect r2 = new android.graphics.Rect(0,0,0,0);
	private void drawImage(Canvas canvas,AWTImage src, int x1, int y1, int x2, int y2, int x3,
			int y3, int x4, int y4)
	{
		r1.set(x1, y1, x2, y2);
		r2.set(x3, y3, x4, y4);

		canvas.drawBitmap(src.getBufferedImage(), r2,
				r1, BaseView.paint);
	}
	
}
