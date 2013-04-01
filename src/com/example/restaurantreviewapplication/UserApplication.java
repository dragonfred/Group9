package com.example.restaurantreviewapplication;

import java.util.ArrayList;
import java.util.UUID;

import com.example.restaurantreviewapplication.Friend;
import com.example.restaurantreviewapplication.Restaurant;

import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class UserApplication extends Application {
	private User currentUser;
	private Restaurants restaurants;	
	private Friend selectedFriend;
	private boolean verified;
	private Restaurant selectedRestaurant;
	private ArrayList<Message> messages;
	
	//testing map - need to change restaurant to get a location
	public Location a;
	
	@Override
	public void onCreate() {
		super.onCreate();
		currentUser = new User();
		restaurants = new Restaurants();
		//friendList = new ArrayList<Friend>();
		verified = false;
	}
	
	public ArrayList<Restaurant> getRestaurants() {
		return restaurants.getRestaurants();
	}
	public void setRestaurants(ArrayList<Restaurant> restaurants) {
		this.restaurants.setRestaurants(restaurants);
	}

	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public String getUserId() {
		return currentUser.getUserId();
	}
	public void setUserId(String userId) {
		this.currentUser.setUserId(userId);
	}
	public String getUsername() {
		return currentUser.getUsername();
	}
	public void setUsername(String username) {
		this.currentUser.setUsername(username);
	}
	public String getPassword() {
		return currentUser.getPassword();
	}
	public void setPassword(String password) {
		this.currentUser.setPassword(password);
	}

	public ArrayList<Friend> getFriendList() {
		return currentUser.getFriendList();
	}
	public void setFriendList(ArrayList<Friend> friendList) {
		this.currentUser.setFriendList(friendList);
	}
	
	public Restaurant getSelectedRestaurant() {
		return selectedRestaurant;
	}
	
	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

	public Friend getSelectedFriend() {
		return selectedFriend;
	}

	public void setSelectedFriend(Friend selectedFriend) {
		this.selectedFriend = selectedFriend;
	}

	public void setSelectedRestaurant(Restaurant selectedRestaurant) {
		this.selectedRestaurant = selectedRestaurant;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
}
