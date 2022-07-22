package com.rmxp4droid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

public class MySeekBar extends SeekBar{
	 public MySeekBar(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

	public MySeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
     public boolean onTouchEvent(MotionEvent event) {
             // TODO Auto-generated method stub
             //原来是要将TouchEvent传递下去的,我们不让它传递下去就行了
             //return super.onTouchEvent(event);
            
             return false ;
     }
}
