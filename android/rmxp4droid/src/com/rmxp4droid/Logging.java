package com.rmxp4droid;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;


public class Logging implements UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		Logging.writerLog(arg1);
		android.os.Process.killProcess(android.os.Process.myPid()); 
		System.exit(-1);

	}
	public synchronized static void writerLog(Throwable ex)
	{
		java.io.BufferedWriter bw=null;
		try {
			bw = new BufferedWriter(new FileWriter(BaseConf.errorfile,true));
			PrintWriter pw=new PrintWriter(bw);
			ex.printStackTrace(pw);
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
			Logging.writerLog(exs);
		}
	}
}
