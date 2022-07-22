package com.rmxp4droid.cxysfx.plugin;



import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

import com.rmxp4droid.Logging;
import com.rmxp4droid.cxysfx.core.control.DateLoaderControl;
import com.rmxp4droid.pub.interfaces.IMusic;


public class BgsMusicPlayer implements IMusic {
	 
	private android.media.SoundPool soundPool;
	public BgsMusicPlayer()
	{  
		this.soundPool = new android.media.SoundPool(10,
				android.media.AudioManager.STREAM_MUSIC, 100);
	    soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
            
	           public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
	                    // TODO Auto-generated method stub
	                    soundPool.play(sampleId, 1, 1f, 0, 0, 1); 
	                   }
	     });
	}

  
	@Override
	public void play(String filename, int volume) {
		try {
			soundPool.load(DateLoaderControl.getINSTANCEOF().getMusicInputStream(filename), 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logging.writerLog(e);
		} 
	}

	@Override
	public void stop() {
	}

}
