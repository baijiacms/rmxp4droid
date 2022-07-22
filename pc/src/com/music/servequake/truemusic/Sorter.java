package com.music.servequake.truemusic;

import java.util.*;
import java.io.*;
import javax.swing.table.*;
import javax.swing.event.*;

//ARGH, this was annoying to get to work right
public class Sorter<M extends Playlist> extends TableRowSorter
{
	Playlist p;
	Sorter(M p)
	{
		super(p);
		this.p=p;
	}

	public void toggleSortOrder(int column)
	{
		super.toggleSortOrder(column);
		
		ArrayList<Audio> oldlist = p.tracks();
		ArrayList<Audio> newlist = new ArrayList<Audio>();
		boolean any=false;
		
		for(int viewi=0;viewi<oldlist.size();viewi++)
		{
			int modeli = convertRowIndexToModel(viewi);
			if(modeli!=viewi) any=true;		
			newlist.add(oldlist.get(modeli));
		}
		if(any)	{p.tracks(newlist);}
	}
}