package com.rmxp4droid.cxysfx.core.rbruntime;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.util.KCode;

import com.rmxp4droid.BaseConf;

public class RunScriptEngineV2 implements RunScriptEngine{
	private  Ruby interpreter =  null;
	public RunScriptEngineV2()
	{
		final String[] args=new String[]{"-J-XX:+TieredCompilation","-J-XX:TieredStopAtLevel=1","-J-noverify","-Xcompile.invokedynamic=false"};
		 RubyInstanceConfig config = new RubyInstanceConfig() {{
			 setCurrentDirectory(BaseConf.WORK_FOLDER);
			 setKCode(KCode.UTF8);
			 setCompileMode(RubyInstanceConfig.CompileMode.OFF);
//           setObjectSpaceEnabled(true); // useful for code completion inside the IRB
           setArgv(args);
       }};
     
       interpreter= Ruby.newInstance(config);
  
	}

	@Override
	public void runScript(String script, String fileName) {
		  interpreter.evalScriptlet(script);
	}
}
