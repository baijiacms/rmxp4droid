package com.rmxp4droid;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Logging {
	public synchronized static void writerLog(Throwable ex)
	{
		java.io.BufferedWriter bw=null;
		try {
			bw = new BufferedWriter(new FileWriter(BaseConf.errorfile,true));
			PrintWriter pw=new PrintWriter(bw);
			ex.printStackTrace(pw);
			pw.flush();
			bw.flush();
			bw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public static void writerLog(String ex)
	{
		try{
		throw  new Exception(ex);
		}catch(Exception exs)
		{
			writerLog(exs);
		}
	}
}
