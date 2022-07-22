package com.rmxp4droid;


public class BaseConf {
	// 工作空间
	public static final String WORK_FOLDER = "x:/sdcard/rmxp4droid/";

	// 字体
	public static final String FontFile = "simkai.ttf";
	// 脚本文件
	public static final String RXDataScript = "/Data/Scripts.rxdata";

	public static final String ScriptCacheFolder = WORK_FOLDER
			+ "/Data/ScriptCache/";
	public static final String RXDataBaseFolder="/Data/";
	public static final String ScriptCacheIndex = "rmxp4scriptCache.rb";
	//日志记录文件
	public static final String errorfile = WORK_FOLDER+"error.log";
	// 变量
	// 是否使用缓存方式来读取脚本(缓存Scripts.rxdata下次加快读取速度)
	public static boolean USECacheScript = false;//缓存出现乱码暂时屏蔽

	public static boolean USEMemoryScript = true;//保存后面load_data的关键对象
	public static final int WIDTH = 640;
	public static int FontSize = 24;
	public static final int HEIGHT = 480;
	public static int INITGAME = 0;// 判断是否已经初始化 0初始化 1初始化完成等待脚本加载完成  2全部加载完成正常开始游戏
	public static String COMPILE_NAME = "OFF";// 使用编译什么编译JIT|FORCE|OFF 默认default
	public static int LOADING_SEEKBAR_MAX=0;
	public static int LOADING_SEEKBAR_INDEX=0;
	public static final int FPS=40;

	
	public static final String CODER_RUBYKEY="data/ruby.key";
	public static final String CODER_ENCODE_DATE_FILE=WORK_FOLDER+"rmxp4droid.coredata";
	public static final boolean CODER_DOENCRYPT=false;//是否使用加密 android版这项功能不可用
	
	
	
	public static final String copyright="copyright:Http://blog.csdn.net/cxy1238";
	
	public static final String VERSIONS="20120219";//版本号同时是脚本加密的密钥(更新时改) 脚本修改时修改它
}
