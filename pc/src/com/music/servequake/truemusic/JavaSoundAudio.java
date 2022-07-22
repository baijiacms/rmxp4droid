
package com.music.servequake.truemusic;

import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

import com.rmxp4droid.pub.component.MusicGeter;


public class JavaSoundAudio extends Audio implements Runnable
{
	AudioInputStream ais;
	AudioFormat format;
	SourceDataLine line;
	Thread runner;
	String res;
	int pos=0, current=0;
	boolean pause=true;
	Playlist l;
	AudioFileFormat aff;
	byte[] buf;
	FloatControl vol;
	int frames = 0;
	float seconds;
	Tag t;
		

	public String resource() { return res; }
	public void resource(String res) throws Exception
	{
		this.res = res;
		
		aff = AudioSystem.getAudioFileFormat(MusicGeter.getMusicFile(res));
		frames = aff.getFrameLength();
		format = aff.getFormat();
		seconds = (float)frames / format.getFrameRate();
		
		ais = AudioSystem.getAudioInputStream(MusicGeter.getMusicFile(res));
		try
		{
			line = (SourceDataLine) AudioSystem.getLine(new DataLine.Info( SourceDataLine.class, format));
		}catch(Exception e){
			ais = AudioSystem.getAudioInputStream( AudioFormat.Encoding.PCM_SIGNED, ais);
			frames = (int)ais.getFrameLength();
			format = ais.getFormat();
			line = (SourceDataLine) AudioSystem.getLine(new DataLine.Info( SourceDataLine.class, format));
		}
		line.open(format);
		line.close();
		ais.close();
		ais=null;
		line=null;
		
		
		t = new Tag(res);
		t.frames = frames;
		t.seconds = seconds;
	}
	public void resource(Tag cachedTag)
	{
		res = cachedTag.resource();
		frames = cachedTag.frames;
		seconds = cachedTag.seconds;
		t = cachedTag;
	}
	
	public void run()
	{
		try
		{
			while(runner!=null)
			{
				if(ais==null || current > pos)
				{
					ais = AudioSystem.getAudioInputStream(new File(res));
					format = ais.getFormat();
					buf = new byte[format.getFrameSize()];
					current = 0;
				}
				
				if(line==null)
				{
					try
					{
						line = (SourceDataLine) AudioSystem.getLine(new DataLine.Info( SourceDataLine.class, format));
					}catch(Exception e){
						ais = AudioSystem.getAudioInputStream( AudioFormat.Encoding.PCM_SIGNED, ais);
						frames = (int)ais.getFrameLength();
						format = ais.getFormat();
						seconds = (int)((float)frames / format.getFrameRate());
						
						buf = new byte[format.getFrameSize()];
						current = 0;
						line = (SourceDataLine) AudioSystem.getLine(new DataLine.Info( SourceDataLine.class, format));
					}
					line.open(format);
					line.start();
					vol = (FloatControl)line.getControl(FloatControl.Type.MASTER_GAIN);
					vol.setValue(l.volume());
				}
				
				while(pos > current)//skip
				{
					ais.skip(buf.length);
					current++;
				}
				
				ais.read(buf,0,buf.length);
				current++;
				line.write(buf,0,buf.length);
				pos++;
				l.frameCompleted(pos, this);
				
				if(pos==frames)
				{
					l.playbackCompleted(this);
					runner=null;
				}
				
				while(pause && runner!=null) synchronized(this) {wait();}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			l.error(ex,"Playback error");
		}
	}
	
	public synchronized void play()
	{
		if(runner==null) //stopped
		{
			pos = 0;
			pause = false;
			runner = new Thread(this);
			runner.start();
		}
		else if(pause) //paused
		{
			pause = false;
			notify();
		}
		else //playing
		{
			pos = 0;
		}
	}

	public synchronized void pause()
	{
		if(runner==null) //stopped
		{
		}
		else if(pause) //paused
		{
			pause = false;
			notify();
		}
		else //playing
		{
			pause = true;
		}
	}
	
	public synchronized void stop()
	{
		if(runner==null) //stopped
		{
		}
		else if(pause) //paused
		{
			runner = null;
			notify();
		}
		else //playing
		{
			runner = null;
		}
		
		pos = 0;
		pause = true;
		
		try{ais.close();}catch(Exception ex){}
		try{line.close();}catch(Exception ex){}
			
		ais = null;
		line = null;
		l.frameCompleted(0,this);
	}
	
	public synchronized void seek(int frame)
	{
		pos = frame;
	}	
	
	public void volume(int level)
	{
		if(vol!=null) vol.setValue(level);
	}
	
	public int framePosition() { return pos; }
	public int frameLength() { return frames; }
	
	public int timePosition()
	{
		return (int)((float)pos / format.getFrameRate());
	}
	public int timeLength()
	{
		return (int)seconds;
	}
	
	boolean isPlaying() {return !pause;}
	boolean isSeekable() {return true;}
	
	public Playlist listener() { return l; }
	public void listener(Playlist l) { this.l=l; }
	
	public Tag tag() {return t;}
	
	String[] formatExtensions(){
		return new String[]{".wav",".au",".aif",".aiff",".snd"};
	}
	
	String formatName(){
		try{
			if(aff==null)aff = AudioSystem.getAudioFileFormat(new File(res));
			return aff.getType().toString();
		}catch(Exception ex){
		}
		return "Error";
	}
}
