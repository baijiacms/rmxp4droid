package com.rmxp4droid.pub.component;

import java.io.File;
import java.io.InputStream;

import com.rmxp4droid.cxysfx.core.control.DateLoaderControl;

public class MusicGeter {
	public static InputStream getMusicFile(String res)
	{
		return DateLoaderControl.getINSTANCEOF().getFileInputStream(res);
	}
	public static File searchAudio(String res)
	{
		String file=DateLoaderControl.getINSTANCEOF().getMusicInputStream(res);

		if(file!=null)
		{
			return new File(file);
		}
		return null;
	}
}
