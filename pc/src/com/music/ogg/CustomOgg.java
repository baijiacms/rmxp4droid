package com.music.ogg;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;

import com.rmxp4droid.pub.component.MusicGeter;
import com.rmxp4droid.pub.control.interfaces.Rmxp4droidSound;

public class CustomOgg implements Rmxp4droidSound  {
	private Audio oggEffect;

	@Override
	public void play(String name) {
		name=MusicGeter.searchAudio(name).getPath();
		if(name.endsWith(".ogg"))
		{
		try {
			oggEffect = AudioLoader.getAudio("OGG",MusicGeter.getMusicFile(name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		oggEffect.playAsSoundEffect(1.0f, 1.0f, false); 
		}
		if(name.endsWith(".wav"))
		{
		try {
			oggEffect = AudioLoader.getAudio("WAV",MusicGeter.getMusicFile(name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		oggEffect.playAsSoundEffect(1.0f, 1.0f, false); 
		}
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	} 
	
}
