 
package com.music.servequake.truemusic;

import java.awt.*;
import java.awt.event.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import java.io.*;

import com.music.farng.mp3.*;

public class AutoOrganize extends JFrame implements ActionListener
{
	JTextField searchRoot = new JTextField("<all>",12);
	JButton choose = new JButton("...");
	JButton go = new JButton("AutoOrganize");
	JTextArea progress = new JTextArea("Moves music under the search root to <target root>/Artist/Album/Title.mp3\nTries to tag untagged music from the filename.\nReplaces duplicates with the larger file (assumed higher quality).\n*WARNING*: Still a new feature; backup any music you would\n be unable to replace before using Auto-Organize.\n\n");
	File[] roots = File.listRoots();
	File targetRoot;
	JTextField targetBox = new JTextField("",12);
	JButton chooseT = new JButton("...");
	
	TrueMusic tm;
	Library l;
	AutoOrganize(TrueMusic tm)
	{
		super("Auto-Organize");
		this.tm=tm;
		l=tm.l;
		
		targetRoot = tm.musicFolder();
		targetBox.setText(targetRoot.toString());
		
		JPanel top = new JPanel();
		top.add(new JLabel("Search root: "));
		top.add(searchRoot);
		top.add(choose);
		top.add(new JLabel("Target root: "));
		top.add(targetBox);
		top.add(chooseT);
		top.add(go);
		
		String x="";
				for(File f:roots) x+=f.toString()+" ";
				searchRoot.setText(x);
				
		choose.addActionListener(this);
		chooseT.addActionListener(this);
		go.addActionListener(this);
		
		searchRoot.setEnabled(false);
		targetBox.setEnabled(false);
		
		add(top,BorderLayout.NORTH);
		add(new JScrollPane(progress));

		setSize(600,400);
		setVisible(true);
	}

	ArrayList<File> files = new ArrayList<File>();
	ArrayList<File> welltagged = new ArrayList<File>();
	ArrayList<File> taggedfromname = new ArrayList<File>();
	ArrayList<File> untaggable = new ArrayList<File>();
	ArrayList<File> unmovable = new ArrayList<File>();
	ArrayList<File> moved = new ArrayList<File>();
	
	Tag filenameTagFor(File f)
	{
		Tag t = new Tag();
		
		String title="",artist="",album="",track="";
		String name = f.getName();
		name = name.substring(0,name.length()-4);
		
		java.util.List<String> tokens = new ArrayList<String>(Arrays.asList( name.split("-")));
		
		for(String x: tokens)
		{
			try{
				new Integer(x.trim());
				track = x;
			}catch(Exception e){}
		}
		tokens.remove(track);
		
		if(tokens.size()==3)
		{
			artist = tokens.get(0);
			album = tokens.get(1);
			title = tokens.get(2);
		}
		else if(tokens.size()==2)
		{
			artist = tokens.get(0);
			title = tokens.get(1);
		}
		else if(tokens.size()==1)
		{
			title = tokens.get(0);
		}
		else return null;
		
		t.title(title.trim());
		t.artist(artist.trim());
		t.album(album.trim());
		t.track(track.trim());
		return t;
	}
	
	void append(String x)
	{
		progress.setText(progress.getText()+x+"\n");
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==choose)
		{
			final JFileChooser chooser = new JFileChooser();
			chooser.setDragEnabled(true);
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setMultiSelectionEnabled(true);

			if(chooser.showDialog(this, "Choose Search Root" ) == JFileChooser.APPROVE_OPTION){
				roots = chooser.getSelectedFiles();
				
				String x="";
				for(File f:roots) x+=f.toString()+" ";
				searchRoot.setText(x);
	    	}
		}
		else if(e.getSource()==chooseT)
		{
			final JFileChooser chooser = new JFileChooser();
			chooser.setDragEnabled(true);
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setMultiSelectionEnabled(false);

			if(chooser.showDialog(this, "Choose Target Root" ) == JFileChooser.APPROVE_OPTION){
				targetRoot = chooser.getSelectedFile();
				targetBox.setText(targetRoot.toString());
	    	}
		}
		else if(e.getSource()==go)
		{
			go.setEnabled(false);
			new Thread(){public void run(){
			go();}}.start();
		}
	}
	
	
	void go()
	{	
		append("Building index");
		doFiles(roots);
		
		append("Found "+files.size()+" canidate music files\n");
		
		l.load();
		
		File root = TrueMusic.musicFolder();
		for(File f:files)
		{
			try{
			Mp3Audio a = new Mp3Audio();
			a.res=f.toString();
			Tag t= a.tag();
			if(t.isTagged())
			{
				welltagged.add(f);
			}
			else
			{
				t = filenameTagFor(f);
				
				if(t==null)
				{
					untaggable.add(f);
					append("untaggable: "+f);
					continue;
				}else{
					append("Guessed filename tag from "+f+" as");
					append("Title:"+t.title());
					append("Arist:"+t.artist());
					append("Album:"+t.album());
					append("Track#:"+t.track()+"\n");
					taggedfromname.add(f);
					a.tag(t);
				}
			}
			
			File newf = new File(targetRoot,t.targetPath());
		
			if(!newf.equals(f))
			{
				if(newf.exists())
				{
					append("targetalreadyexists");
					if(newf.length()>f.length())
					{
						f.delete();
						append("removed smaller duplicate");
						moved.add(f);
					}
					else
					{
						//DOMOVE
						newf.delete();
						f.renameTo(newf);
						t.resource(newf.toString());
						append("overwrote smaller duplicate");
						moved.add(f);
					}
				}
				else
				{
					
					try{
						newf.getParentFile().mkdirs();
						boolean b = f.renameTo(newf);
						if(!b) throw new Exception("failed move");
						t.resource(newf.toString());
						append("moved "+f+" to "+newf);
						moved.add(f);
					}catch(Exception ex){
						append("cannot move "+f+" to "+newf);
						append(""+ex);
						unmovable.add(f);
					}
				}
			}
			else append("already in place");
			
			}catch(Exception ex){ex.printStackTrace();append(""+ex);}
		}	
		l.save();
		
		append("welltaggedfiles "+welltagged.size());
		append("taggedfromfilename "+taggedfromname.size());
		append("untaggable "+untaggable.size());
		append("unmovable "+unmovable.size());
		append("moved "+moved.size());
	}
	
	void doFiles(File[] fs){
		if(fs!=null)
		for(File f:fs){
			if(f.isDirectory() && !f.getName().equals("WINDOWS") && !f.getName().equals("Program Files"))
			{	
				doFiles(f.listFiles());	continue;
			}
			if(f.length()<1024*1024) continue;
			if(f.getName().toLowerCase().endsWith(".mp3"))
				files.add(f);
		}
	}
}