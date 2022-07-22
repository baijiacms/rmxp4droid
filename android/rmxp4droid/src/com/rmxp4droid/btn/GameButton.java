package com.rmxp4droid.btn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.rmxp4droid.R;
import com.rmxp4droid.pub.component.YInput;

public class GameButton extends Button{

	public GameButton(Context context) {
		super(context);
	}

	public GameButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int key=0;
		switch(this.getId())
		{
		case R.id.btnUp:
			key=YInput.UP;
			break;
		case R.id.btnDown:
			key=YInput.DOWN;
			break;
		case R.id.btnLeft:
			key=YInput.LEFT;
			break;
		case R.id.btnRight:
			key=YInput.RIGHT;
			break;
		case R.id.buttonA:
			key=YInput.A;
			break;
		case R.id.buttonb:
			key=YInput.B;
			break;
		case R.id.buttonc:
			key=YInput.C;
			break;
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			YInput.setKeyDown(key);
			break;
		case MotionEvent.ACTION_UP:
			YInput.setKeyUp(key);
			break;
		}
		return super.onTouchEvent(event);
	}

}
