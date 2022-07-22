/**
 * 
 */
package com.rmxp4droid.pub.component;


public class Rect {

	public int x, y, width, height;

	/**
	 * Clone a Rect object.
	 * 
	 * @param rect
	 */
	public Rect(final Rect rect) {
		this(rect.x, rect.y, rect.width, rect.height);
	}

	/**
	 * Creates a new Rect object.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Rect(final int x, final int y, final int width, final int height) {
		set(x, y, width, height);
	}

	/**
	 * Sets all parameters at once.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void set(final int x, final int y, final int width, final int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rect [x=" + x + ", y=" + y + ", width=" + width + ", height="
				+ height + "]";
	}
}
