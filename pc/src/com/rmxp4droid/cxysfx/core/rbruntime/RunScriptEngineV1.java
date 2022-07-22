package com.rmxp4droid.cxysfx.core.rbruntime;


import org.jruby.RubyInstanceConfig.CompileMode;
import org.jruby.embed.ScriptingContainer;
import org.jruby.util.KCode;

import com.rmxp4droid.BaseConf;

public class RunScriptEngineV1 implements RunScriptEngine{
	private  ScriptingContainer interpreter =  null;
	public RunScriptEngineV1()
	{
		 interpreter = new ScriptingContainer();
		    if(CompileMode.FORCE.name().equalsIgnoreCase(BaseConf.COMPILE_NAME))
		    {
		    	interpreter.setCompileMode(CompileMode.FORCE);
		    }
		    if(CompileMode.JIT.name().equalsIgnoreCase(BaseConf.COMPILE_NAME))
		    {
		    	interpreter.setCompileMode(CompileMode.JIT);
		    }
		    if(CompileMode.OFF.name().equalsIgnoreCase(BaseConf.COMPILE_NAME))
		    {
		    	interpreter.setCompileMode(CompileMode.OFF);
		    }
			interpreter.setKCode(KCode.UTF8);
			interpreter.setCurrentDirectory(BaseConf.WORK_FOLDER);
	}
	public void runScript(String script,String fileName)
	{
			interpreter.runScriptlet(script);
	
	}
}
