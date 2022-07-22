package com.rmxp4droid;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.SurfaceHolder;

import com.rmxp4droid.cxysfx.core.control.RenderControl;
import com.rmxp4droid.cxysfx.java2d.Java2DSprite;
import com.rmxp4droid.pub.component.Sprites;


public class BaseView {
	public static Paint paint = new Paint();    
	public static Paint transpaprent=null;//清除画面用画笔
	
	public BaseView() {

		sprites = new ArrayList<Sprites>();
		
	}

//	public void setSurfaceHolder(SurfaceHolder holder) {
//		this.holder = holder;	
//	}
	
	private SurfaceHolder holder;

	public void setHolder(SurfaceHolder holder) {
		this.holder = holder;
	}
	private android.graphics.Rect canvasRect=new android.graphics.Rect(0,0,BaseConf.WIDTH,BaseConf.HEIGHT);
	public  void repaint()
    {
		
		if(BaseConf.INITGAME==2)
		{//游戏中

			if(holder!=null)
			{
//				  drawSprites(awtImage.getCanvas());
//				  holder.setFixedSize(BaseConf.WIDTH, BaseConf.HEIGHT);
				  Canvas canvas =holder.lockCanvas(canvasRect);
				  drawSprites(canvas);
//				  canvas.drawBitmap(awtImage.getBufferedImage(), 0, 0,null);
				  holder.unlockCanvasAndPost(canvas);
//				   doFPS();
//			drawSprites(awtImage.getCanvas());
//			baseCanvas.drawMain();
			}
			
		}else
		{
			if(BaseConf.INITGAME==1)
			{//显示窗口
				BaseConf.INITGAME=2;
				RenderControl.getINSTANCEOF().getLoadingActivity().goMainActivity();
				
			}
		}

    }
    public void initScreen() {
		BaseView.paint.setColor(Color.WHITE);
		android.graphics.Typeface font= android.graphics.Typeface.createFromAsset(RenderControl.getINSTANCEOF().getLoadingActivity().getAssets(),BaseConf.FontFile);
		BaseView.paint.setTypeface(font);
		BaseView.paint.setTextSize(BaseConf.FontSize);
		BaseView.transpaprent=new Paint();
		BaseView.transpaprent.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
	}
     
    private boolean refreshSort=false;
    private final ReverseSort reverseSort=new ReverseSort();
	private void drawSprites( Canvas bc) {
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
	
	public void setRefreshSort(boolean refreshSort) {
		this.refreshSort = refreshSort;
	}
//	public void drawImage(AWTImage src, int x1, int y1, int x2, int y2, int x3,
//			int y3, int x4, int y4)
//	{
//		android.graphics.Rect r1 = new android.graphics.Rect(x1, y1, x2, y2);
//		android.graphics.Rect r2 = new android.graphics.Rect(x3, y3, x4, y4);
//
//		awtImage.getCanvas().drawBitmap(src.getBufferedImage(), r2,
//				r1, paint);
//	}

	
	private final List<Sprites> sprites;

	
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
//	
//		int i = 0;
//		while (i < sprites.size()) {
//			// bigger? add it
//			if (sprites.get(i).z() > z) {
//				sprites.add(i, sprite);
//				return;
//			}
//			i++;
//		}
//		
//		// found nothing?
//		sprites.add(sprites.size(), sprite);
	
	}

//	/**
//	 * Clear all Sprites
//	 */
//	public void clear() {
//		
//		// dispose all
//		for (Sprites s : sprites) {
//			s.dispose();
//		}
//
//		sprites.clear();
//	
//	}

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


//	public AWTImage getAwtImage() {
//		return awtImage;
//	}

	

	
}
class ReverseSort implements Comparator<Sprites>{
	 public int compare(Sprites obj1,Sprites obj2) { 
	        return (obj1.getRealZ()-obj2.getRealZ());
	    }
}