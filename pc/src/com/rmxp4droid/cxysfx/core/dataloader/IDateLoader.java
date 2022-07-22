package com.rmxp4droid.cxysfx.core.dataloader;

import java.io.InputStream;

public interface IDateLoader {
	public String getScript(String fileurl);
	public InputStream getFileInputStream(String fileurl);
	public String getImageInputStream(String fileurl);
	public String getMusicInputStream(String fileurl);
}
