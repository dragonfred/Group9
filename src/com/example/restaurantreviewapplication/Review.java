/**
 * 
 */
package com.example.restaurantreviewapplication;

/**
 * @author Matt
 *
 */
public class Review {
	
	private String review;
	private float tasteRating;
	private float serviceRating;
	private float cleanlinessRating;
	private float overallRating;


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
}
