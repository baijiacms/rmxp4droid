package com.rmxp4droid.cxysfx.core.control;





import java.io.InputStream;


import com.rmxp4droid.cxysfx.core.dataloader.BaseDataLoader;
import com.rmxp4droid.cxysfx.core.dataloader.IDateLoader;


public class DateLoaderControl {
	private  static DateLoaderControl INSTANCEOF=new DateLoaderControl();
	private DateLoaderControl()
	{
	
			idateLoader=new BaseDataLoader();

		
	}
	public static DateLoaderControl getINSTANCEOF() {
		return INSTANCEOF;
	}
	private  IDateLoader idateLoader;


	public  String getImageInputStream(String fileurl)
	{
		
		return idateLoader.getImageInputStream(fileurl);
	}

	public  String getScript(String fileurl)
	{
	
		return idateLoader.getScript(fileurl);
	}
	public  String getMusicInputStream(String fileurl)
	{
	
		return idateLoader.getMusicInputStream(fileurl);
	}
	public  InputStream getFileInputStream(String fileurl)
	{
	
		return idateLoader.getFileInputStream(fileurl);
	}
	
}
