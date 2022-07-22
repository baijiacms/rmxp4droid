 
package com.music.servequake.truemusic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.rmxp4droid.pub.component.MusicGeter;
import com.rmxp4droid.pub.control.interfaces.Rmxp4droidSound;

public class Playlist implements Iterable<Audio>, TableModel,Rmxp4droidSound
{
	ArrayList<Audio> tracks = new ArrayList<Audio>();
	Audio current = null;
	
	boolean random = false;
	boolean repeat = false;
	Library l = new Library();
	int volume = -20;
	
	String name = "Playlist";
//	TrueMusic tm;
	
	public String getStatus()
	{
		double time = 0.0; String unit = "seconds";
		for(Audio a:tracks) time=time+a.timeLength();
		
		if(time>60) {time=time/60.0; unit = "minutes";
			if(time>60) {time=time/60.0; unit = "hours";
				if(time>24) {time=time/24.0; unit = "days";
				}
			}
		}
		time = ((int)(time*10.0))/10.0;
		
		double size = 0.0; String sunit = "B";
		for(Audio a:tracks) size+= new File(a.resource()).length();
		if(size>1024){ size=size/1024.0; sunit = "KiB";
			if(size>1024){ size=size/1024.0; sunit = "MiB";
				if(size>1024){ size=size/1024.0; sunit = "GiB";
				}
			}
		}
		size = ((int)(size*10.0))/10.0;
		
		return ""+tracks.size()+" songs, "+time+" "+unit+", "+size+" "+sunit;
	}
	
	public Playlist(String name, TrueMusic tm)
	{
		this.name = name;
//		this.tm = tm;
	}
	public Playlist(String name)
	{
		
		this.name = name;
//		this.tm = tm;
	}
	public boolean equals(Object o)
	{
		if(o instanceof Playlist) return name.equals(((Playlist)o).name);
		return false;
	}
	
	public String toString()
	{
		return name;
	}
	
	public void play()
	{
		if(current!=null) current.play();
	}
	
	public void pause()
	{
		if(current!=null) current.pause();
	}
	
	public void stop()
	{
		if(current!=null) current.stop();
	}
	
	public void next()
	{
		if(tracks.size()==0)
		{
			current = null;
			changed();
			return;
		}
		
		int next = tracks.indexOf(current);
		
		if(random)
		{
			int r = (int) (Math.random()*tracks.size());
			if(r==next) next++;
			else next = r;
		}
		else next++;
		
		if( next>=tracks.size() )
		{
			if(repeat) next = 0;
			else 
			{
				stop();
				return;
			}
		}

		stop();
		current = tracks.get(next);
		play();

		changed();
	}

	public void previous()
	{
		if(tracks.size()==0)
		{
			current = null;
			changed();
			return;
		}

		int next = tracks.indexOf(current);
		next--;

		if( next<0 ) return;

		stop();
		current = tracks.get(next);
		play();

		changed();
	}

	public void volume(int level)
	{
		level=1;
		volume = level;
		if(current!=null) current.volume(level);
	}

	public void seek(int frame)
	{
		if(current!=null) current.seek(frame);
	}

	public void setTrack(int pos)
	{
		if(tracks.size()==0)
		{
			current = null;
			changed();
			return;
		}

		if( pos<0 || pos>=tracks.size() ) return;

		stop();
		current = tracks.get(pos);
		play();

		changed();
	}

	public void setTrack(Audio a)
	{
		setTrack( tracks.indexOf(a) );
	}

	public void playLast()
	{
		setTrack( tracks.size()-1 );
	}
	
	public void setRandom(boolean flag)
	{
		random = flag;
	}

	public void setRepeat(boolean flag)
	{
		repeat = flag;
	}

	public File file(){
		return new File(TrueMusic.musicFolder(),name+".m3u");
	}
	
	public void load() throws Exception {
		addTrack( file().toString() );
	}

