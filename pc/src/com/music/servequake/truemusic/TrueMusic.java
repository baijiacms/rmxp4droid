 
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

public class TrueMusic extends JFrame implements ActionListener, ChangeListener, MouseListener, KeyListener, DropTargetListener, ItemListener, ListSelectionListener
{
	final TrueMusic parent = this;
	
	Playlist p = new Playlist("Default Playlist",this);
	Playlist libraryp = new Playlist("Library View",this);
	
	
	JLabel timeBefore = new JLabel();
	JLabel timeAfter = new JLabel();
	JSlider seek = new JSlider(0,1);
	
	JButton previous = new JButton("<<");
	JButton stop = new JButton("X");
	JButton play = new JButton(">");
	JButton pause = new JButton("||");
	JButton next = new JButton(">>");
	JToggleButton random = new JToggleButton("~");
	JToggleButton repeat = new JToggleButton("\u221e"); //infinity sign
	JSlider volume = new JSlider(-80,0,-20);

	JPanel top;
	JTabbedPane panes = new JTabbedPane(JTabbedPane.LEFT);

	Vector playlists = new Vector();
	JComboBox playlistsBox;
	JButton newPlaylist = new JButton("Add");
	JButton removePlaylist = new JButton("Remove");
	JButton clearPlaylist = new JButton("Clear");
	JButton addMedia = new JButton("Add Music");
	JTable entries = new JTable(p);
	JLabel pstatus = new JLabel();
	
	JList artists = new JList(new String[]{"<Playlists still loading...>"});
	JList albums = new JList(new String[]{""});
	JTable songs = new JTable(libraryp);

	JButton smallMode = new JButton("Small Mode");
	JButton scan = new JButton("Scan for Music");
	JButton org = new JButton("Auto-Organize");
	
	Library l = new Library();
	
