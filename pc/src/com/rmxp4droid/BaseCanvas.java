package com.rmxp4droid;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rmxp4droid.cxysfx.java2d.component.AWTKeyListener;

 
 
public class BaseCanvas  {
	
	private boolean isDrawing;
    JFrame frame;
    Canvas canvas;
    BufferStrategy bufferStrategy;
	public BaseCanvas() {
		 frame = new JFrame("");
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(640, 480));
        panel.setLayout(null);
         
        canvas = new Canvas();
        canvas.setBounds(0, 0, 640, 480);
        canvas.setIgnoreRepaint(true);
         
        panel.add(canvas);
        canvas.addKeyListener(new AWTKeyListener());
//        frame.setIconImage(new AWTImage(DateLoaderControl.getImageInputStream(BaseConf.iconImage)).getBufferedImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
         
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
       
    
	}
	
	private BaseView baseView=null;
	
		public void setBaseView(BaseView baseView) {
		this.baseView = baseView;
	}

		   Graphics2D g=null;
	public  void postInvalidate() {

	
		if(this.isDrawing())
		{ g = (Graphics2D) bufferStrategy.getDrawGraphics();
		    g.clearRect(0, 0, 640, 480);
		    baseView.render();
				        bufferStrategy.show();
				        doFPS();

		        this.setDrawing(false);
			}
		
	}
	private  long startTime= System.currentTimeMillis();  
	private void doFPS()
	{
		  /**取得更新游戏结束的时间**/  

	    long endTime = System.currentTimeMillis();  

	    /**计算出游戏一次更新的毫秒数**/  

	    int diffTime  = (int)(endTime - startTime);  

	    /**确保每次更新时间为30帧**/  

	    while(diffTime <=BaseConf.FPS) {  

	        diffTime = (int)(System.currentTimeMillis() - startTime);  

	        /**线程等待**/  

	        Thread.yield();  
	    }  

        startTime= System.currentTimeMillis();  
	}
//	
	public boolean isDrawing() {
		return isDrawing;
	}
	
	public BaseView getBaseView() {
		return baseView;
	}

	public void setDrawing(boolean isDrawing) {
		this.isDrawing = isDrawing;
	}

	public Graphics2D getG() {
		return g;
	} 
  
}