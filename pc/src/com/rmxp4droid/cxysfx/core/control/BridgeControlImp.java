package com.rmxp4droid.cxysfx.core.control;




import com.rmxp4droid.BaseView;
import com.rmxp4droid.pub.control.interfaces.IMusicControl;
import com.rmxp4droid.pub.control.interfaces.IRenderControl;
import com.rmxp4droid.pub.interfaces.IMusic;
import com.rmxp4droid.pub.interfaces.IRenderer;

public class BridgeControlImp implements IMusicControl,IRenderControl {

	

	@Override
	public IMusic getMusicBgmPlayer() {
		// TODO Auto-generated method stub
		return MusicControl.getINSTANCEOF().getMusicBgmPlayer();
	}

	@Override
	public IMusic getMusicBgsPlayer() {
		// TODO Auto-generated method stub
		return MusicControl.getINSTANCEOF().getMusicBgsPlayer();
	}

	@Override
	public IMusic getMusicMePlayer() {
		// TODO Auto-generated method stub
		return MusicControl.getINSTANCEOF().getMusicMePlayer();
	}

	@Override
	public IMusic getMusicSePlayer() {
		// TODO Auto-generated method stub
		return MusicControl.getINSTANCEOF().getMusicSePlayer();
	}

	@Override
	public IRenderer er() {
		// TODO Auto-generated method stub
		return RenderControl.getINSTANCEOF().er();
	}

	@Override
	public BaseView getWindow() {
		// TODO Auto-generated method stub
		return RenderControl.getINSTANCEOF().getWindow();
	}

	

}
