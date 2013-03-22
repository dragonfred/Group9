/**
 * 
 */
package com.example.restaurantreviewapplication;

/**
 * @author Matt
 *
 */
public class Friend {

	private String userId;
	
	/**
	 * 
	 */
	public Friend() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String SendMessage(String message)
	{
		//if message sent succeeded.
		return "Message sent successfully";
		
		//else
		//return "Message sent unsuccessfully";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return userId;
	}
	
	

}
