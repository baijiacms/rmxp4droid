package com.publish;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.publish.cxyencoder.EncodeFile;
import com.publish.cxyencoder.ObjectRW;

/**
 * A
 * 适用地图与动画的jar加密
 * @author Administrator
 *
 */
public class DateEncoder {
	 private Hashtable<Object,Object> jarContents = new
	 Hashtable<Object,Object>();
		
	 public static void main(String[] args) {
	 boolean isrun=true;
	 if(isrun)
	 {
		 DateEncoder me=new DateEncoder();
	 me.loadJarContents("X:/sdcard/data.jar","X:/sdcard/rmxp4droid.coredata");//动画加密
	 }
	 }
	 
	 /**
	  * 加密jar
	  * 其中单个文件进行base64加密
	  * @param jarFileName
	  * @param outputFile
	  * @return
	  */
	 private boolean loadJarContents(String jarFileName,String outputFile) { /* loadJarContents
	 */
	 try { /* load the contents */
	 ZipFile zFile = new ZipFile(jarFileName);
	 Enumeration<?> list = zFile.entries();
	 BufferedInputStream is;
	 ZipEntry ze = null;
	
	 while (list != null && list.hasMoreElements()) { /*
	 * save Jar contents
	 * in the hash table
	 */
	 ze = (ZipEntry) list.nextElement();
	 if (ze.isDirectory()) {
	 continue;
	 }
	
	 /* prepare repository */
	 byte resource[] = null;
	 if (ze.getSize() > 0) {
	 resource = new byte[(int) ze.getSize()];
	 }else
	 {
		 resource = new byte[1];
	 }
	 is = new BufferedInputStream(zFile.getInputStream(ze));
	
	 /* read in zip current zip entry */
	 is.read(resource, 0, resource.length);
	
	 /* stow the resource full path */
//	 jarContents.put(ze.getName().toLowerCase(), resource);
	
	 /* stow the resource filename only */
	 int i = ze.getName().lastIndexOf('/');
	 if (i > 0) {
	 String key = ze.getName().substring(i + 1,
	 ze.getName().length());
//	  System.out.println("raw "+ze.getName()+" key="+key);
	 jarContents.put(ze.getName().toUpperCase(), base64Encoder(resource));
	 }
	 } /* save Jar contents in the hash table */
	 } catch (NullPointerException e) {
		 e.printStackTrace();
	 System.out.println("done.");
	 return (false);
	 } catch (FileNotFoundException e) {
	 e.printStackTrace();
	 return (false);
	 } catch (IOException e) {
	 e.printStackTrace();
	 return (false); 
	 } catch (Exception e) {
	 e.printStackTrace();
	 return (false);
	 }
	 ObjectRW objectRW=new ObjectRW();
	 

	 objectRW.saveObjectToFile(outputFile,  jarContents);
	 
	 EncodeFile encode=new EncodeFile();
	 encode.encrypt(new File(outputFile), "CJIUYTRFGHNMKIOL");
	 
	 // if(MAEPlugin.DBUG_MAEP)
	 // System.out.println("JarResources-loadJarContents() jarFileName='"+
	 // jarFileName+"'\n jarContents="+jarContents.toString());
	
	 return (true);
	 }
	 
	 /**
	  * byte base64加密
	  * @param byt
	  * @return
	  * @throws Exception
	  */
		public byte[] base64Encoder( byte[] byt) throws Exception
		{
			 ByteArrayOutputStream boustream=new ByteArrayOutputStream();
			 (new com.sun.base64.BASE64Encoder()).encode(byt,boustream);
			 return boustream.toByteArray();
		}
}
