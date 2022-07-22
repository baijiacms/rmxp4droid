package com.rmxp4droid;



import com.rmxp4droid.cxysfx.core.control.BridgeControlImp;
import com.rmxp4droid.cxysfx.core.control.MusicControl;
import com.rmxp4droid.cxysfx.core.control.RenderControl;
import com.rmxp4droid.cxysfx.core.rbruntime.RBRuntime;
import com.rmxp4droid.cxysfx.java2d.Java2D;
import com.rmxp4droid.cxysfx.plugin.BgmMusicPlayer;
import com.rmxp4droid.cxysfx.plugin.BgsMusicPlayer;
import com.rmxp4droid.pub.component.Ycore;

public class MainHelper {
	private BaseView baseView=null;
	public MainHelper(BaseView baseCanvas) 
	{
		this.baseView=baseCanvas;

		
	}
	public void start()
	{
		initMusicPlayer();
		initView();
		initBridge();
		initCoreScript();
	}
	
	private void initMusicPlayer()
	{
		MusicControl.getINSTANCEOF().setMusicBgmPlayer(new BgmMusicPlayer());
		BgsMusicPlayer bgs=new BgsMusicPlayer();
		MusicControl.getINSTANCEOF().setMusicSEPlayer(bgs);
		MusicControl.getINSTANCEOF().setMusicBgsPlayer(bgs);
		MusicControl.getINSTANCEOF().setMusicMePlayer(bgs);
	} 
	private void initView()
	{
		baseView.initScreen();
		RenderControl.getINSTANCEOF().set(new Java2D(),baseView);
	}
	private void initBridge()
	{//初始化传入bridge需要初始化的对象
		//脚本运行前传入
		BridgeControlImp bridgeControlImp=new BridgeControlImp();
		Ycore.setiMusicControl(bridgeControlImp);
		Ycore.setiRenderControl(bridgeControlImp);
	}
	private void initCoreScript()
	{
	
		new Thread(){
			public void run()
			{
			RBRuntime.getINSTANCEOF().loadScriptData();
			}
		}.start();
	}
//	public static void main(String[]args) throws Exception
//	{
//
//		MainHelper main=new MainHelper();
//		main.start();
//	}
}
