/**
 * 
 */
package com.example.restaurantreviewapplication;

import java.io.File;

import android.graphics.Bitmap;

/**
 * @author Matt
 *
 */
public class Picture {

	private File fileName;
	private Bitmap photo;
	
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

	public Bitmap getPhoto() {
		return photo;
	}

	public void setPhoto(Bitmap photo) {
		this.photo = photo;
	}

}
