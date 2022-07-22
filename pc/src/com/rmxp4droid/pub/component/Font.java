/**
 * 
 */
package com.rmxp4droid.pub.component;

import org.jruby.RubyObject;








public class Font {


	/**
	 * The font name. The default is "MS PGothic". Include an array of strings
	 * to specify multiple fonts to be used in a desired order.
	 */

	public static RubyObject default_name = null;
	// TODO include Array support

	/**
	 * The font size. The default is 24 points.
	 */
	public static int default_size = 24;

	/**
	 * The bold flag. The default is FALSE.
	 */
	public static boolean default_bold = false;

	/**
	 * The italic flag. The default is FALSE.
	 */
	public static boolean default_italic = false;

	/**
	 * The flag for shadow text. The default is TRUE. When enabled, a black
	 * shadow will be drawn to the bottom right of the character.
	 */
	public static boolean default_shadow = false;

	/**
	 * The font color (Color). Alpha values may also be used. The default is
	 * (255,255,255,255). Alpha values are also used when drawing shadow text.
	 */
	public static Color default_color = Color.WHITE;

	public RubyObject name;
	public int size;
	public boolean bold, italic, shadow;
	public Color color;

	/**
	 * Creates a Font object.
	 */
	public Font() {
		this("simkai", default_size);
//		if(default_name!=null)
//		{
//			for(int i=0;i<default_name.size();i++)
//			{	
//				if(Font.isExist(default_name.get(i).toString()))
//				{
//				
//					this.name=default_name.get(i).toString();
//					break;
//				}
//			}
//			 
//		}
	}

	/**
	 * Creates a Font object.
	 * 
	 * @param name
	 */

	public Font(String name) {
		this(name, default_size);
	}

	/**
	 * Creates a Font object.
	 * 
	 * @param name
	 * @param size
	 */
	public Font(String name, int size) {
		super();
		this.size = size;
		bold = default_bold;
		italic = default_italic;
		color = default_color;
	}

	/**
	 * Returns TRUE when the specified font exists within the system.
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isExist(String name) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Font [name=");
		builder.append(name);
		builder.append(", size=");
		builder.append(size);
		builder.append(", bold=");
		builder.append(bold);
		builder.append(", italic=");
		builder.append(italic);
		builder.append(", color=");
		builder.append(color);
		builder.append("]");
		return builder.toString();
	}

}
