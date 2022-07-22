 
package com.music.servequake.truemusic;

import java.io.*;
import java.util.*;

public class Tag
{
	String resource="",title="",artist="",album="",track="";
	String genre="",lyrics="",year="",composer="",comment="";
	long mod;
	int frames;
	float seconds;
	
	public Tag(){}
	public Tag(String res)
	{
		resource=res;
		mod=new File(resource).lastModified();
	}
	
	public boolean isValid()
	{
		return new File(resource).lastModified() == mod;
	}
	
	public String title()
	{
		if(title.equals("")) return new File(resource).getName();
		else return title;
	}
	public void title(String x) { if(x!=null) title = x;}

	public String artist() {return artist;}
	public void artist(String x) { if(x!=null) artist = x;}
	public String libraryArtist(){return artist.equals("")?"Unknown":artist;}

	public String album() {return album;}
	public void album(String x) { if(x!=null) album = x;}
	public String libraryAlbum(){return album.equals("")?"Unknown":album;}
	
	public String track() {return track;}
	public void track(String x)
	{
		if(x!=null)
			if(x.equals("")) track = x;
			else track = new Integer(x).toString();
	}

	static String[][] codes = new String[][]{{"(0)","Blues"},{"(1)","Classic Rock"},{"(2)","Country"},{"(3)","Dance"},{"(4)","Disco"},{"(5)","Funk"},{"(6)","Grunge"},{"(7)","Hip-Hop"},{"(8)","Jazz"},{"(9)","Metal"},{"(10)","New Age"},{"(11)","Oldies"},{"(12)","Other"},{"(13)","Pop"},{"(14)","R&B"},{"(15)","Rap"},{"(16)","Reggae"},{"(17)","Rock"},{"(18)","Techno"},{"(19)","Industrial"},{"(20)","Alternative"},{"(21)","Ska"},{"(22)","Death Metal"},{"(23)","Pranks"},{"(24)","Soundtrack"},{"(25)","Euro-Techno"},{"(26)","Ambient"},{"(27)","Trip-Hop"},{"(28)","Vocal"},{"(29)","Jazz+Funk"},{"(30)","Fusion"},{"(31)","Trance"},{"(32)","Classical"},{"(33)","Instrumental"},{"(34)","Acid"},{"(35)","House"},{"(36)","Game"},{"(37)","Sound Clip"},{"(38)","Gospel"},{"(39)","Noise"},{"(40)","AlternRock"},{"(41)","Bass"},{"(42)","Soul"},{"(43)","Punk"},{"(44)","Space"},{"(45)","Meditative"},{"(46)","Instrumental Pop"},{"(47)","Instrumental Rock"},{"(48)","Ethnic"},{"(49)","Gothic"},{"(50)","Darkwave"},{"(51)","Techno-Industrial"},{"(52)","Electronic"},{"(53)","Pop-Folk"},{"(54)","Eurodance"},{"(55)","Dream"},{"(56)","Southern Rock"},{"(57)","Comedy"},{"(58)","Cult"},{"(59)","Gangsta"},{"(60)","Top 40"},{"(61)","Christian Rap"},{"(62)","Pop/Funk"},{"(63)","Jungle"},{"(64)","Native American"},{"(65)","Cabaret"},{"(66)","New Wave"},{"(67)","Psychadelic"},{"(68)","Rave"},{"(69)","Showtunes"},{"(70)","Trailer"},{"(71)","Lo-Fi"},{"(72)","Tribal"},{"(73)","Acid Punk"},{"(74)","Acid Jazz"},{"(75)","Polka"},{"(76)","Retro"},{"(77)","Musical"},{"(78)","Rock & Roll"},{"(79)","Hard Rock"},{"(80)","Folk"},{"(81)","Folk-Rock"},{"(82)","National Folk"},{"(83)","Swing"},{"(84)","Fast Fusion"},{"(85)","Bebob"},{"(86)","Latin"},{"(87)","Revival"},{"(88)","Celtic"},{"(89)","Bluegrass"},{"(90)","Avantgarde"},{"(91)","Gothic Rock"},{"(92)","Progressive Rock"},{"(93)","Psychedelic Rock"},{"(94)","Symphonic Rock"},{"(95)","Slow Rock"},{"(96)","Big Band"},{"(97)","Chorus"},{"(98)","Easy Listening"},{"(99)","Acoustic"},{"(100)","Humour"},{"(101)","Speech"},{"(102)","Chanson"},{"(103)","Opera"},{"(104)","Chamber Music"},{"(105)","Sonata"},{"(106)","Symphony"},{"(107)","Booty Bass"},{"(108)","Primus"},{"(109)","Porn Groove"},{"(110)","Satire"},{"(111)","Slow Jam"},{"(112)","Club"},{"(113)","Tango"},{"(114)","Samba"},{"(115)","Folklore"},{"(116)","Ballad"},{"(117)","Power Ballad"},{"(118)","Rhythmic Soul"},{"(119)","Freestyle"},{"(120)","Duet"},{"(121)","Punk Rock"},{"(122)","Drum Solo"},{"(123)","A capella"},{"(124)","Euro-House"},{"(125)","Dance Hall"}};
	
	public String genre() {return genre;}
	public void genre(String x) { 
		if(x!=null) genre = x;
		for(String[] code: codes)
			if(genre.equals(code[0])) genre = code[1];
	}

	public String lyrics() {return lyrics;}
	public void lyrics(String x) { if(x!=null) lyrics = x;}

	public String composer() {return composer;}
	public void composer(String x) { if(x!=null) composer = x;}
	
	public String comment() {return comment;}
	public void comment(String x) { if(x!=null) comment = x;}
	
	public String year() {return year;}
	public void year(String x) { if(x!=null) year = x;}
	
	public String resource() {return resource;}
	public void resource(String x) {resource = x;}

	public String targetPath() {return libraryArtist() + "/" + libraryAlbum() + "/" + title() + ".mp3";}
	
	public String toString()
	{
		if(title.equals("")) return new File(resource).getName();
		
		String x = title;
		if(!artist.equals("")) x += " by " + artist;
		if(!album.equals("")) x += " from " + album;
		if(!year.equals("")) x += " (" + year + ")";
		return x;
	}
	
	boolean isTagged()
	{
		return !title.trim().equals("");
	}
	
	public boolean equals(Object o)
	{
		if(o instanceof Tag) return new File(((Tag)o).resource).equals(new File(resource)) ;
		return false;
	}
	
	public Object[] toArray()
	{
		Object[] o = new Object[13];
		o[0] = resource;
		o[1] = title;
		o[2] = artist;
		o[3] = album;
		o[4] = track;
		o[5] = genre;
		o[6] = lyrics;
		o[7] = year;
		o[8] = composer;
		o[9] = comment;
		o[10] = mod;
		o[11] = frames;
		o[12] = seconds;
		return o;
	}
	
	public void fromArray(Object[] o)
	{
		resource = (String) o[0];
		title = (String) o[1];
		artist = (String) o[2];
		album = (String) o[3];
		track = (String) o[4];
		genre = (String) o[5];
		lyrics = (String) o[6];
		year = (String) o[7];
		composer = (String) o[8];
		comment = (String) o[9];
		mod = (Long) o[10];
		frames = (Integer) o[11];
		seconds = (Float) o[12];
	}
}