	public TrueMusic()
	{
		super("TrueMusic (loading playlists...)");
		
		try{l.load();}catch(Exception e){e.printStackTrace();}
		
		playlists.add(p);
		playlists.add(libraryp);
		playlistsBox = new JComboBox(playlists);
		
		previous.setToolTipText( "Previous Track" );
		pause.setToolTipText( "Pause" );
		play.setToolTipText( "Play" );
		stop.setToolTipText( "Stop" );
		next.setToolTipText( "Next Track" );
		random.setToolTipText( "Random" );
		repeat.setToolTipText( "Loop Playlist" );
		seek.setToolTipText( "Seek" );
		volume.setToolTipText( "Volume" );
		timeBefore.setToolTipText( "Time Elapsed" );
		timeAfter.setToolTipText( "Time Remaining" );
		playlistsBox.setToolTipText( "Playlists" );
		newPlaylist.setToolTipText( "Create New Playlist" );
		removePlaylist.setToolTipText( "Remove Current Playlist" );
		clearPlaylist.setToolTipText( "Clear Current Playlist" );
		addMedia.setToolTipText( "Add Music to Current Playlist" );
			
		previous.addActionListener(this);
		stop.addActionListener(this);
		play.addActionListener(this);
		pause.addActionListener(this);
		next.addActionListener(this);
		addMedia.addActionListener(this);
		random.addActionListener(this);
		repeat.addActionListener(this);
		seek.addChangeListener(this);
		volume.addChangeListener(this);
		entries.addMouseListener(this); 
		entries.addKeyListener(this); 
		playlistsBox.addItemListener(this);
		newPlaylist.addActionListener(this);
		removePlaylist.addActionListener(this);
		clearPlaylist.addActionListener(this);
		
		entries.setDragEnabled(true);
		new DropTarget(entries,this);
		entries.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		entries.setShowGrid(false);
		//entries.setRowSorter(new TableRowSorter<TableModel>(p));
		//entries.setRowSorter(p);
		entries.setRowSorter(new Sorter(p));
		
		JPanel controls = new JPanel(new GridLayout(1,5));
		controls.add(previous);
		controls.add(stop);
		controls.add(play);
		controls.add(pause);
		controls.add(next);
		JPanel pcontrols = new JPanel(new GridLayout(1,2));
		pcontrols.add(repeat);
		pcontrols.add(random);
		JPanel left = new JPanel();
		left.add(controls);
		left.add(pcontrols);
		
		JPanel b = new JPanel(new BorderLayout());
		b.add(volume);
		b.add(left, BorderLayout.WEST);
		
		JPanel topsub = new JPanel(new BorderLayout());
		topsub.add(seek);
		topsub.add(timeBefore,BorderLayout.WEST);
		topsub.add(timeAfter,BorderLayout.EAST);
		
		top = new JPanel(new BorderLayout());
		top.add(topsub);
		top.add(b, BorderLayout.SOUTH);
		
		JPanel playlistPanel = new JPanel(new BorderLayout());		
			JPanel pptop = new JPanel();
				pptop.add(playlistsBox);
				pptop.add(pstatus);
				pptop.add(newPlaylist);
				pptop.add(removePlaylist);
				pptop.add(clearPlaylist);
				pptop.add(addMedia);
			playlistPanel.add(pptop, BorderLayout.NORTH);
			playlistPanel.add(new JScrollPane(entries));

		JSplitPane ltop = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(artists), new JScrollPane(albums) );
		JSplitPane library = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ltop, new JScrollPane(songs) );
			artists.addListSelectionListener(this);
			albums.addListSelectionListener(this);
			songs.addMouseListener(this); 
			songs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			songs.setShowGrid(false);
		
		JPanel options = new JPanel();
			smallMode.addActionListener(this);
			options.add(smallMode);
			scan.addActionListener(this);
			options.add(scan);
			org.addActionListener(this);
			options.add(org);
		
		JTextArea about = new JTextArea("TrueMusic 20090926\nhttp://truemusic.servequake.com/\n\nmp3 - seeking, volume, timing, tag editing, caching\nWAV, SND, AU, AIFF, Native FLAC\n - seeking, volume, timing, caching\nOgg Vorbis, Ogg FLAC - seeking, volume, timing\nSpeex - seeking, volume\nmidi - interruptable play\n\nTrueMusic is free software; you can redistribute it\nand/or modify it under the terms of the GNU General\nPublic License as published by the Free Software\nFoundation; either version 2 of the License or (at\nyour option) any later version.\n\nThis program is distributed in the hope that it will\nbe useful but WITHOUT ANY WARRANTY; without even the\nimplied warranty of the MERCHANTABILITY or FITNESS\nFOR A PARTICULAR PURPOSE. See the GNU General Public\nLicense for more details.\n\nThis software is based on or using the J-Ogg library\navailable from http://www.j-ogg.de/ and copyrighted \nby Tor-Einar Jarnbjo.\n\nThis software uses jFlac released under the GPL,\nJavaLayer released under the LGPL, Java ID3 Tag\nLibrary released under the LGPL, and JSpeex released\nunder the BSD License.\n");
		about.setEditable(false);
		
		panes.add("Playlist", playlistPanel);
		panes.add("Library", library);
	//	panes.add("Podcasts", new JLabel());
	//	panes.add("Import", new JLabel());
	//	panes.add("Export", new JLabel());
	//	panes.add("Convert", new JLabel());
		panes.add("Options", options);
		panes.add("About", new JScrollPane(about));
		panes.setSelectedIndex(0);
		
		setLayout(new BorderLayout());
		add(top, BorderLayout.NORTH);
		add(panes);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(550,400);
		setVisible(true);
		
		ltop.setDividerLocation(0.5);library.setDividerLocation(0.5);
				
		try{ p.load(); }catch(Exception e){}
	
		scanPlaylists();
		refreshLibrary();	
	}
	
	private void scanPlaylists(){
		File dir = musicFolder();	
		File[] fs = dir.listFiles();

		for(File f:fs){
			String x = f.getName();
			if(x.equals("Default Playlist.m3u")) continue;

			if( x.toLowerCase().endsWith(".m3u") ){
				String name = x.substring(0,x.length()-4);
				try{
					Playlist pl = new Playlist(name, this);
					pl.load();
					playlists.add(pl);
				}catch(Exception e){
				}
			}
		}
	}
	
	public void error(Throwable t, String desc)
	{
		JOptionPane.showMessageDialog(this,  desc+"\n"+t.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void drop(DropTargetDropEvent e)
	{
		e.acceptDrop(1);
		Transferable t = e.getTransferable();
		
		try{
			final java.util.List<File> fl = (java.util.List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);

			new Thread()
			{
				public void run()
				{
					for(File f: fl) addMedia(f);
					playlistChanged();
				}
			}.start();
		}
		catch(Exception ex){}
	}

	public void itemStateChanged(final ItemEvent e)
	{
		new Thread(){
			public void run(){
				if(e.getStateChange()==e.SELECTED){
					Object o = playlistsBox.getSelectedItem();
					if(o==p) return;
					
					p.stop();
					p = (Playlist) o;
					
					entries.setModel(p);
					//entries.setRowSorter(new TableRowSorter<TableModel>(p));
					//entries.setRowSorter(p);
					entries.setRowSorter(new Sorter(p));
		
					p.volume(volume.getValue());		
					updateCurrent(p.current,p);
					
					pstatus.setText(p.getStatus());
				}
			}
		}.start();
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==play) p.play();
		else if(e.getSource()==pause) p.pause();
		else if(e.getSource()==stop) p.stop();
		else if(e.getSource()==next) p.next();
		else if(e.getSource()==previous) p.previous();
		else if(e.getSource()==addMedia) addMedia();
		else if(e.getSource()==newPlaylist) newPlaylist();
		else if(e.getSource()==removePlaylist) removePlaylist();
		else if(e.getSource()==clearPlaylist) p.clear();
		else if(e.getSource()==random) p.setRandom(random.isSelected());
		else if(e.getSource()==repeat) p.setRepeat(repeat.isSelected());
		else if(e.getSource()==smallMode) { setSize(500,90);validate();}
		else if(e.getSource()==scan) scanForMedia();
		else if(e.getSource()==org) new AutoOrganize(this);
	}
	
	void newPlaylist()
	{
		String name = JOptionPane.showInputDialog(parent, "Name new playlist:" );
		if(name==null||name.equals("")||playlists.contains(name)) return;
		
		Playlist pl = new Playlist( name, parent);
		try{ pl.save(); }catch(Exception e){}
		playlists.add(pl);
		playlistsBox.setSelectedItem(pl);
		return;
	}
	
	void removePlaylist()
	{
		if(p.name.equals("Default Playlist")) return;
		
		if(JOptionPane.showConfirmDialog(parent, "Are you sure you want to remove "+p.toString()+"?", "Confirm Delete", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
			p.stop();
			p.delete();
			playlistsBox.removeItem(p);
			playlistsBox.setSelectedIndex(0);
		}
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_DELETE){
			if(e.getSource()==entries) p.removeTrack(entries.getSelectedRow());
		}
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource()==entries)
		{
			if(e.getClickCount()==2) //double click: play selected
			{
				p.setTrack( entries.rowAtPoint(e.getPoint()) );
			}
			else if(e.getButton()==MouseEvent.BUTTON3) //right click: show menu
			{
				int index = entries.rowAtPoint(e.getPoint());
				if(index==-1) return;
				entries.setRowSelectionInterval(index,index);
				new TrackMenu( p.getTrack(index) ).show(entries, e.getX(), e.getY() );
			}
		}
		else if(e.getSource()==songs)
		{
			if(e.getClickCount()==2)
			{
				if(p != libraryp)
				{
					p.stop();
					p = libraryp;
					
					entries.setModel(p);
					//entries.setRowSorter(new TableRowSorter<TableModel>(p));
					entries.setRowSorter(new Sorter(p));
					
					p.volume(volume.getValue());
					//updateCurrent(p.current,p);
					
					playlistsBox.setSelectedItem(p);
				}
				
				p.setTrack( entries.rowAtPoint(e.getPoint()) );
			}
		}
	}
	
	class TrackMenu extends JPopupMenu implements ActionListener{
		JMenuItem play,edit,remove,delete;
		Audio a;

		TrackMenu(Audio a){
			this.a = a;
			//play = add("Play");   //good ui: only provide one way to do things
			edit = add("Tag");
			remove = add("Remove");
			delete = add("Delete");
			//play.addActionListener(this);
			edit.addActionListener(this);
			remove.addActionListener(this);
			delete.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e){
			//if(e.getSource()==play) p.setTrack(a);
			//else
			if(e.getSource()==edit) new TagEditor(a,p);
			else if(e.getSource()==remove) p.removeTrack(a);
			else if(e.getSource()==delete)
			{
				int op = JOptionPane.showConfirmDialog(parent, "Are you sure you want to permantly delete\n"+a.resource()+"\nfrom your computer?","Confirm Delete", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

				if(op!=JOptionPane.YES_OPTION) return;
				
				File f = new File(a.resource());
				boolean success =false;
				try{ success = f.delete(); }catch(Exception ex){}
				
				if(!success){
					JOptionPane.showMessageDialog(this, "Could not delete "+a.resource() , "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				p.removeTrack(a);
			}
		}
	}//TrackMenu
	
	public void updateCurrent(Audio a, Playlist p){
		if(this.p!=p) return;
		if(a==null){
			setTitle("Add music to your playlist.");
			play.setEnabled(false);
			pause.setEnabled(false);
			stop.setEnabled(false);
			next.setEnabled(false);
			previous.setEnabled(false);
			seek.setEnabled(false);
			
		}else{
			setTitle(a.displayString());			
			boolean flag = a.isSeekable() || a instanceof MidiAudio;
			play.setEnabled(flag);
			pause.setEnabled(flag); if(a instanceof MidiAudio) pause.setEnabled(false);
			stop.setEnabled(flag);
			next.setEnabled(flag);
			previous.setEnabled(flag);
			seek.setMaximum(a.frameLength());
			seek.setEnabled(a.isSeekable());
			entries.setRowSelectionInterval(p.currentIndex(),p.currentIndex());
		}
	}
	
	public void playlistChanged(){
		p.save();
		l.save();	
		
		pstatus.setText(p.getStatus());
	}
	
	public void time(int elapsed, int remaining){
		if(elapsed+remaining==0){//the timing info is lacking for the format
			timeBefore.setText("  ");
			timeAfter.setText("  ");
		}else{
			timeBefore.setText("  "+formattedSeconds(elapsed));
			timeAfter.setText(formattedSeconds(remaining)+"  ");
		}
	}
	public String formattedSeconds(int seconds){
		int minutes = seconds / 60;
		seconds = seconds % 60;
		return ""+minutes+":"+ (seconds<10?"0":"") + seconds;
	}
	
	int ignore=0; //prevent normal advancement from triggering seeks
	public void frameCompleted(int frame){
		ignore=frame;
		seek.setValue(frame);
	}
	
	public void stateChanged(ChangeEvent e) {
		if(e.getSource()==seek && seek.getValue()!=ignore) p.seek(seek.getValue());
		else if(e.getSource()==volume) p.volume(volume.getValue());
	}
	
	void refreshLibrary(){
		artists.setListData(l.artists());
		artists.setSelectedIndex(0);
	}
	
	//TODO: use Library factory
	Playlist mockp = new Playlist("",this);
	void scanForMedia(){
		if(JOptionPane.showConfirmDialog(parent, "Scan all drives for music (this can take awhile)?","Really Scan?", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)!=JOptionPane.YES_OPTION) return;
				
		scanFiles(File.listRoots());
		refreshLibrary();
	}
	void scanFiles(File[] fs){
		if(fs!=null)
		for(File f: fs){
			if(f.isDirectory())	scanFiles(f.listFiles());
			else try{ mockp.addTrack(f.toString()); }catch(Exception e){}
		}
	}
	
	static File musicFolder(){
		try{File c = new File("./").getCanonicalFile(); //current directory if it is called Music
		if(c.getName().equals("Music") || c.getName().equals("My Music")) return c;
		}catch(Exception e){}
		
		File f = new JFileChooser().getCurrentDirectory(); //home directory dive to Music if possible
		File musicf = new File(f,"Music");
		if(musicf.exists()) return musicf;
		musicf = new File(f,"My Music");
		if(musicf.exists()) return musicf;
		return f; //otherwise just home
	}
	
	public void addMedia(){
		final JFileChooser chooser = new JFileChooser(musicFolder());
		chooser.setDragEnabled(true);
		chooser.setFileFilter(new MusicFileFilter());
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setMultiSelectionEnabled(true);

		if(chooser.showDialog(this, "Add to Playlist" ) == JFileChooser.APPROVE_OPTION){
			new Thread(){
				public void run(){
						File[] fs = chooser.getSelectedFiles();
						for(File f: fs) addMedia(f);
						refreshLibrary();
						playlistChanged();
				}
			}.start();
    	}
	}
	
	void addMedia(File f){
		if(f.isFile()){
			try	{ 
				p.addTrack(f.toString());
			}catch(Exception e){
				error(e, "Cannot play "+f.getName()+"\nThe encoding might not be supported\nor the file may be corrupt.");
				e.printStackTrace();
			}
		}else{
			for(File f2: f.listFiles() ) addMedia(f2);
		}
	}

	public void valueChanged(ListSelectionEvent e)
	{
		if(e.getSource()==artists){
			//todo: should try to retain album on artist switch
			int i = artists.getSelectedIndex();
			if(i==-1) return;
			else if(i==0) albums.setListData(l.albums());
			else albums.setListData(l.albumsForArtist(sansCount(artists.getSelectedValue().toString()) ));
			albums.setSelectedIndex(0);
		} else {
			if(albums.getSelectedIndex()<0) return;
			int i = artists.getSelectedIndex();
			if(i==-1) return;
			else if(i==0){
				int j = albums.getSelectedIndex();
				if(j==0) libraryp.withTags(l.allSongs() );
				else libraryp.withTags(l.songsForAlbum(sansCount(albums.getSelectedValue().toString()) ));
			}else{
				int j = albums.getSelectedIndex();
				if(j==0) libraryp.withTags(l.songsForArtist(sansCount(artists.getSelectedValue().toString()) ));
				else libraryp.withTags(l.songsForArtistAndAlbum(sansCount(artists.getSelectedValue().toString()),sansCount(albums.getSelectedValue().toString()) ));			
			}
		}
	}
	
	public String sansCount(String x)
	{
		int i = x.lastIndexOf(" ");
		return x.substring(0,i);
	}
	
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void dragEnter(DropTargetDragEvent e){}
	public void dragExit(DropTargetEvent e){}
	public void dragOver(DropTargetDragEvent e){}
	public void dropActionChanged(DropTargetDragEvent e){}
	
//	public static void main(String args[]) throws Exception
//	{
//		if(args.length > 0)
//		{
//			File f = new File( System.getProperty("user.dir") , ".tmargs" );
//			PrintStream out = new PrintStream(f);
//			for(String x: args) out.println(x);
//			out.flush();out.close();
//			System.out.println("sent");
//			Thread.sleep(300);			
//			if(f.exists()==false) return;
//			
//			System.out.println("not received");
//			f.delete();
//		}
//		
//		UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
//		
//		TrueMusic tm = new TrueMusic();
//		try{		
//			for(String x: args) tm.addMedia(new File(x));
//		}catch(Exception ex){}
//		
//		File f = new File( System.getProperty("user.dir") , ".tmargs" );
//		while(true)
//		{
//			try{
//				Thread.sleep(100);
//				if(f.exists())
//				{
//					System.out.println("received");
//					BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
//					ArrayList<String> xs = new ArrayList<String>();
//					String x;
//					while( (x=in.readLine())!=null )
//						xs.add(x);
//					in.close();
//					f.delete();
//					for(String z: xs) try{
//						tm.addMedia(new File(z));
//						}catch(Exception ex){}
//					
//					tm.requestFocus();
//				}
//			}catch(Exception ex){ex.printStackTrace();}
//		}
//	}
}
