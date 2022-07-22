package com.rmxp4droid;





import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;


public class MainActivity extends Activity   {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Thread.setDefaultUncaughtExceptionHandler(new Logging());
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		 
	}


	
}
