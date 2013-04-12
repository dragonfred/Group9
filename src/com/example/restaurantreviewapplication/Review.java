/**
 * 
 */
package com.example.restaurantreviewapplication;

import java.util.UUID;

import android.graphics.Bitmap;

/**
 * @author Matt
 *
 */
public class Review implements Storable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6399639411788272690L;
	private String reviewer;
	private String review;
	private float tasteRating;
	private float serviceRating;
	private float cleanlinessRating;
	private float overallRating;
	//private Bitmap image;
	private byte[] imageByteArray;
	private UUID uuid;
	  
	public UUID getUuid() {
		if(this.uuid==null) this.uuid = UUID.randomUUID();
		return this.uuid;
	}
	
	public Review() {
		this.review = "";
		this.tasteRating = 0.0f;
		this.serviceRating = 0.0f;
		this.cleanlinessRating = 0.0f;
		this.overallRating = 0.0f;
	}
	
	/**
	 * @param review
	 * @param tasteRating
	 * @param serviceRating
	 * @param cleanlinessRating
	 * @param overallRating
	 */
	public Review(String review, float tasteRating, float serviceRating,
			float cleanlinessRating, float overallRating) {
		this.review = review;
		this.tasteRating = tasteRating;
		this.serviceRating = serviceRating;
		this.cleanlinessRating = cleanlinessRating;
		this.overallRating = overallRating;
	}
	
	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	/**
	 * @return the review
	 */
	protected String getReview() {
		return review;
	}
	
	/**
	 * @param review the review to set
	 */
	protected void setReview(String review) {
		this.review = review;
	}
	
	/**
	 * @return the tasteRating
	 */
	protected float getTasteRating() {
		return tasteRating;
	}
	
	/**
	 * @param tasteRating the tasteRating to set
	 */
	protected void setTasteRating(float tasteRating) {
		this.tasteRating = tasteRating;
	}
	
	/**
	 * @return the serviceRating
	 */
	protected float getServiceRating() {
		return serviceRating;
	}
	
	/**
	 * @param serviceRating the serviceRating to set
	 */
	protected void setServiceRating(float serviceRating) {
		this.serviceRating = serviceRating;
	}
	
	/**
	 * @return the cleanlinessRating
	 */
	protected float getCleanlinessRating() {
		return cleanlinessRating;
	}
	
	/**
	 * @param cleanlinessRating the cleanlinessRating to set
	 */
	protected void setCleanlinessRating(float cleanlinessRating) {
		this.cleanlinessRating = cleanlinessRating;
	}
	
	/**
	 * @return the overallRating
	 */
	protected float getOverallRating() {
		return overallRating;
	}
	@Override
	public String toString() {
		return review;
	}
	
	/**
	 * @param overallRating the overallRating to set
	 */
	protected void setOverallRating(float overallRating) {
		this.overallRating = overallRating;
	}

	public Bitmap getImage() {
		//return image;
		return Server.byteArrayToBitmap(imageByteArray);
	}

	public void setImage(Bitmap image) {
		//this.image = image;
		this.imageByteArray = Server.bitmapToByteArray(image);
	}	
}