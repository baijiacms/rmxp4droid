
package com.music.servequake.truemusic;

import java.io.File;
import javax.swing.filechooser.*;

public class MusicFileFilter extends FileFilter
{
    public boolean accept(File f)
	{
		if(f == null) return false;
	    if(f.isDirectory()) return true;
		
		String[] extensions = {".mp3", ".ogg", ".wav", ".aif", ".aiff", ".au", ".m3u", ".flac", ".spx", ".midi", ".mid"};
		
	    for(String x: extensions)
		{
			if(f.getName().toLowerCase().endsWith(x)) return true;
		}
		return false;
    }

    public String getDescription()
	{
		return "Supported music files (.mp3, .ogg, .wav, .aif, .aiff, .au, .m3u, .flac, .spx, .midi, .mid)";
    }
}
