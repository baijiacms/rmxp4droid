/**
 * 
 */
package com.rmxp4droid.pub.component;




public class Color implements java.io.Serializable,Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;;

	public static Color WHITE =Ycore.YCORE_WHITE;

	/**
	 * The red/green/blue/alpha value (0-255). Values out of range are
	 * automatically corrected.
	 */
	public double red, green, blue, alpha;

	// TODO auto correct

	/**
	 * Creates a Color object. If alpha is omitted, it is assumed at 255.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 */
	public Color(final double red, final double green, final double blue) {
		setColor(red, green, blue);
		alpha = 255;
	}

	/**
	 * Creates a Color object. If alpha is omitted, it is assumed at 255.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public Color(final double red, final double green, final double blue,
			final double alpha) {
		setColor(red, green, blue, alpha);
	}

	/**
	 * Sets all components at once.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void setColor(final double red, final double green, final double blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	/**
	 * Sets all components at once.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void setColor(final double red, final double green,
			final double blue, final double alpha) {
		setColor(red, green, blue);
		this.alpha = alpha;
	}

	public void set(double r, double g, double b)
	{
	     this.red = r;
	     this.green = g;
	     this.blue = b;
	     this.alpha = 255;
	}
	public void set(double r, double g, double b, double a)
	{
	     this.red = r;
	     this.green = g;
	     this.blue = b;
	     this.alpha = a;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Color [red=" + red + ", green=" + green + ", blue=" + blue
				+ ", alpha=" + alpha + "]";
	}

	/**
	 * Code from MACL
	 * 
	 * @param r
	 * @param g
	 * @param b
	 */
	public static Color to_hsb(double r, double g, double b) {
		// Correction if Greater than 255
		// r,g,b = [r,g,b].collect! {|color| color = 255 if color > 255}
		// Get Maximum
		double max = Math.max(r, Math.max(g, b));
		// Get Minimum
		double min = Math.min(r, Math.min(g, b));
		// Calculate hue
		double h = 0;
		if (max == r && g >= b)
			h = 60 * (g - b) / (max - min);
		if (max == r && g < b)
			h = 60 * (g - b) / (max - min) + 360;
		if (max == g)
			h = 60 * (b - r) / (max - min) + 120;
		if (max == b)
			h = 60 * (r - g) / (max - min) + 240;
		if (max == min)
			h = 0;
		// Calculate Saturation
		double s = max == 0 ? 0 : 100 * (1 - min / max);
		// Calculate Brightness
		double v = 100 * max / 255;
		// Return Hue, Saturation, And Value
		return new Color(h, s, v);
	}

	/**
	 * Code from MACL
	 * 
	 * @param hue
	 * @param sat
	 * @param bri
	 * @return
	 */
	public static Color hsb_to_rgb(double hue, double sat, double bri) {
		// Convert All To Floats
		// double hue = hueI, sat = satI, bri = briI;
		// Ensure Hue is [0, 360)
		hue %= 360;
		// Reduce to [0, 1]
		sat = sat > 100 ? 1.0 : sat / 100;
		bri = bri > 100 ? 1.0 : bri / 100;
		// Get Sector
		int sector = (int) (hue / 60);
		double f = (int) (hue / 60 - sector);
		double p = (int) (bri * (1 - sat));
		double q = (int) (bri * (1 - f * sat));
		double t = (int) (bri * (1 - (1 - f) * sat));
		// Branch By Sector and get r,g,b values
		double r = 0, g = 0, b = 0;
		switch (sector) {
		case 0:
			r = bri;
			g = t;
			b = p;
			break;
		case 1:
			r = bri;
			g = t;
			b = p;
			break;
		case 2:
			r = q;
			g = bri;
			b = p;
			break;
		case 3:
			r = p;
			g = bri;
			b = t;
			break;
		case 4:
			r = p;
			g = q;
			b = bri;
			break;
		case 5:
			r = t;
			g = p;
			b = bri;
			break;
		case 6:
			r = bri;
			g = p;
			b = q;
			break;
		}
		// Set Color
		// color = [r,g,b]
		// Convert to [0, 255] Range
		// color.collect! {|value| value * 255}
		// Return Color
		return new Color(r * 255, g * 255, b * 255);
	}
}
