package com.example.restaurantreviewapplication;

import java.util.ArrayList;
import java.util.UUID;

import com.example.restaurantreviewapplication.Friend;
import com.example.restaurantreviewapplication.Restaurant;

import android.app.Application;

public class UserApplication extends Application {
	private String userId;
	private String username;
	private String password;
	private String facebookUserID;
	private String facebookPassword;
	private ArrayList<Friend> friendList;
	private boolean verified;
	private ArrayList<Restaurant> restaurants;
	private Restaurant selectedRestaurant;
	

	
	private UUID uuid;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		friendList = new ArrayList<Friend>();
		verified = false;
	}
	
	public UUID getUuid() {
		if(this.uuid==null) this.uuid = UUID.randomUUID();
		return this.uuid;
	}
	
	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(ArrayList<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		System.out.println(username);
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFacebookUserID() {
		return facebookUserID;
	}
	public void setFacebookUserID(String facebookUserID) {
		this.facebookUserID = facebookUserID;
	}
	public String getFacebookPassword() {
		return facebookPassword;
	}
	public void setFacebookPassword(String facebookPassword) {
		this.facebookPassword = facebookPassword;
	}
	public ArrayList<Friend> getFriendList() {
		return friendList;
	}
	public void setFriendList(ArrayList<Friend> friendList) {
		this.friendList = friendList;
	}
	
	public Restaurant getSelectedRestaurant() {
		return selectedRestaurant;
	}

	public void setSelectedRestaurant(Restaurant selectedRestaurant) {
		this.selectedRestaurant = selectedRestaurant;
	}
	
}
