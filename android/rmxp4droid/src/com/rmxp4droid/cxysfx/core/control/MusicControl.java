package com.rmxp4droid.cxysfx.core.control;

import com.rmxp4droid.pub.interfaces.IMusic;

public class MusicControl {
	private static MusicControl INSTANCEOF=new MusicControl();

	private  IMusic musicSePlayer=new BlankMucisPlayer();
	private  IMusic musicBgmPlayer=new BlankMucisPlayer();
	private  IMusic musicBgsPlayer=new BlankMucisPlayer();
	private  IMusic musicMePlayer=new BlankMucisPlayer();
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

	@Override
	public void play(String filename, int volume) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
	
}
