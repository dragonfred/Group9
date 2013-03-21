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
	private String name;
	private String address;
	private String phone;
	private String location;
	private ArrayList<Review> reviews;
	
	/**
	 * 
	 */
	public Restaurant(String name, String address, String phone, String location) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.location = location;
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
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return name + " - " + address;
	}
	
	
	
	/*public Review GetReview(Restaurant restaurant)
	{
		//needs to be implemented
		//return  
	}*/
}
