 
package com.music.servequake.truemusic;

import java.io.FileInputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

import com.music.kc7bfi.jflac.FLACDecoder;
import com.music.kc7bfi.jflac.PCMProcessor;
import com.music.kc7bfi.jflac.frame.Frame;
import com.music.kc7bfi.jflac.metadata.StreamInfo;
import com.music.kc7bfi.jflac.util.ByteData;
import com.rmxp4droid.pub.component.MusicGeter;


public class NativeFlacAudio extends Audio implements Runnable,PCMProcessor
{
	int frames = 0; //number of frames in the clip
	int pos = 0; //the next frame to play
	int current = 0; //the next frame in the decoder
	boolean pause = true;
	Thread runner;
	FLACDecoder dec;
	String res;
	Playlist l;
	Tag t;
	FloatControl vol;
	SourceDataLine line;
	AudioFormat af;
	
	float seconds;
	
	public String resource(){return res;}
	public void resource(String res) throws Exception
	{
		this.res = res;
		t = new Tag(res);
		
		frames = 0;
		dec = new FLACDecoder(MusicGeter.getMusicFile(res));
		dec.readMetadata();
		
		while(!dec.isEOF())
		{
			dec.readNextFrame();
			frames++;
		}
		//dec.close()?????????????????????
		long sampleLength = dec.getSamplesDecoded();
		af = dec.getStreamInfo().getAudioFormat();
		dec = null;
		
		seconds = (float)sampleLength / af.getFrameRate();
		
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
			while(runner==Thread.currentThread())
			{
				if(dec==null || current > pos)
				{
					dec = new FLACDecoder(new FileInputStream(res));
					dec.addPCMProcessor(this);
					dec.readMetadata();
					current = 0;
				}
				
				while(pos > current)
				{
					dec.readNextFrame();
					current++;
				}
				
				Frame f = dec.readNextFrame();
				current++;
				if(f==null)
				{
					l.playbackCompleted(this);
					runner=null;
				}
				else dec.callPCMProcessors(f);
				
				while(pause && runner!=null) synchronized(this) {wait();}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			l.error(e,"Playback error");
		}
	}
	
	public void processStreamInfo(StreamInfo streamInfo)
	{
        af = streamInfo.getAudioFormat();
	}
	
	public void processPCM(ByteData pcm)
	{
		try
		{
			if(line==null)
			{
				line = (SourceDataLine) AudioSystem.getLine(new DataLine.Info(SourceDataLine.class, af));
	            line.open(af);
				line.start();
				
				vol = (FloatControl)line.getControl(FloatControl.Type.MASTER_GAIN);
				vol.setValue(l.volume());
			}
			
	        line.write(pcm.getData(), 0, pcm.getLen());
			pos++;
			l.frameCompleted(pos,this);
		}
		catch(Exception e){ e.printStackTrace();}
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
		//close dec
		dec = null;
		l.frameCompleted(0,this);
	}
	
	public synchronized void seek(int frame)
	{
		this.pos = frame;
	}
	
	public void volume(int level)
	{
		if(vol!=null) vol.setValue(level);
	}
	
	public int framePosition() { return pos; }
	public int frameLength() { return frames; }
	
	public int timePosition()
	{
		if(dec==null)return 0;
		return (int)((float)dec.getSamplesDecoded() / af.getFrameRate());
	}
	public int timeLength()
	{
		return (int)seconds;
	}
	
	public boolean isPlaying(){return !pause;}
	public boolean isSeekable() {return true;}
	
	public Playlist listener() { return l; }
	public void listener(Playlist l) { this.l=l; }

	public Tag tag() {return t;}

	public String formatName(){return "Native FLAC";}
	public String[] getExtensions(){return new String[]{".flac"};}
}