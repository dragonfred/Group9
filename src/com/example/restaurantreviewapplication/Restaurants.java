/**
 * 
 */
package com.example.restaurantreviewapplication;

import java.util.ArrayList;


/**
 * @author Matt
 *
 */
public class Restaurants {

	private ArrayList<Restaurant> restaurants;
	
	/**
	 * 
	 */
		
	public Restaurants() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the restaurants
	 */
	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}

	/**
	 * @param restaurants the restaurants to set
	 */
	public void setRestaurants(ArrayList<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	//find restaurants using GPS
	public ArrayList<Restaurant> FindRestaurants()
	{
		return restaurants;
	}
	
	//find restuarants using string
	public ArrayList<Restaurant> FindRestaurants(String keyword)
	{
		return restaurants; 
	}
	
	public ArrayList<Restaurant> FindRestaurants(int zipCode)
	{
		return restaurants;
	}
}
