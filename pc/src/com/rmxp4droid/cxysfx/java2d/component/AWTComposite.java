package com.rmxp4droid.cxysfx.java2d.component;

import java.awt.Composite;

public class AWTComposite {
	Composite composite=null;
	public AWTComposite(Composite composite)
	{
		this.composite=composite;
	}
	public Composite getComposite() {
		return composite;
	}
	
}
