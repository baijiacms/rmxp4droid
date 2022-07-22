package com.rmxp4droid.cxysfx.plugin;



import com.rmxp4droid.Logging;
import com.rmxp4droid.cxysfx.core.control.DateLoaderControl;
import com.rmxp4droid.pub.interfaces.IMusic;

public class BgmMusicPlayer implements IMusic {
	 
	private android.media.MediaPlayer player;
	public BgmMusicPlayer()
	{ 
		
	}

  
	@Override
	public void play(String filename, int volume) {
		try {
			if(player==null)
			{
			player=new android.media.MediaPlayer();
			}else
			{
				stop();
				player=new android.media.MediaPlayer();
			}
			player.setDataSource(DateLoaderControl.getINSTANCEOF().getMusicInputStream(filename));

			player.prepare();
			player.setLooping(true);

			player.seekTo(0);
			player.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logging.writerLog(e);
		} 
	}

	@Override
	public void stop() {
		if(player!=null)
		{
			try {
			player.stop();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Logging.writerLog(e);
			} 
			try {
			player.release();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Logging.writerLog(e);
			} 
			player=null;
		}
	}

}
