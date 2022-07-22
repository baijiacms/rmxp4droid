package com.rmxp4droid;


import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.rmxp4droid.cxysfx.java2d.Java2DSprite;
import com.rmxp4droid.cxysfx.java2d.component.AWTAlphaComposite;
import com.rmxp4droid.cxysfx.java2d.component.AWTComposite;
import com.rmxp4droid.cxysfx.java2d.component.AWTImage;
import com.rmxp4droid.pub.component.Sprites;

public class BaseView {
	private BaseCanvas baseCanvas=null;

	
	public BaseView() {

		sprites = new ArrayList<Sprites>();
		this.setFPS(BaseConf.FPS);
		
	}

	public void setBaseCanvas(BaseCanvas baseCanvas) {
		this.baseCanvas = baseCanvas;
		baseCanvas.setBaseView(this);
	}


	public  void repaint()
    {
		if(baseCanvas.isDrawing()==false)
		{
		baseCanvas.setDrawing(true);
//         render();
         baseCanvas.postInvalidate();
		}
    }
    
    
    

    public void initScreen() {
		this.setFPS(BaseConf.FPS);

	}
    public void render() {
      
    	drawSprites(this);
    }
    private boolean refreshSort=false;
    private final ReverseSort reverseSort=new ReverseSort();
	private void drawSprites( BaseView bc) {
		// draw the sprites
		if(refreshSort)
		{
			Collections.sort(this.sprites,reverseSort);
			refreshSort=false;
		}
		for(int i=0;i<this.sprites.size();)
		{
			Sprites s=this.sprites.get(i);
			if(s.x<-BaseConf.WIDTH*0.1||s.x>BaseConf.WIDTH+BaseConf.WIDTH*0.1
					||s.y<-BaseConf.HEIGHT*0.1||s.y>BaseConf.HEIGHT+BaseConf.HEIGHT*0.1)
			{
//				this.sprites.remove(s);
				i++;
			}else
			{
				((Java2DSprite)s.getSprite()).renderSprite(bc);
				i++;
			}
			
		}

	}
	public void drawImage(AWTImage img,
		      int dx1, int dy1, int dx2, int dy2,
		      int sx1, int sy1, int sx2, int sy2)
	{
	
	
		baseCanvas.getG().drawImage(img.getBufferedImage(),  dx1,  dy1,  dx2,  dy2,
			       sx1,  sy1,  sx2,  sy2,null );
	
	}
	public void drawImageCache(AWTImage img)
	{

		baseCanvas.getG().drawImage(img.getBufferedImage(),  0,0,null );
	}
     

	


	
	public int height() {
		// TODO Auto-generated method stub
		return BaseConf.HEIGHT;
	}


	
	


	
	public int width() {
		// TODO Auto-generated method stub
		return BaseConf.WIDTH;
	}

	
	private final List<Sprites> sprites;
	private int fps;

	
	public void setRefreshSort(boolean refreshSort) {
		this.refreshSort = refreshSort;
	}

	/**
	 * Register Sprite
	 * 
	 * @param sprite
	 */
	public void registerSprite(Sprites sprite) {
		sprites.add(sprite);
		refreshSort=true;
		
//		int z = sprite.z() + (sprite.viewport() == null ? 0 : sprite.viewport().z());
//		// look to find the right position
//		int i = 0;
//		while (i < sprites.size()) {
//			// bigger? add it
//			if (sprites.get(i).z() > z) {
//				sprites.add(i, sprite);
//				return;
//			}
//			i++;
//		}
//		// found nothing?
//		sprites.add(sprites.size(), sprite);
	}

	/**
	 * Clear all Sprites
	 */
	public void clear() {
		// dispose all
		for (Sprites s : sprites) {
			s.dispose();
		}

		sprites.clear();
	}

	/**
	 * UnRegister Sprite
	 * 
	 * @param sprite
	 */
	public void unregisterSprite(Sprites sprite) {
		if (isRegisterSprited(sprite)) {
			sprites.remove(sprite);
		}
	}

	/**
	 * Is this Sprite registered?
	 * 
	 * @param sprite
	 */
	public boolean isRegisterSprited(Sprites sprite) {
		return sprites.contains(sprite);
	}

	

	/**
	 * In [Smooth Mode], the number of times the screen is refreshed per second. The larger the value, the more CPU
	 * power is required. Normally set at 40. When not in [Smooth Mode], the refresh rate is halved, and graphics are
	 * drawn in every other frame. Changing this property is not recommended; however, it can be set anywhere from 10 to
	 * 120. Values out of range are automatically corrected.
	 * 
	 * @return
	 */
	public int getFPS() {
		return fps;
	}

	/**
	 * Set FPS
	 */
	public void setFPS(int fps) {
		this.fps = fps;
	}
	/**
	 * android版需要
	 */
	public void refreshUI()
	{
		
	}

	public AWTComposite getComposite() {
		return new AWTComposite(((Graphics2D)baseCanvas.getG()).getComposite());
	}
	public void setComposite(AWTComposite awtc) {
		((Graphics2D)baseCanvas.getG()).setComposite(awtc.getComposite());
	}
	public void setComposite(int rule, float alpha) {
		((Graphics2D)baseCanvas.getG()).setComposite(new AWTAlphaComposite(rule, alpha).getComposite());
	}
	

}
class ReverseSort implements Comparator<Sprites>{
	 public int compare(Sprites obj1,Sprites obj2) {
	        return (obj1.getRealZ()-obj2.getRealZ());
	    }
}