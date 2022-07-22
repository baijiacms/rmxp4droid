package com.rmxp4droid;


import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SeekBar;

import com.rmxp4droid.cxysfx.core.control.RenderControl;

public class LoadingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading_main);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		if(BaseConf.INITGAME==0)
		{
				Thread.setDefaultUncaughtExceptionHandler(new Logging());
				RenderControl.getINSTANCEOF().setLoadingActivity(this);
				BaseConf.INITGAME=1;
				BaseView baseView=new BaseView();
				MainHelper main = new MainHelper(baseView);
				main.start();
			}
		
	
	}
	public void refreshUI()
	{
		
		SeekBar	seekBar=(SeekBar)LoadingActivity.this.findViewById(R.id.seekBar1);
		seekBar.setMax(BaseConf.LOADING_SEEKBAR_MAX);
		seekBar.setProgress(BaseConf.LOADING_SEEKBAR_INDEX);

	}
	
	public void goMainActivity() {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}


}
