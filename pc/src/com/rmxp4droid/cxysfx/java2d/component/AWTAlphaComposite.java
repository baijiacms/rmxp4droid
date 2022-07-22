package com.rmxp4droid.cxysfx.java2d.component;

import java.awt.AlphaComposite;

public class AWTAlphaComposite {
	private AlphaComposite alphaComposite;

	public static final int  SRC_OVER=AlphaComposite.SRC_OVER;
	public AWTAlphaComposite(int rule, float alpha)
	{
		alphaComposite=AlphaComposite.getInstance(rule, alpha);
	}
	public AlphaComposite getComposite()
	{
		return alphaComposite;
	}
} 
