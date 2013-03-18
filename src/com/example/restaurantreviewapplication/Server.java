package com.example.restaurantreviewapplication;

import java.util.ArrayList;

import android.app.Activity;





public class Server {
	private UserApplication app;
	private Activity activity;
	
	public Server(Activity act) {
		super();
		
		this.activity = act;
		// Now here you can get getApplication()
	    
		app = (UserApplication) act.getApplication();
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean checkUserPassword(){
		boolean result = true;
		String username = app.getUsername();
		String password = app.getPassword();
		
		//check user/password on server return false for fail;
		
		// if user/password is correct return true
		
		//returned from server: userid and add to app.setuserid
		
		//this tells the rest of the application it is a verified user:
		app.setVerified(true);
		
		
		return result;
		
	}
	
	
	public void getRestaurants(int zip, String keyword) {
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		int i;

		Restaurant a;

		
		for (i = 0; i < 10; i++) {
			a = new Restaurant("Restaurant" + i, "Address", "407-555-1212", "GPS Coords");
			restaurants.add(a);
		}
		
		app.setRestaurants(restaurants);
//		return restaurants;
	}
	
}
