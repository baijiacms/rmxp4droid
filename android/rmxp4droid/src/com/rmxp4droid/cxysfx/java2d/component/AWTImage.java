package com.rmxp4droid.cxysfx.java2d.component;




import com.rmxp4droid.BaseView;
import com.rmxp4droid.pub.component.Color;
import com.rmxp4droid.pub.component.Rect;


public class AWTImage {
	private android.graphics.Bitmap buf = null;
	private android.graphics.Canvas canvas=null;
	public AWTImage(int w, int h)
	{
		buf = android.graphics.Bitmap.createBitmap(w, h,
				android.graphics.Bitmap.Config.ARGB_8888);
	}
	public AWTImage(String pathName)
	{
		buf = android.graphics.BitmapFactory.decodeFile(pathName);
	}
	private android.graphics.Rect r1 = new android.graphics.Rect(0,0,0,0);
	private android.graphics.Rect r2 = new android.graphics.Rect(0,0,0,0);
	public void drawImage(AWTImage src, int x1, int y1, int x2, int y2, int x3,
			int y3, int x4, int y4)
	{
		r1.set(x1, y1, x2, y2);
		r2.set(x3, y3, x4, y4);

		this.getCanvas().drawBitmap(src.getBufferedImage(), r2,
				r1, BaseView.paint);
	}
	public void draw_text(int x, int y, int width, int height, String str,
			int align)
	{
		// can draw?
//		if (container.font.color.alpha <= 0 || container.font.size < 6)
//			return;
		
		
	

//		// draw shadow?
//		if (container.font.shadow) {
//			drawText(x + 1, y + 1, width, height, str, align, 
//					container.font.color.alpha / 2,container);
//		}

		drawText(x, y, width, height, str, align,  255);

		
		
	}
	/**
	 * Helpermethod to draw, because of shadow
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param str
	 * @param align
	 */
	private void drawText(int x, int y, int width, int height, String str,
			int align,  double alpha) {
		// g.setColor(java.awt.Color.yellow);
		// g.drawString(str, 4, 14);

	
		Rect rect = text_size(str);
		
		height -= 3;
		width -= 6;

		//left
		if (align == 0) {
			this.getCanvas().drawText(str, x, y + height / 2 + rect.height / 2,BaseView.paint);
		}

		// center
		if (align == 1) {
			this.getCanvas().drawText(str, x + width / 2 - rect.width / 2, y + height / 2
					+ rect.height / 2,BaseView.paint);

		}

		// right
		if (align == 2) {
			this.getCanvas().drawText(str, x + width - rect.width - 1, y + height / 2
					+ rect.height / 2,BaseView.paint);
		}

	
	
	}
	private Rect textRect=new Rect(0,0,0,0);
	public Rect text_size(String str)
	{
		
		// get postion
			int theight=(int)BaseView.paint.getTextSize();;
		int twidth=(int)BaseView.paint.measureText(str);
//		int theight=rect.height();
//		int twidth=rect.width();
		// set it
//		return new Rect(0, 0,  twidth, theight);
		textRect.set(0, 0,  twidth, theight);
		return textRect;
	}
	private android.graphics.Rect r = new android.graphics.Rect(0,0,0,0);
	public void fill_rect(int x, int y, int width, int height, Color color) {
		r.set(x, y, x+width, y+height);
//		android.graphics.Rect r = new android.graphics.Rect(x, y, x+width, y+height);
		this.getCanvas().drawRect(r, BaseView.transpaprent);
		
		// clear
//		Graphics2D g = (Graphics2D) buf.getGraphics();
//		g.setBackground(new java.awt.Color(0f, 0f, 0f, 0f));
//		g.clearRect(x, y, width, height);
//
//		// clear?
//		if (color.alpha > 0) {
//			// draw
//
//		
//			
//			buf.getGraphics().fillRect(x, y, width, height);
//
//			
//		}
	}
	public int getWidth()
	{
		return buf.getWidth();
	}
	public int getHeight()
	{
		return buf.getHeight();
	}
	
	public android.graphics.Bitmap getBufferedImage() {
		return buf;
	}
	
	public android.graphics.Canvas getCanvas() {
		if(canvas==null)
		{
			canvas=new android.graphics.Canvas(buf);
		}
		return canvas;
	}
	public int getRGB(int x,int y)
	{
		return buf.getPixel(x, y);
	}
	public void bitDispose()
	{
		if(buf!=null)
		{
		buf.recycle();
		buf=null;
		}
	}
}
