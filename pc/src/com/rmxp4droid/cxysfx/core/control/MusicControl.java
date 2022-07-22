package com.rmxp4droid.cxysfx.core.control;


import com.music.ogg.CustomOgg;
import com.music.servequake.truemusic.Playlist;
import com.rmxp4droid.pub.control.interfaces.Rmxp4droidSound;
import com.rmxp4droid.pub.interfaces.IMusic;


public class MusicControl {
	private static MusicControl INSTANCEOF=new MusicControl();

	private  IMusic musicSePlayer=new BlankMucisPlayer("se");
	private  IMusic musicBgmPlayer=new BlankMucisPlayer("bgm");
	private  IMusic musicBgsPlayer=new BlankMucisPlayer("bgs");
	private  IMusic musicMePlayer=new BlankMucisPlayer("me");
	public void setMusicBgmPlayer(IMusic musicBgmPlayer) {
		this.musicBgmPlayer = musicBgmPlayer;
	}
	public void setMusicSEPlayer(IMusic musicSePlayer) {
		this.musicSePlayer = musicSePlayer;
	}
	public void setMusicBgsPlayer(IMusic musicBgsPlayer) {
		this.musicBgsPlayer = musicBgsPlayer;
	}
	public void setMusicMePlayer(IMusic musicMePlayer) {
		this.musicMePlayer = musicMePlayer;
	}
	public static MusicControl getINSTANCEOF() {
		return INSTANCEOF;
	}

	public  IMusic getMusicSePlayer() {
		return musicSePlayer;
	}

	public  IMusic getMusicBgmPlayer() {
		return musicBgmPlayer;
	}
	
	public  IMusic getMusicBgsPlayer() {
		return musicBgsPlayer;
	}

	public  IMusic getMusicMePlayer() {
		return musicMePlayer;
	}

	
	
}
class BlankMucisPlayer implements IMusic
{
	protected  Rmxp4droidSound rmxp4droidSound=null;
	public BlankMucisPlayer(String type)
	{
		if("se".equals(type))
		{
		rmxp4droidSound = new CustomOgg();
		}
		if("bgm".equals(type))
		{
		rmxp4droidSound = new Playlist("bgm");
		}
		if("bgs".equals(type))
		{
		rmxp4droidSound = new CustomOgg();
		}
		if("me".equals(type))
		{
		rmxp4droidSound = new Playlist("me");
		}
	}
	@Override
	public void play(String filename, int volume) {
		// TODO Auto-generated method stub
		rmxp4droidSound.play(filename);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	
	}
	
	
}
