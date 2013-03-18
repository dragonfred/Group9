package com.example.restaurantreviewapplication;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListRestaurantsActivity extends Activity {
	
//	String[] restaurants = {"Restaurant 1","Restaurant 2","Restaurant 3","Restaurant 4",
//						"Restaurant 5","Restaurant 6","Restaurant 7","Restaurant 8"};
	private UserApplication app;
	private Server serverConnection;
	ArrayList<Restaurant> restaurants;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_restaurants);
		app = (UserApplication)getApplication();
		serverConnection = new Server(this);
	
		ListView reviewList = (ListView) findViewById(R.id.restaurantsListView);
		restaurants = app.getRestaurants();
		ArrayAdapter<Restaurant> adapter = new ArrayAdapter<Restaurant>(this, android.R.layout.simple_list_item_1, restaurants);				
		reviewList.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_restaurants, menu);
		return true;
	}
	


}
