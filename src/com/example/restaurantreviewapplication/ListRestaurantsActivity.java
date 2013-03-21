package com.example.restaurantreviewapplication;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListRestaurantsActivity extends Activity {
	
//	String[] restaurants = {"Restaurant 1","Restaurant 2","Restaurant 3","Restaurant 4",
//						"Restaurant 5","Restaurant 6","Restaurant 7","Restaurant 8"};
	private UserApplication app;
	ArrayList<Restaurant> restaurants;
	ListView reviewList;
	ArrayAdapter<Restaurant> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_restaurants);
		app = (UserApplication)getApplication();
		
	
		reviewList = (ListView) findViewById(R.id.restaurantsListView);
		restaurants = app.getRestaurants();
		adapter = new ArrayAdapter<Restaurant>(this, android.R.layout.simple_list_item_1, restaurants);				
		reviewList.setAdapter(adapter);
		
		reviewList.setOnItemClickListener(
				new OnItemClickListener(){
					
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						nextScreen(position);
						
					}
				}
				);
		
	}

	private void nextScreen(int position){
		app.setSelectedRestaurant(restaurants.get(position));
		Intent intent = new Intent(this, RestaurantActivity.class);
		startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_restaurants, menu);
		return true;
	}
	



}
