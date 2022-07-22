package com.rmxp4droid.btn;

import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.rmxp4droid.R;

public class ExitButton extends Button {
	private AlertDialog.Builder exitDialog=null;
	public ExitButton(Context context) {
		super(context);
		init(context);
	}

	public ExitButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	private void init(Context context)
	{
		exitDialog=new AlertDialog.Builder(context);
		exitDialog.setTitle("Exit rmxp4droid?");
		exitDialog.setIcon(R.drawable.exit);
		exitDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {  
	  
	        @Override  
	        public void onClick(DialogInterface dialog, int which) {  
	        // 点击"确认"后的操作   
	        	System.exit(1);
	  
	        }  
	    });  
		exitDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {  
	  
	        @Override  
	        public void onClick(DialogInterface dialog, int which) {  
	        // 点击"返回"后的操作,这里不设置没有任何操作   
	        }  
	    });
		
		
		
	 
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:
			
			switch(this.getId())
			{
			case R.id.buttonExit:
				exitSystem();
				break;
			}
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		return super.onTouchEvent(event);
	}
	private void exitSystem()
	{
		
		exitDialog.show();  
	}
	
}
