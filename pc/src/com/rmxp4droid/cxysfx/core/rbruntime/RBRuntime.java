package com.rmxp4droid.cxysfx.core.rbruntime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.rmxp4droid.BaseConf;
import com.rmxp4droid.Logging;
import com.rmxp4droid.cxysfx.core.control.DateLoaderControl;
import com.rmxp4droid.cxysfx.core.control.RenderControl;
public class RBRuntime {
	private final static RBRuntime INSTANCEOF=new RBRuntime();
	private boolean init=false;
	public static RBRuntime getINSTANCEOF() {
		return INSTANCEOF;
	}
	private RunScriptEngine interpreter;
	private RBRuntime()
	{
		interpreter=new RunScriptEngineV2(); 
	}
	private  void loadBase()
	{
	
		 runScript("i1base/1java.rb");
	     runScript("i1base/2methods.rb");
	     runScript("i1base/3sprite.rb");
	}
	private  void loadcore()
	{
	
		 runScript("i2core/1table.rb");
		 runScript("i2core/2javaMethod.rb");
//		 runScript("i2core/3bitmap.rb");
		 runScript("i2core/4color.rb");
		 runScript("i2core/5graphics.rb");
		 runScript("i2core/6input.rb");
		 runScript("i2core/7plane.rb");
		 runScript("i2core/8tilemap.rb");
		 runScript("i2core/9tone.rb");
		 runScript("i2core/10window.rb");
	}
	private  void loadrgss()
	{
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
		 runScript("i3rpg/1rpg.rb");
	}
//	private  void loadrgssplus()
//	{
//		 runScript("4rgssplus/1cache.rb");
//		 runScript("4rgssplus/2sprite.rb");
//		 runScript("4rgssplus/3weather.rb");
//	}
	private  void loadFinal()
	{
		if(BaseConf.USECacheScript)
		{
			File file=new File(BaseConf.ScriptCacheFolder);
			if(!file.exists())
			{
				file.mkdir();
			}
			File indexFile=new File(BaseConf.ScriptCacheFolder+BaseConf.ScriptCacheIndex);
			if(!indexFile.exists())
			{//创建缓存文件
				 runScript("i4run/exportScript.rb");//生成/Data/Scripts.rb文件
			}
			readIndexFile(BaseConf.ScriptCacheFolder,BaseConf.ScriptCacheIndex);
			
		}else
		{
			
			 runScript("i5other/runScripts.rb");
		}
	}

	private void readIndexFile(String cacheFolder,String indexFileName)
	{
		
		BufferedReader br=null;
		try {
			 br=new BufferedReader(new InputStreamReader(new FileInputStream(cacheFolder+indexFileName)));
				String scriptName=null;
				List<String> scriptli=new ArrayList<String>();
				while((scriptName=br.readLine())!=null)
				{
					scriptli.add(cacheFolder+scriptName);
				}
				for(int i=0;i<scriptli.size();i++)
				{
					runSDCardScript(scriptli.get(i));
				}
		}catch(Exception ex)
		{
			Logging.writerLog(ex);
			ex.printStackTrace();
			System.exit(0);
		}
	}
	
	private  void runSDCardScript(String fileName)
	{
	
		try {
			interpreter.runScript(inputStreamToString(new FileInputStream(fileName)),fileName);
//			interpreter.runScriptlet(new FileInputStream(fileName),fileName);
		} catch (Exception ex) {
			Logging.writerLog(ex);
			ex.printStackTrace();
			System.exit(0);
		}
	}
	private String inputStreamToString(InputStream is) throws IOException
	{
		 BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
		 StringBuilder sb = new StringBuilder();
	     String line;
	        while ((line = reader.readLine()) != null) {

	                          sb.append(line).append(System.getProperty("line.separator"));

	        	               }
	        return sb.toString();
		
	}
	
	
	private  void runScript(String fileName)
	{
	
		try {
			interpreter.runScript(DateLoaderControl.getINSTANCEOF().getScript(fileName),fileName);
		} catch (Exception ex) {
			ex.printStackTrace(); //不打印错误
			System.exit(0);
		}
	}
	/**
	 * @param args
	 */
	public  void loadScriptData() {
		if(init==false)
		{
		init=true;
		BaseConf.LOADING_SEEKBAR_INDEX=0;
		loadBase();
		BaseConf.LOADING_SEEKBAR_INDEX=BaseConf.LOADING_SEEKBAR_INDEX+30;
		RenderControl.getINSTANCEOF().getWindow().refreshUI();
		loadcore();
		BaseConf.LOADING_SEEKBAR_INDEX=BaseConf.LOADING_SEEKBAR_INDEX+30;
		RenderControl.getINSTANCEOF().getWindow().refreshUI();
		loadrgss();
		BaseConf.LOADING_SEEKBAR_INDEX=BaseConf.LOADING_SEEKBAR_INDEX+30;
		RenderControl.getINSTANCEOF().getWindow().refreshUI();
//		loadrgssplus();
		loadFinal();
		}
	}
	
	
}
