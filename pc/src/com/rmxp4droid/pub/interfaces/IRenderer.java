/**
 * 
 */
package com.rmxp4droid.pub.interfaces;


import com.rmxp4droid.pub.component.Sprites;




/**
 * 
 */
public abstract class IRenderer {

	/**
	 * This Method will call every graphics update, if importent implement it,
	 * otherwise leave it empty
	 */
	public abstract void graphicsUpdate();



	/**
	 * Creates a bitmap object with the specified size.
	 * 
	 * @param width
	 * @param height
	 * @param container
	 */
	public abstract IBitmap createBitmap(int width, int height);

	/**
	 * Creates a bitmap object with the specified size.
	 * 
	 * @param width
	 * @param container
	 */
	public abstract IBitmap createBitmap(final String filename);

	/**
	 * Creates a Sprite object
	 * 
	 * @param container
	 */
	public abstract ISprite createSprite(Sprites container);

	/**
	 * Return the name of the renderer
	 * 
	 * @return
	 */
	public abstract String getName();


	
}
