package com.rmxp4droid.cxysfx.core.decoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


import com.rmxp4droid.BaseConf;

public class RubyKeyDecoder {
	private HashMap<String,byte[]> scriptMap=null;
	private ObjectInputStream ois=null;
	public RubyKeyDecoder(InputStream ins)
	{
		try {
			 ois=new ObjectInputStream(ins);
			scriptMap=(HashMap<String,byte[]>)ois.readObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String getRBScript(String fileurl)
	{
		try {
			return gzipunrar(decryptByDES(scriptMap.get(fileurl),BaseConf.VERSIONS.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			System.exit(-1);
			return null;
		}
			
	}
	/**
	 * 解压缩方案2
	 * @param bytes
	 * @return
	 */
	private  String gzipunrar(byte[] bytes)
	{
		try{
			ByteArrayInputStream ins = new ByteArrayInputStream(bytes);
			GZIPInputStream zip2 = new GZIPInputStream(ins);
			ByteArrayOutputStream ous=new ByteArrayOutputStream();
		
			 int i=-1;
			while((i=zip2.read())!=-1)
			{
				ous.write(i);
			}
		
			zip2.close();
			return new String(ous.toByteArray());
		}catch(Exception ex)
		{
			ex.printStackTrace();
			System.exit(0);
		}
		return null;
	}
    /**
     * 用DES方法解密输入的字节
     * bytKey需为8字节长，是解密的密码
     */
    private byte[] decryptByDES(byte[] bytE,byte[] bytKey) throws Exception{
        DESKeySpec desKS = new DESKeySpec(bytKey);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey sk = skf.generateSecret(desKS);
        Cipher cip = Cipher.getInstance("DES");
        cip.init(Cipher.DECRYPT_MODE,sk);
        return cip.doFinal(bytE);
    }
}
