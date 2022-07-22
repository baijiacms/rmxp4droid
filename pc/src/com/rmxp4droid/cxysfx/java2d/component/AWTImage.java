package com.rmxp4droid.cxysfx.java2d.component;


import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.rmxp4droid.cxysfx.core.control.DateLoaderControl;
import com.rmxp4droid.pub.component.Color;
import com.rmxp4droid.pub.component.Rect;


public class AWTImage {
	private BufferedImage buf=null;
	public AWTImage(int w, int h)
	{
		buf=new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
	}
	public AWTImage(String pathName)
	{
//		try {
//			buf= ImageIO.read(new FileInputStream(pathName));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		try {
		buf= ImageIO.read(DateLoaderControl.getINSTANCEOF().getFileInputStream(pathName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void drawImage(AWTImage src, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2)
	{

			((Graphics2D) buf.getGraphics()).drawImage(src.getBufferedImage(), dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
			
		 	
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

		Graphics2D g = (Graphics2D) buf.getGraphics();
		Rect rect = text_size(str);
		
		height -= 3;
		width -= 6;

		//left
		if (align == 0) {
			g.drawString(str, x, y + height / 2 + rect.height / 2);
		}

		// center
		if (align == 1) {
			g.drawString(str, x + width / 2 - rect.width / 2, y + height / 2
					+ rect.height / 2);

		}

		// right
		if (align == 2) {
			g.drawString(str, x + width - rect.width - 1, y + height / 2
					+ rect.height / 2);
		}

	
	
	}
	private Rect textRect=new Rect(0,0,0,0);
	public Rect text_size(String str)
	{
		Graphics2D g = (Graphics2D) buf.getGraphics();
		// set for calculate
		// data.getGraphics().setColor(Transfer.convertYtC(container.font.color));

		Rectangle2D r = g.getFontMetrics().getStringBounds(str, g);
		// set it
		textRect.set(0, 0, (int) r.getWidth(), (int) r.getHeight());
//		return new Rect(0, 0, (int) r.getWidth(), (int) r.getHeight());
		return textRect;
	}

	public void fill_rect(int x, int y, int width, int height, Color color) {
	
		Graphics2D g = (Graphics2D) buf.getGraphics();
		g.setBackground(new java.awt.Color(0f, 0f, 0f, 0f));
		g.clearRect(x, y, width, height);
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
	
	public BufferedImage getBufferedImage() {
		return buf;
	}
	
	
	public int getRGB(int x,int y)
	{
		return buf.getRGB(x, y);
	}
	public void bitDispose()
	{
		
	}
}
