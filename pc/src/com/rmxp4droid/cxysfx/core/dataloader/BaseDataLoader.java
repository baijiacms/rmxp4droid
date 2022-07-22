package com.rmxp4droid.cxysfx.core.dataloader;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import jruby.RBScript;

import com.rmxp4droid.BaseConf;
import com.rmxp4droid.Logging;
import com.rmxp4droid.cxysfx.core.decoder.RubyKeyDecoder;
/**
 * 默认数据载入组件 基本的
 * @author Administrator
 *
 */
public class BaseDataLoader implements IDateLoader{
	/**
	 * 核心ruby脚本载入
	 */
	private RubyKeyDecoder rubyKeyDecoder;
	public BaseDataLoader()
	{
		rubyKeyDecoder=new RubyKeyDecoder(RBScript.class.getResourceAsStream(BaseConf.CODER_RUBYKEY));
		
	}
	
	public String getScript(String fileurl) {
	
		return rubyKeyDecoder.getRBScript(fileurl);

	}
	public InputStream getFileInputStream(String fileurl) {
	
		
		try {
			return new FileInputStream(fileurl);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String getImageInputStream(String fileurl) {
		
	
			return searchImage(fileurl);
	}

	public String getMusicInputStream(String fileurl) {
		// TODO Auto-generated method stub
		return searchAudio(fileurl);
	}

	private 	final String[] imageEnd = {".png", ".jpg", ".jpeg", ".bmp" };
	private 	final String[] AudioEnd = {".ogg", ".mp3", ".wav", ".mid", ".midi" };
	/**
	 * Search for the image, e.g. add ending
	 * 
	 * @return
	 */
	private String searchImage(final String filename) {
		File f2 = new File(BaseConf.WORK_FOLDER, filename );
		if (f2.exists())
			return f2.getPath();

		// check endings
	
		for (final String s : imageEnd) {
			File f = new File(BaseConf.WORK_FOLDER, filename + s);
			if (f.exists())
				return f.getPath();
		}

		
		Logging.writerLog("Exception:"+"Image " + new File(BaseConf.WORK_FOLDER, filename).getAbsolutePath()
				+ " don't exist");
	
		return null;
	}
	/**
	 * Search for the image, e.g. add ending
	 * 
	 * @return
	 */
	private String searchAudio(final String filename) {
		File f2 = new File(BaseConf.WORK_FOLDER, filename );
		if (f2.exists())
			return f2.getPath();
		
		File f;

		// check endings
	
		for (final String s : AudioEnd) {
			f = new File(BaseConf.WORK_FOLDER, filename + s);
			if (f.exists())
				return f.getAbsolutePath();
		}

		
		Logging.writerLog("Exception:"+"Image " + new File(BaseConf.WORK_FOLDER, filename).getAbsolutePath()
				+ " don't exist");
	
		return null;
	}
	
}