	public void save()
	{
		try{
			System.out.println("Playlist io");
		
			OutputStream os = new FileOutputStream( file() );
			PrintStream out = new PrintStream(os);
			for(Audio a:tracks) out.println( a.resource() );
			out.close();
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public void delete(){
		file().delete();
	}
	public void play(String name)
	{

		this.stop();
		try {
			addTrack( name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.play();
	}
	public void addTrack(String res) throws Exception
	{
		File musicFIle=MusicGeter.searchAudio(res);
		if(musicFIle==null)
		{
			return;
		}
		res=musicFIle.getPath();
		this.clear(); 
	
		
		Audio a = l.audioFor(res);
		if(a==null)return;
		a.listener(this);
		tracks.add(a);
		if(current==null) current = a;
		changed();
	}
	
	public void clear()
	{
		stop();
		tracks = new ArrayList<Audio>();
		current = null;
		changed();
//		tm.playlistChanged();
	}
	
	public void removeTrack(int pos)
	{
		if(pos<0 || pos>=tracks.size()) return;

		Audio dead = tracks.get(pos);
		if(current==dead) next();

		tracks.remove(dead);
		changed();
//		tm.playlistChanged();
	}

	public void removeTrack(Audio a)
	{
		removeTrack( tracks.indexOf(a) );
	}
	
	public int currentIndex()
	{
		return tracks.indexOf(current);
	}
	
	public Audio getTrack(int index)
	{
		return tracks.get(index);
	}
	
//////////////////////////////

	public void withTags(Tag[] ts)
	{
		stop();
		tracks.clear();
		current=null;
		
		//add tracks for tags
		for(Tag t: ts)
		{
			try
			{
				addTrack(t.resource());
			}
			catch(Exception e){e.printStackTrace();}
		}
	}
	
//////////////////////////////

	public Iterator<Audio> iterator()
	{
		return tracks.iterator();
	}
	
	public ArrayList<Audio> tracks()
	{
		return tracks;
	}
	public void tracks(ArrayList<Audio> tracks)
	{
		this.tracks = tracks;
		changed();
	}

///////////////////

	public void frameCompleted(int frame, Audio a)
	{
//		if(current == a)
//		{
//			tm.frameCompleted(frame);
//			tm.time( a.timePosition(), a.timeLength()-a.timePosition() );
//		}
	}

	public void playbackCompleted(Audio a)
	{
		if(current == a) next();
	}

	public void error(Throwable t, String desc)
	{
//		tm.error(t,desc);
	}
	
	public int volume()
	{
		return volume;
	}

/////////////////////////////////////
	
	void changed()
	{
		for(TableModelListener tml:tmls) tml.tableChanged( new TableModelEvent(this) );
//		tm.updateCurrent(current,this);
	}
	
	ArrayList<TableModelListener> tmls = new ArrayList<TableModelListener>();
	
	public void addTableModelListener(TableModelListener l)
	{
		tmls.add(l);
	}
	public void removeTableModelListener(TableModelListener l)
	{
		tmls.remove(l);
	}
	
	public Class<?> getColumnClass(int columnIndex) {if(columnIndex==3)return Integer.class; return String.class;}
	
	public int getColumnCount(){return 6;}
	
	static String[] col = {"Title","Artist","Album","Track","Genre","Format"};
	
	public String getColumnName(int index)
	{
		return col[index];
	}
	
	public int getRowCount()
	{
		return tracks.size();
	}
	
	public Object getValueAt(int row, int col)
	{
		Audio a = tracks.get(row);
		Tag t = a.tag();
		if(col==0) return t.title();
		if(col==1) return t.artist();
		if(col==2) return t.album();
		if(col==3) return (t.track.equals("")?0:new Integer(t.track()));
		if(col==4) return t.genre();
		if(col==5) return a.formatName(); 
		
		return "Invalid column";
	}
	
	//TODO: tag editing avaible here?
	public boolean isCellEditable(int row, int col) {return false;}
	public void setValueAt(Object aValue, int row, int col) {}
}
