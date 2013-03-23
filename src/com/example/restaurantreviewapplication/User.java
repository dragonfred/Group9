/**
 * 
 */
package com.example.restaurantreviewapplication;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Matt
 *
 */
public class User implements Storable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 440442999209371288L;
	private String userId;
	private String username;
	private String password;
	private String facebookUserID;
	private String facebookPassword;
	private ArrayList<Friend> friendList;
	
	/**
	 * 
	 */
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	
	public User(String userId, String username, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
	}


	private UUID uuid;
	  
	public UUID getUuid() {
		if(this.uuid==null) this.uuid = UUID.randomUUID();
		return this.uuid;
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

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the facebookUserID
	 */
	public String getFacebookUserID() {
		return facebookUserID;
	}

	/**
	 * @param facebookUserID the facebookUserID to set
	 */
	public void setFacebookUserID(String facebookUserID) {
		this.facebookUserID = facebookUserID;
	}

	/**
	 * @return the facebookPassword
	 */
	public String getFacebookPassword() {
		return facebookPassword;
	}

	/**
	 * @param facebookPassword the facebookPassword to set
	 */
	public void setFacebookPassword(String facebookPassword) {
		this.facebookPassword = facebookPassword;
	}

	/**
	 * @return the friendList
	 */
	public ArrayList<Friend> getFriendList() {
		return friendList;
	}

	/**
	 * @param friendList the friendList to set
	 */
	public void setFriendList(ArrayList<Friend> friendList) {
		this.friendList = friendList;
	}

}
