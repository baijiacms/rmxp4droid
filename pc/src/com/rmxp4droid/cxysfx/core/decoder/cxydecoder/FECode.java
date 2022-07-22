package com.rmxp4droid.cxysfx.core.decoder.cxydecoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class FECode {
	/**
     * 解密函数
     * 输入：
     * 要解密的文件，密码（由0-F组成，共48个字符，表示3个8位的密码）如：
     * AD67EA2F3BE6E5ADD368DFE03120B5DF92A8FD8FEC2F0746
     * 其中：
     * AD67EA2F3BE6E5AD DES密码一
     * D368DFE03120B5DF DES密码二
     * 92A8FD8FEC2F0746 DES密码三
     * 输出：
     * 对输入的文件解密后，保存到用户指定的文件中。
     */
    public ByteArrayInputStream decrypt(FileInputStream fileIn,String sKey){
        try{
       

     
                    
                    byte[] bytK1 = getKeyByStr(sKey.substring(0,16));
                    
                    byte[] buf = new byte[1024];

                    ByteArrayOutputStream os=new ByteArrayOutputStream();
                   
                    int numRead = 0;
                    while ((numRead = fileIn.read(buf)) >= 0) {
                      os.write(buf, 0, numRead);
                    }
                    os.flush();
                    os.close();
                    return new ByteArrayInputStream(decryptByDES(os.toByteArray(),bytK1));
                    
                   
         
         
        }catch(Exception e){
			// TODO Auto-generated catch block
			System.out.println("a game error");
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
    
    /**
     * 输入密码的字符形式，返回字节数组形式。
     * 如输入字符串：AD67EA2F3BE6E5AD
     * 返回字节数组：{173,103,234,47,59,230,229,173}
     */
    private byte[] getKeyByStr(String str){
        byte[] bRet = new byte[str.length()/2];
        for(int i=0;i<str.length()/2;i++){
            Integer itg =
                    new Integer(16*getChrInt(str.charAt(2*i)) + getChrInt(str.charAt(2*i+1)));
            bRet[i] = itg.byteValue();
        }
        return bRet;
    }
    /**
     * 计算一个16进制字符的10进制值
     * 输入：0-F
     */
    private int getChrInt(char chr){
        int iRet=0;
        if(chr=="0".charAt(0)) iRet = 0;
        if(chr=="1".charAt(0)) iRet = 1;
        if(chr=="2".charAt(0)) iRet = 2;
        if(chr=="3".charAt(0)) iRet = 3;
        if(chr=="4".charAt(0)) iRet = 4;
        if(chr=="5".charAt(0)) iRet = 5;
        if(chr=="6".charAt(0)) iRet = 6;
        if(chr=="7".charAt(0)) iRet = 7;
        if(chr=="8".charAt(0)) iRet = 8;
        if(chr=="9".charAt(0)) iRet = 9;
        if(chr=="A".charAt(0)) iRet = 10;
        if(chr=="B".charAt(0)) iRet = 11;
        if(chr=="C".charAt(0)) iRet = 12;
        if(chr=="D".charAt(0)) iRet = 13;
        if(chr=="E".charAt(0)) iRet = 14;
        if(chr=="F".charAt(0)) iRet = 15;
        return iRet;
    }
}
