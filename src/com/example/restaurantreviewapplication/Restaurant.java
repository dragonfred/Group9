/**
 * 
 */
package com.example.restaurantreviewapplication;

import java.util.ArrayList;

/**
 * @author Matt
 *
 */
public class Restaurant {

	private String restaurantId;
	private ArrayList<Review> reviews;
	
	/**
	 * 
	 */
	public Restaurant() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the restaurantId
	 */
	public String getRestaurantId() {
		return restaurantId;
	}

	/**
	 * @param restaurantId the restaurantId to set
	 */
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	/**
	 * @return the reviews
	 */
	public ArrayList<Review> getReviews() {
		return reviews;
	}

	/**
	 * @param reviews the reviews to set
	 */
	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public String AddReview(Review review)
	{
		
		//if review was successful
		return "Thank You for your review!";
		
		//else
		//return "Sorry your review cannot be processed at this time."
	}
	
	public ArrayList<Review> ListReviews(Restaurant restaurant)
	{
		//needs to be implemented
		return reviews;
	}
	
	/*public Review GetReview(Restaurant restaurant)
	{
		//needs to be implemented
		//return  
	}*/
}
