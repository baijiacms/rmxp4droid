 
package com.music.servequake.truemusic;

public abstract class Audio
{
	public String resource() { return "";}
	public void resource(String res) throws Exception {} //count frames, read tag; throw exception if cannot play resource
	public void resource(Tag cachedTag) {} 
		
	public void play() {} //if stopped, play; if paused, unpause; if playing, restart //Audios should acquire resource on play
	public void pause() {} //if stoped, ignore; if paused, unpause; if playing, pause  //Audios in pause may keep resources open
	public void stop() {} //stop //Audios are expected to release resources on stop

	public void seek(int frame) {} 
	
	public void volume(int level){}
	
	public int framePosition() {return 0;}
	public int frameLength() {return 0;} 
	public int timePosition() {return 0;} //in seconds
	public int timeLength() {return 0;}
	
	boolean isPlaying() {return false;}
	boolean isSeekable() {return false;}
	boolean isTaggable() {return false;}

	public Playlist listener() { return null; }
	public void listener(Playlist p) {}
	
	public Tag tag() {return new Tag(resource());}
	public void tag(Tag t) throws Exception {}

	String[] formatExtensions(){ return new String[]{""}; }
	String formatName(){return "Undefined";}
	
	public String toString() { return new java.io.File(resource()).getName(); }
	public String displayString() { return tag().toString(); }
	
}
