package com.rmxp4droid.cxysfx.core.decoder.cxydecoder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Iterator;

public class CxyDecoder {
	 private Hashtable<Object,Object> jarContents = null;

	 
	
	 public  CxyDecoder()
	 {
	
	 }
	
	 public void loadJar(String jarfileUrl)
	 {
		 ObjectRW objectRW=new ObjectRW();
		 jarContents=(Hashtable<Object,Object>)objectRW.ReadObjectFileToObject(jarfileUrl);
		 
	 }
	 
	
	 public String searchFile(String startX)
	 {
		 
		Iterator itor= jarContents.keySet().iterator();
		while(itor.hasNext())
		{
			String key=itor.next().toString();
			if(key.startsWith(startX))
			{
			
				return key;
			}
			
		}
		return null;
		
	 }
	 

	 public byte[] getFile(String key) 
	 {
		 try{
		 return base64Decoder(  (byte[])jarContents.get(key.toUpperCase()));
		 }catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
		 return null;
	 }
	 
	 public InputStream getFileST(String key) 
	 {
		 return new ByteArrayInputStream(getFile(key));
		 
	 }
		private byte[] base64Decoder( byte[] byt) throws Exception
		{
			 ByteArrayInputStream boustream=new ByteArrayInputStream(byt);
			
		
			 return (new com.sun.base64.BASE64Decoder()).decodeBuffer(boustream);
		}
}
