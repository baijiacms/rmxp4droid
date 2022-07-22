/**
 * 
 */
package com.rmxp4droid.cxysfx.java2d;





import com.rmxp4droid.cxysfx.core.control.RenderControl;
import com.rmxp4droid.pub.component.Sprites;
import com.rmxp4droid.pub.interfaces.IBitmap;
import com.rmxp4droid.pub.interfaces.IRenderer;
import com.rmxp4droid.pub.interfaces.ISprite;


/**
 * 
 */
public class Java2D extends IRenderer {





	/*
	 * (non-Javadoc)
	 * 
	 * @see de.yaams.jrgss.core.render.core.IRenderer#graphicsUpdate()
	 */
	@Override
	public  void graphicsUpdate() {
//		((Java2DWindow) RenderCenter.getWindow()).getArea().repaint();
		(RenderControl.getINSTANCEOF().getWindow()).repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.yaams.jrgss.core.render.core.IRenderer#createBitmap(int, int,
	 * de.yaams.jrgss.core.java.Bitmap)
	 */
	@Override
	public IBitmap createBitmap(int width, int height) {
		return new Java2DBitmap(width, height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.yaams.jrgss.core.render.core.IRenderer#createBitmap(java.lang.String,
	 * de.yaams.jrgss.core.java.Bitmap)
	 */
	@Override
	public IBitmap createBitmap(String filename) {
		return new Java2DBitmap(filename);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.yaams.jrgss.core.render.core.IRenderer#createSprite(java.lang.String,
	 * de.yaams.jrgss.core.java.ISprite)
	 */
	@Override
	public ISprite createSprite(Sprites container) {
		return new Java2DSprite(container);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.yaams.jrgss.core.render.core.IRenderer#getName()
	 */
	@Override
	public String getName() {
		return "cxy game";
	}



}
