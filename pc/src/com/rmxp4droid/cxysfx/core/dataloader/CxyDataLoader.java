package com.rmxp4droid.cxysfx.core.dataloader;



import java.io.InputStream;

import com.rmxp4droid.BaseConf;
import com.rmxp4droid.cxysfx.core.decoder.RubyKeyDecoder;
import com.rmxp4droid.cxysfx.core.decoder.cxydecoder.CxyDecoder;
/**
 * 默认数据载入组件 基本的
 * @author Administrator
 *
 */
public class CxyDataLoader implements IDateLoader{
	/**
	 * 核心ruby脚本载入
	 */
	private RubyKeyDecoder rubyKeyDecoder;
	private CxyDecoder cxyDecoder;
	public CxyDataLoader()
	{
		cxyDecoder=new CxyDecoder();
		cxyDecoder.loadJar(BaseConf.CODER_ENCODE_DATE_FILE);
		//需要放在Data文件夹下，否则找不到
		rubyKeyDecoder=new RubyKeyDecoder(cxyDecoder.getFileST(BaseConf.CODER_RUBYKEY));
		
	}
	
	public String getScript(String fileurl) {
	
		return rubyKeyDecoder.getRBScript(fileurl);

	}
	public InputStream getFileInputStream(String fileurl) {
		
		
			return cxyDecoder.getFileST(searchImage(fileurl));

	}
	public String getImageInputStream(String fileurl) {
		
			return searchImage(fileurl);
	}

	public String getMusicInputStream(String fileurl) {
		// TODO Auto-generated method stub
		return searchAudio(fileurl);
	}

//	private 	final String[] imageEnd = {".png", ".jpg", ".jpeg", ".bmp" };
//	private 	final String[] AudioEnd = {".ogg", ".mp3", ".wav", ".mid", ".midi" };
	/**
	 * Search for the image, e.g. add ending
	 * 
	 * @return
	 */
	private String searchImage(final String filename) {
		

		// check endings
	
	
			String f=cxyDecoder.searchFile((filename ).toUpperCase());
			if(f!=null)
			{
				return f;
			}


	
	
		return null;
	}
	/**
	 * Search for the image, e.g. add ending
	 * 
	 * @return
	 */
	private String searchAudio(final String filename) {
	

		// check endings
	
	
			String f=cxyDecoder.searchFile((filename ).toUpperCase());
			if(f!=null)
			{
				return f;
			}
	

		
	
		return null;
	}
	
}
