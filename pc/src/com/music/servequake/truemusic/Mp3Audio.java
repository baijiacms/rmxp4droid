
package com.music.servequake.truemusic;


import java.io.File;

import com.music.farng.mp3.AbstractMP3Tag;
import com.music.farng.mp3.MP3File;
import com.music.javazoom.jl.player.advanced.AdvancedPlayer;
import com.rmxp4droid.pub.component.MusicGeter;

//inline AdvancedPlayer in; replace to use SourceDataLine line here also
public class Mp3Audio extends Audio implements Runnable
{
	int frames = 0; //number of frames in the clip
	int pos = 0; //the next frame to play
	int current = 0; //the next frame in the decoder
	boolean pause = true;
	Thread runner;
	AdvancedPlayer p; //the mp3 decoder
	String res;
	Playlist l;
	Tag t;
	float seconds;

	public String resource() { return res; }
	public void resource(String res) throws Exception
	{
		this.res = res;
		
		p = new AdvancedPlayer( MusicGeter.getMusicFile(res));
		while(p.skipFrame()) {frames++;}
		seconds = ((float)frames) * p.msPerFrame() / 1000.0f;
		p.close();
		p = null;
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
				if(p==null || current > pos)
				{
					p = new AdvancedPlayer( MusicGeter.getMusicFile(res) );
					current = 0;
				}
				
				while(pos > current)
				{
					p.skipFrame();
					current++;
				}
				
				com.music.javazoom.jl.player.JavaSoundAudioDevice.vol.setValue(l.volume());
		try{	p.decodeFrame();	}catch(ArrayIndexOutOfBoundsException ex){}catch(com.music.javazoom.jl.decoder.JavaLayerException ex){}
				current++;
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
		if(p!=null) p.close();
		p = null;
		l.frameCompleted(0,this);
	}
	
	public synchronized void seek(int frame)
	{
		pos = frame;
	}
	
	public void volume(int level)
	{
		if(com.music.javazoom.jl.player.JavaSoundAudioDevice.vol!=null)
		com.music.javazoom.jl.player.JavaSoundAudioDevice.vol.setValue(level);
	}
	
	
	public int framePosition() { return pos; }
	public int frameLength() { return frames; }	

	public int timePosition()
	{
		if(p==null)return 0;
		return (int)((float)pos * p.msPerFrame() / 1000.0f );
	}
	public int timeLength()
	{
		return (int)seconds;
	}
	
	public boolean isPlaying(){return !pause;}
	public boolean isSeekable() {return true;}
	public boolean isTaggable() {return true;}
	
	public Playlist listener() { return l; }
	public void listener(Playlist l) { this.l=l; }
	
	public Tag tag()
	{
		if(t==null)
		{	
			t = new Tag(res);
			t.frames = frames;
			t.seconds = seconds;
			
			try
			{
				MP3File f = new MP3File( new File(res), false );
				AbstractMP3Tag tag = null;
		
				if(f.hasID3v1Tag()) tag = f.getID3v1Tag();
				if(f.hasID3v2Tag()) tag = f.getID3v2Tag();

				if(tag!=null){
					t.title( tag.getSongTitle() );
					t.artist( tag.getLeadArtist() );
					t.album( tag.getAlbumTitle() );
					t.track( tag.getTrackNumberOnAlbum() );
					t.genre( tag.getSongGenre() );
					t.lyrics( tag.getSongLyric() );
					t.year( tag.getYearReleased() );
					t.composer( tag.getAuthorComposer() );
					t.comment( tag.getSongComment() );
				}
			}
			catch(Exception e){}
		}
		return t;
	}
	
	public void tag(Tag t) throws Exception
	{
		MP3File f = new MP3File( new File(res), false );
		AbstractMP3Tag tag = null;

		if(f.hasID3v1Tag()) tag = f.getID3v1Tag();
		if(f.hasID3v2Tag()) tag = f.getID3v2Tag();
		if(tag==null) tag = new com.music.farng.mp3.id3.ID3v2_2();

		tag.setSongTitle( t.title() );
		tag.setLeadArtist( t.artist() );
		tag.setAlbumTitle( t.album() );
		tag.setTrackNumberOnAlbum( t.track() );
		tag.setSongGenre( t.genre() );
		tag.setSongLyric( t.lyrics() );
		tag.setYearReleased( t.year() );
		tag.setAuthorComposer( t.composer() );
		tag.setSongComment( t.comment() );
		
		f.save();
		this.t = t;
	}
	
	public String formatName(){return "mp3";}
	public String[] getExtensions(){return new String[]{".mp3"};}
}