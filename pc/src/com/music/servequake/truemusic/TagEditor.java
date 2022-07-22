
package com.music.servequake.truemusic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

class TagEditor extends JFrame implements ActionListener,ListSelectionListener,KeyListener,ChangeListener
{
	JList list;
	ArrayList<Audio> audios = new ArrayList<Audio>();

	JLabel titlel = new JLabel("Title");
	JLabel artistl = new JLabel("Artist");
	JLabel albuml = new JLabel("Album");
	JLabel trackl = new JLabel("Track#");
	JLabel yearl = new JLabel("Year");
	JLabel genrel = new JLabel("Genre");
	JLabel composerl = new JLabel("Composer");
	JLabel commentl = new JLabel("Comment");
	JLabel lyricsl = new JLabel("Lyrics");
	
	JCheckBox titleb = new JCheckBox();
	JCheckBox artistb = new JCheckBox();
	JCheckBox albumb = new JCheckBox();
	JCheckBox trackb = new JCheckBox();
	JCheckBox yearb = new JCheckBox();
	JCheckBox genreb = new JCheckBox();
	JCheckBox composerb = new JCheckBox();
	JCheckBox commentb = new JCheckBox();
	JCheckBox lyricsb = new JCheckBox();
	
	JLabel file = new JLabel();
	JTextField title = new JTextField();
	JTextField artist = new JTextField();
	JTextField album = new JTextField();
	JTextField track = new JTextField(3);
	JTextField year = new JTextField(5);
	JTextField genre = new JTextField();
	JTextField composer = new JTextField();
	JTextField comment = new JTextField();
	JTextArea lyrics = new JTextArea();
	JScrollPane lyricsp = new JScrollPane(lyrics);

	JButton update = new JButton("Update");
	JButton revert = new JButton("Revert");
	JButton cancel = new JButton("Cancel");
	JButton freedb = new JButton("FreeDB");

