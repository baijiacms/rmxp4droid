package com.publish;

import java.io.FileNotFoundException;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.zip.GZIPOutputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.rmxp4droid.BaseConf;
/**
 * 导出核心ruby脚本
 * @author cxy
 *
 */
public class EncodeScriptFile {
	HashMap<String,byte[]> s=new HashMap<String,byte[]>();
	/**
	 * 导出核心ruby脚本
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		EncodeScriptFile d=new EncodeScriptFile();
		d.read();
		d.outputFile();
	}
	public void read() throws Exception
	{
		 runScript("i1base/1java.rb");
	     runScript("i1base/2methods.rb");
	     runScript("i1base/3sprite.rb");
	     
	     runScript("i2core/1table.rb");
		 runScript("i2core/2javaMethod.rb");
		 runScript("i2core/3bitmap.rb");
		 runScript("i2core/4color.rb");
		 runScript("i2core/5graphics.rb");
		 runScript("i2core/6input.rb");
		 runScript("i2core/7plane.rb");
		 runScript("i2core/8tilemap.rb");
		 runScript("i2core/9tone.rb");
		 runScript("i2core/10window.rb");
		 
//		 runScript("3rgss/actor.rb");
//		 runScript("3rgss/animation.rb");
//		 runScript("3rgss/animationFrame.rb");
//		 runScript("3rgss/animationTiming.rb");
//		 runScript("3rgss/armor.rb");
//		 runScript("3rgss/audioFile.rb");
//		 runScript("3rgss/class.rb");
//		 runScript("3rgss/classLearning.rb");
//		 runScript("3rgss/commonEvent.rb");
//		 runScript("3rgss/enemy.rb");
//		 runScript("3rgss/enemyAction.rb");
//		 runScript("3rgss/event.rb");
//		 runScript("3rgss/eventCommand.rb");
//		 runScript("3rgss/eventPage.rb");
//		 runScript("3rgss/eventPageCondition.rb");
//		 runScript("3rgss/eventPageGraphic.rb");
//		 runScript("3rgss/item.rb");
//		 runScript("3rgss/map.rb");
//		 runScript("3rgss/mapInfo.rb");
//		 runScript("3rgss/moveCommand.rb");
//		 runScript("3rgss/moveRoute.rb");
//		 runScript("3rgss/skill.rb");
//		 runScript("3rgss/state.rb");
//		 runScript("3rgss/system.rb");
//		 runScript("3rgss/systemTestBattle.rb");
//		 runScript("3rgss/systemWords.rb");
//		 runScript("3rgss/tileset.rb");
//		 runScript("3rgss/troop.rb");
//		 runScript("3rgss/troopMember.rb");
//		 runScript("3rgss/troopPage.rb");
//		 runScript("3rgss/troopPageCondition.rb");
//		 runScript("3rgss/weapon.rb");
//		 
//		 runScript("4rgssplus/1cache.rb");
//		 runScript("4rgssplus/2sprite.rb");
//		 runScript("4rgssplus/3weather.rb");
		 runScript("i3rpg/1rpg.rb");
		 
//		 runScript("runScripts.rb");过期,变成转换
		 runScript("i4run/exportScript.rb");//生成/Data/Scripts.rb文件
		 runScript("i5other/runScripts.rb");
	}
	private  void runScript(String fileName) throws Exception
	{
		
			InputStream f=this.getClass().getResourceAsStream("rb/"+fileName);
			   byte[] contents = new byte[f.available()];
			   int i=0;
			    int count = 0;
			    while ((i = f.read()) != -1) {
			        contents[count] = (byte) i;
			        count++;
			      }
//			    s.put(fileName,encryptByDES( contents,BaseConf.VERSIONS.getBytes()));
			    s.put(fileName,encryptByDES( gziprar(contents),BaseConf.VERSIONS.getBytes()));
	}
	/**
	 * 压缩
	 * @param bytes
	 * @return
	 */
	private static byte[] gziprar(byte[] bytes)
	{
		try{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream zip = new GZIPOutputStream(out);
		zip.write(bytes);
		zip.finish();
		zip.close();
		return out.toByteArray();
		}catch(Exception ex)
		{
			ex.printStackTrace();
			System.exit(0);
		}
		return null;
	}
    /**
     * 用DES方法加密输入的字节
     * bytKey需为8字节长，是加密的密码
     */
    private byte[] encryptByDES(byte[] bytP,byte[] bytKey) throws Exception{
        DESKeySpec desKS = new DESKeySpec(bytKey);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey sk = skf.generateSecret(desKS);
        Cipher cip = Cipher.getInstance("DES");
        cip.init(Cipher.ENCRYPT_MODE,sk);
        return cip.doFinal(bytP);
    }
	public void outputFile() throws FileNotFoundException, IOException
	{
		java.io.ObjectOutputStream fos=new ObjectOutputStream(new FileOutputStream("C:/ruby.key"));
		fos.writeObject(s);
		fos.flush();
		fos.close();
	}
}
