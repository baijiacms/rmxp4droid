 
package com.music.servequake.truemusic;

import java.io.*;
import java.util.*;

public class Library
{
	Collection<Tag> tags= new HashSet<Tag>();
	
	Audio audioFor(String res) throws Exception
	{
//		File f = new File(res);
//		if(!f.exists())
//		{
//			try{
//			//find current root and use in lieu
//			File c = new File("./").getCanonicalFile();
//			while(c.getParentFile()!=null) c = c.getParentFile();
//			f = new File(c,res.substring(res.indexOf("\\")+1));
//			if(!f.exists()) return null;
//			res = f.toString();
//			}catch(Exception e){}
//		}
	
	
		String x = res.toLowerCase();
		
		if(x.endsWith(".mp3"))
		{
			Tag t = tagFor(res);
			if(t!=null){
				Mp3Audio ma = new Mp3Audio();
				ma.resource(t);
				return ma;
			}else{
				Audio a = new Mp3Audio();
				a.resource(res);
				addTag(a.tag());
				return a;
			}
		}
		else if(x.endsWith(".wav") || x.endsWith(".aif") || x.endsWith(".aiff") || x.endsWith(".au"))
		{
			Tag t = tagFor(res);
			if(t!=null){
				JavaSoundAudio a = new JavaSoundAudio();
				a.resource(t);
				return a;
			}else{
				Audio a = new JavaSoundAudio();
				a.resource(res);
				addTag(a.tag());
				return a;
			}
		}
		else if(x.endsWith(".ogg") )
		{
		return null;
		}
		else if(x.endsWith(".flac") )
		{
			Tag t = tagFor(res);
			if(t!=null){
				NativeFlacAudio a = new NativeFlacAudio();
				a.resource(t);
				return a;
			}else{
				Audio a = new NativeFlacAudio();
				a.resource(res);
				addTag(a.tag());
				return a;
			}
		}
		else if(x.endsWith(".mid")||x.endsWith(".midi") )
		{
			Audio a = new MidiAudio();
			a.resource(res);
			return a;
		}
		return null;
	}
	
	Tag tagFor(String res)
	{
	
		for(Tag t: tags) if(	 new File(t.resource()).equals(new File(res))) return t;
		return null;
	}
	
	void addTag(Tag t)
	{
		if(tags.contains(t)) return;
		valid=false;
		tags.add(t);
	}
	
	File file(){
		return new File(TrueMusic.musicFolder(),".tmcache");
	}
	
	boolean valid=true;
	public void save(){
		if(valid)return;
		try{
			System.out.println("Library io");
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file()));
		
			for(Tag t: tags) oos.writeObject(t.toArray());
			oos.flush();
			oos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void load(){
		tags = new HashSet<Tag>();
		try{		
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file()));
		
			while(true){
				Tag t = new Tag();
				t.fromArray( (Object[]) ois.readObject() );
				if(!t.isValid()) {System.out.println("old tag");valid=false;}
				else if(tags.contains(t)) {System.out.println("duplicate tag");valid=false;}
				else tags.add(t); /////keep all for library??? but remove dead files
			}
		}catch(EOFException e){
		}catch(Exception e){
			e.printStackTrace();
		}	
		save();
	}
	
	Tag[] allSongs(){
		return tags.toArray(new Tag[tags.size()]);
	}
	
	String[] artists(){
		Set<String> a1 = new HashSet<String>();
		for(Tag t: tags) a1.add(t.libraryArtist());
		
		Vector<String> a2 = new Vector<String>();
		for(String artist: a1) a2.add(artist+" ("+songsForArtist(artist).length+")");
		
		Collections.sort(a2, alphabet);
		a2.add(0, "All "+a2.size()+" Artists ("+allSongs().length+")");
		return a2.toArray(new String[a2.size()]);
	}
	
	String[] albums()
	{
		Set<String> a1 = new HashSet<String>();
		for(Tag t: tags) a1.add(t.libraryAlbum());
		
		Vector<String> a2 = new Vector<String>();
		for(String album: a1) a2.add(album+" ("+songsForAlbum(album).length+")");
		
		Collections.sort(a2, alphabet);
		a2.add(0,"All "+a2.size()+" Albums ("+allSongs().length+")");
		return a2.toArray(new String[a2.size()]);
	}
	
	String[] albumsForArtist(String artist)
	{
		Set<String> a1 = new HashSet<String>();
		for(Tag t: tags) if(t.libraryArtist().equals(artist)) a1.add(t.libraryAlbum());
		
		Vector<String> a2 = new Vector<String>();
		for(String album: a1) a2.add(album+" ("+songsForArtistAndAlbum(artist,album).length+")");
		
		Collections.sort(a2, alphabet);
		a2.add(0,"All "+a2.size()+" Albums ("+songsForArtist(artist).length+")");
		return a2.toArray(new String[a2.size()]);
	}
	
	Tag[] songsForArtist(String artist)
	{
		Set<Tag> a1 = new HashSet<Tag>();
		for(Tag t: tags) if(t.libraryArtist().equals(artist)) a1.add(t);
		
		List<Tag> a2 = new ArrayList<Tag>(a1);
		Collections.sort(a2, ttrack);
		Collections.sort(a2, talbum);
		return a2.toArray(new Tag[a2.size()]);
	}
	
	Tag[] songsForAlbum(String album)
	{
		Set<Tag> a1 = new HashSet<Tag>();
		for(Tag t: tags) if(t.libraryAlbum().equals(album)) a1.add(t);
		
		List<Tag> a2 = new ArrayList<Tag>(a1);
		Collections.sort(a2, ttrack);
		return a2.toArray(new Tag[a2.size()]);
	}
	
	Tag[] songsForArtistAndAlbum(String artist, String album)
	{
		Set<Tag> a1 = new HashSet<Tag>();
		for(Tag t: tags) if(t.libraryArtist().equals(artist) && t.libraryAlbum().equals(album)) a1.add(t);
		
		List<Tag> a2 = new ArrayList<Tag>(a1);
		Collections.sort(a2, ttrack);
		return a2.toArray(new Tag[a2.size()]);
	}
	
	Comparator<String> alphabet = new Comparator<String>(){
		public int compare(String a, String b){
			return a.compareToIgnoreCase(b);
		}
	};
	Comparator<Tag> ttrack = new Comparator<Tag>(){
		public int compare(Tag a, Tag b){
			return new Integer("0"+a.track()).compareTo(new Integer("0"+b.track()));
		}
	};
	Comparator<Tag> talbum = new Comparator<Tag>(){
		public int compare(Tag a, Tag b){
			return a.libraryAlbum().compareTo(b.libraryAlbum());
		}
	};
}
