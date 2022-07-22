/**
 * 
 */
package com.rmxp4droid.pub.component;



import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.jruby.RubyObject;

import com.rmxp4droid.BaseConf;
import com.rmxp4droid.pub.control.interfaces.IMusicControl;
import com.rmxp4droid.pub.control.interfaces.IRenderControl;




/**
 * 
 */
public class Ycore {
	
	public static String getScript=BaseConf.RXDataScript;
	public static File getPath=new File(BaseConf.WORK_FOLDER);
	
	public static String ScriptCacheFolder=BaseConf.ScriptCacheFolder;
	public static String ScriptCacheIndex=BaseConf.ScriptCacheIndex;
	
	public static Font defaultFont=new Font();
	public static final Color YCORE_WHITE = new Color(255, 255, 255);
	public static final  Color YCORE_BLACK=new Color(0, 0, 0, 0);
	private static final String []loadDataName={"Data/Actors.rxdata","Data/Classes.rxdata","Data/Skills.rxdata","Data/Items.rxdata","Data/Weapons.rxdata","Data/Armors.rxdata","Data/Enemies.rxdata","Data/Troops.rxdata","Data/States.rxdata","Data/Animations.rxdata","Data/Tilesets.rxdata","Data/CommonEvents.rxdata","Data/System.rxdata"};
	private static final Map<String,RubyObject> mxcache=new HashMap<String,RubyObject>();//保存后面load_data的关键对象
	
	public static int getWidth()
	{
		return BaseConf.WIDTH;
	}
	public static int getHeight()
	{
		return BaseConf.HEIGHT;
	}
	public static void putLoadDataMap(String objectName,RubyObject rubyObject)
	{
		if(BaseConf.USEMemoryScript)
		{
			if(objectName!=null)
			{
				for(int i=0;i<loadDataName.length;i++)
				{
					if(objectName.toUpperCase(Locale.US).equals(loadDataName[i].toUpperCase(Locale.US)))
					{
						mxcache.put(objectName, rubyObject);
						
					}
					
				}
			}
		}
	}
	public static RubyObject getLoadDataMap(String objectName)
	{
		if(BaseConf.USEMemoryScript)
		{
		return (RubyObject)mxcache.get(objectName);
		}else
		{
			return null;
		}
	}
	

	/**
	 * Basic Settings
	 */
	public static boolean musik = true, sound = true, fullscreen = false, smoothmodus = true, debug = false,
			active = true;
	
	
	/**
	 * Init the system
	 * 
	 * @param errors
	 */
	public static void saveSettings() {
	}

	
	/**
	 * exit game
	 */
	public static void exit()
	{
		System.exit(0);
		
	}
	
	
	private static IMusicControl iMusicControl;
	public static IMusicControl getiMusicControl() {
		return iMusicControl;
	}
	public static void setiMusicControl(IMusicControl iMusicControl) {
		Ycore.iMusicControl = iMusicControl;
	}
	
	private static IRenderControl iRenderControl;
	public static IRenderControl getiRenderControl() {
		return iRenderControl;
	}
	public static void setiRenderControl(IRenderControl iRenderControl) {
		Ycore.iRenderControl = iRenderControl;
	}
	

	




	
}
