/**
 * 
 */
package com.example.restaurantreviewapplication;

import java.io.File;

/**
 * @author Matt
 *
 */
public class Picture {

	private File fileName;
	
	/**
	 * 
	 */
	public Picture() {
		fileName = null;
	}

	/**
	 * @return the fileName
	 */
	public File getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(File fileName) {
		this.fileName = fileName;
	}

}