	TagEditor(Audio a, Playlist pp)
	{
		super("Tag Editor");
		audios.add(a);
		
		Vector v = new Vector();for(Audio aa:pp)v.add(aa);
		list = new JList(v);
		list.addListSelectionListener(this);
		
		title.addKeyListener(this);
		artist.addKeyListener(this);
		album.addKeyListener(this);
		track.addKeyListener(this);
		year.addKeyListener(this);
		genre.addKeyListener(this);
		composer.addKeyListener(this);
		comment.addKeyListener(this);
		lyrics.addKeyListener(this);
		
		titleb.addChangeListener(this);
		artistb.addChangeListener(this);
		albumb.addChangeListener(this);
		trackb.addChangeListener(this);
		yearb.addChangeListener(this);
		genreb.addChangeListener(this);
		composerb.addChangeListener(this);
		commentb.addChangeListener(this);
		lyricsb.addChangeListener(this);
		
		//update.setEnabled(false);
		//revert.setEnabled(false);
		freedb.setEnabled(false);freedb.setVisible(false);
		
		JPanel p = new JPanel();
		
		GroupLayout layout = new GroupLayout(p);
		p.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//reminds me a bit of Smalltalk :D
		layout.setHorizontalGroup(
			layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addComponent(titlel)
				.addComponent(artistl)
				.addComponent(albuml)
				.addComponent(trackl)
				.addComponent(composerl)
				.addComponent(commentl)
				.addComponent(lyricsl)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(titleb)
				.addComponent(artistb)
				.addComponent(albumb)
				.addComponent(trackb)
				.addComponent(composerb)
				.addComponent(commentb)
				.addComponent(lyricsb)
			)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(file)
				.addComponent(title)
				.addComponent(artist)
				.addComponent(album)
				.addGroup(layout.createSequentialGroup()
					.addComponent(track,  GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
          GroupLayout.PREFERRED_SIZE)
					.addComponent(yearl)
					.addComponent(yearb)
					.addComponent(year,  GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
          GroupLayout.PREFERRED_SIZE)
					.addComponent(genrel)
					.addComponent(genreb)
					.addComponent(genre)
				)
				.addComponent(composer)
				.addComponent(comment)
				.addComponent(lyricsp)
				.addGroup(layout.createSequentialGroup()
					.addComponent(update)
					.addComponent(revert)
					.addComponent(freedb)
					.addComponent(cancel)
				)
			)
		);
		layout.setVerticalGroup(
			layout.createSequentialGroup()
			.addComponent(file)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(titlel)
				.addComponent(titleb)
				.addComponent(title)
			)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(artistl)
				.addComponent(artistb)
				.addComponent(artist)
			)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(albuml)
				.addComponent(albumb)
				.addComponent(album)
			)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(trackl)
				.addComponent(trackb)
				.addComponent(track, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(yearl)
				.addComponent(yearb)
				.addComponent(year, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(genrel)
				.addComponent(genreb)
				.addComponent(genre)
			)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(composerl)
				.addComponent(composerb)
				.addComponent(composer)
			)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(commentl)
				.addComponent(commentb)
				.addComponent(comment)
			)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(lyricsl)
				.addComponent(lyricsb)
				.addComponent(lyricsp)
			)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(update)
				.addComponent(revert)
				.addComponent(freedb)
				.addComponent(cancel)
			)
		);
		
		redoCheckboxes();
		reset();
		
		update.addActionListener(this);
		cancel.addActionListener(this);
		revert.addActionListener(this);
		freedb.addActionListener(this);
		
		add(new JScrollPane(list),BorderLayout.WEST);
		add(p);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(600,400);
		setVisible(true);
	}
	
	public void stateChanged(ChangeEvent e) {
		redoCheckboxes();
	}

	void redoCheckboxes()
	{
		boolean b = audios.size() > 1;
		titleb.setVisible(b);
		artistb.setVisible(b);
		albumb.setVisible(b);
		trackb.setVisible(b);
		yearb.setVisible(b);
		genreb.setVisible(b);
		commentb.setVisible(b);
		composerb.setVisible(b);
		lyricsb.setVisible(b);
		
		boolean one = audios.size()==1;
		title.setEnabled(one||titleb.isSelected());
		artist.setEnabled(one||artistb.isSelected());
		album.setEnabled(one||albumb.isSelected());
		track.setEnabled(one||trackb.isSelected());
		year.setEnabled(one||yearb.isSelected());
		genre.setEnabled(one||genreb.isSelected());
		composer.setEnabled(one||composerb.isSelected());
		comment.setEnabled(one||commentb.isSelected());
		lyrics.setEnabled(one||lyricsb.isSelected());
	}
	
	public void keyTyped(KeyEvent e){changed(true);}
	public void keyReleased(KeyEvent e){changed(true);}
	public void keyPressed(KeyEvent e){changed(true);}
	
	
	void changed(boolean b)
	{
		update.setEnabled(b);
		revert.setEnabled(b);
	}
	
	public void valueChanged(ListSelectionEvent e)
	{
		audios = new ArrayList<Audio>();
		for(Object o:list.getSelectedValues()) audios.add((Audio)o);
		redoCheckboxes();
		reset();
	}
	
	void textChanged()
	{
		changed(true);
	}
	
	void reset()
	{
		if(audios.size() > 1)
		{
			String stitle=null,sartist=null,salbum=null,strack=null,sgenre=null,syear=null,scomposer=null,scomment=null,slyrics=null;
			boolean btitle,bartist,balbum,btrack,bgenre,byear,bcomposer,bcomment,blyrics;
			
			for(Audio a: audios){
				Tag t = a.tag();
				
				if(stitle==null) stitle = t.title();
				if(!stitle.equals(t.title())) {btitle=true; stitle="(Multiple values)";}
				
				if(sartist==null) sartist = t.artist();
				if(!sartist.equals(t.artist())) {bartist=true; sartist="(Multiple values)";}
				
				if(salbum==null) salbum = t.album();
				if(!salbum.equals(t.album())) {balbum=true; salbum="(Multiple values)";}
				
				if(strack==null) strack = t.track();
				if(!strack.equals(t.track())) {btrack=true; strack="(Multiple values)";}
				
				if(syear==null) syear = t.year();
				if(!syear.equals(t.year())) {byear=true; syear="(Multiple values)";}
				
				if(sgenre==null) sgenre = t.genre();
				if(!sgenre.equals(t.genre())) {bgenre=true; sgenre="(Multiple values)";}
				
				if(scomposer==null) scomposer = t.composer();
				if(!scomposer.equals(t.composer())) {bcomposer=true; scomposer="(Multiple values)";}
				
				if(scomment==null) scomment = t.comment();
				if(!scomment.equals(t.comment())) {bcomment=true; scomment="(Multiple values)";}
				
				if(slyrics==null) slyrics = t.lyrics();
				if(!slyrics.equals(t.lyrics())) {blyrics=true; slyrics="(Multiple values)";}
				
				//if(sx==null) sx = t.x();
				//if(!sx.equals(t.x())) {bx=true; sx="(Multiple values)";}
			}
			
			file.setText(""+audios.size()+" files");
			title.setText(stitle);
			artist.setText(sartist);
			album.setText(salbum);
			track.setText(strack);
			year.setText(syear);
			genre.setText(sgenre);
			composer.setText(scomposer);
			comment.setText(scomment);
			lyrics.setText(slyrics);
			
			titleb.setSelected(false);
			artistb.setSelected(false);
			albumb.setSelected(false);
			trackb.setSelected(false);
			yearb.setSelected(false);
			genreb.setSelected(false);
			composerb.setSelected(false);
			commentb.setSelected(false);
			lyricsb.setSelected(false);
		}
		else
		{
			Audio a = audios.get(0);
			
			Tag t = a.tag();		
			file.setText(a.resource());
			title.setText(t.title());
			artist.setText(t.artist());
			album.setText(t.album());
			track.setText(t.track());
			genre.setText(t.genre());
			year.setText(t.year());
			composer.setText(t.composer());
			comment.setText(t.comment());
			lyrics.setText(t.lyrics());
			
			boolean editable = a.isTaggable() && new java.io.File(a.resource()).canWrite();
			title.setEnabled(editable);
			artist.setEnabled(editable);
			album.setEnabled(editable);
			track.setEnabled(editable);
			genre.setEnabled(editable);
			year.setEnabled(editable);
			composer.setEnabled(editable);
			comment.setEnabled(editable);
			lyrics.setEnabled(editable);
			update.setEnabled(editable);
		}
		changed(false);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Audio a = audios.get(0);
		
		if(e.getSource()==update)
		{
			try
			{
				if(audios.size() > 1)
				{
					for(Audio ea : audios)
					{
						a = ea; //so error message displays correct file
						Tag t = a.tag();
						if(titleb.isSelected()) t.title(title.getText());
						if(artistb.isSelected()) t.artist(artist.getText());
						if(albumb.isSelected()) t.album(album.getText());
						if(trackb.isSelected()) t.track(track.getText());
						if(yearb.isSelected()) t.year(year.getText());
						if(genreb.isSelected()) t.genre(genre.getText());
						if(composerb.isSelected()) t.composer(composer.getText());
						if(commentb.isSelected()) t.comment(comment.getText());
						if(lyricsb.isSelected()) t.lyrics(lyrics.getText());
						a.tag(t);
					}
				}
				else
				{
					Tag t = a.tag();
					t.title(title.getText());
					t.artist(artist.getText());
					t.album(album.getText());
					t.track(track.getText());
					t.year(year.getText());
					t.genre(genre.getText());
					t.composer(composer.getText());
					t.comment(comment.getText());
					t.lyrics(lyrics.getText());
					a.tag(t);
				}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,  "Error writing tag for file "+"\n"+a.resource()+"\n"+ex.toString(), "Error writing tag!", JOptionPane.ERROR_MESSAGE);
			}
			reset();
		}
		if(e.getSource()==cancel) dispose();
		if(e.getSource()==revert) reset();
	}
}
