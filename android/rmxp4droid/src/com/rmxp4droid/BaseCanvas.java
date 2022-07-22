package com.rmxp4droid;





import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.rmxp4droid.cxysfx.core.control.RenderControl;

 
public class BaseCanvas   extends SurfaceView   implements
SurfaceHolder.Callback{  
  
	public BaseCanvas(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.getHolder().addCallback(this);
//		this.getHolder().setSizeFromLayout();this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
	
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		holder.setFixedSize(BaseConf.WIDTH, BaseConf.HEIGHT);
		//修正bug 图像绘制的bug
		  Canvas canvas = holder.lockCanvas();
		  Paint vPaint = new Paint();
		  vPaint.setAlpha(0);
		  canvas.drawBitmap(android.graphics.Bitmap.createBitmap(1, 1,
		  android.graphics.Bitmap.Config.ARGB_8888), 0,0, vPaint);
		  holder.unlockCanvasAndPost(canvas);  
		  
		RenderControl.getINSTANCEOF().getWindow().setHolder(holder);

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}


      
}  