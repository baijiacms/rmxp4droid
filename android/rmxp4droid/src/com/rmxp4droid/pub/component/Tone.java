/**
 * 
 */
package com.rmxp4droid.pub.component;



/**
 * The RGBA color class. Each component is handled with a floating point value
 * (Float).
 * 
 * 
 */
// TODO change to floating

public class Tone implements java.io.Serializable,Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The red/green/blue value (-255-255). Values out of range are
	 * automatically corrected.s
	 */
	public double red, green, blue;

	// TODO auto correct

	/**
	 * The grayscale filter strength (0 to 255). Values out of range are
	 * automatically corrected. When this value is not 0, processing time is
	 * significantly longer than when using tone balance adjustment values
	 * alone.
	 */
	public double gray;

	/**
	 * Creates a Color object. If alpha is omitted, it is assumed at 255.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 */
	public Tone(double red, double green, double blue) {
		set(red, green, blue);
		gray = 0;
	}

	/**
	 * Creates a Color object. If alpha is omitted, it is assumed at 255.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @param gray
	 */
	public Tone(double red, double green, double blue, double gray) {
		set(red, green, blue, gray);
	}

	/**
	 * Sets all components at once.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public void set(double red, double green, double blue) {
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
	 * @param gray
	 */
	public void set(double red, double green, double blue, double gray) {
		set(red, green, blue);
		this.gray = gray;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tone other = (Tone) obj;
		if (Double.doubleToLongBits(blue) != Double
				.doubleToLongBits(other.blue))
			return false;
		if (Double.doubleToLongBits(gray) != Double
				.doubleToLongBits(other.gray))
			return false;
		if (Double.doubleToLongBits(green) != Double
				.doubleToLongBits(other.green))
			return false;
		if (Double.doubleToLongBits(red) != Double.doubleToLongBits(other.red))
			return false;
		return true;
	}

	/**
	 * Clone it
	 */
	@Override
	public Tone clone() {
		try {
			return (Tone) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tone [red=");
		builder.append(red);
		builder.append(", green=");
		builder.append(green);
		builder.append(", blue=");
		builder.append(blue);
		builder.append(", gray=");
		builder.append(gray);
		builder.append("]");
		return builder.toString();
	}
}
