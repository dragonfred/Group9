package com.example.restaurantreviewapplication;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FindRestaurantsActivity extends Activity {

	private Button searchNearby;
	private Button searchButton;
	private EditText zipCodeText;
	private EditText keywordText;
	private UserApplication app;
	//private Server2 serverConnection;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (UserApplication)getApplication();
		//serverConnection = new Server2(this);
		setContentView(R.layout.activity_find_restaurants);
		setupViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_find_restaurants, menu);
		return true;
	}
	
	private void setupViews(){
		searchNearby = (Button) findViewById(R.id.searchNearby);
		searchButton = (Button) findViewById(R.id.searchButton);
		zipCodeText = (EditText) findViewById(R.id.zipCodeText);
		keywordText = (EditText) findViewById(R.id.keywordText);
		
	}
	
	public void searchNearbyHandler(View V){

		//serverConnection.getRestaurants(0, "NA");
		// sets up dummy data for testing, replace with server call:
		getRestaurants(0, "NA");
		
		Intent intent = new Intent(this, ListRestaurantsActivity.class);
		startActivity(intent);
	}

	public void searchHandler(View V){
		String keyword;
		int zip = 0;

		keyword = keywordText.getText().toString();

		if (!(keyword.trim().length() == 0)) {
			String rawzip = zipCodeText.getText().toString();

			if (!(rawzip.trim().length() == 0)) {
				zip = Integer.parseInt(rawzip);
			}
			//serverConnection.getRestaurants(zip, keyword);
			
			getRestaurants(zip, keyword);
			
			Intent intent = new Intent(this, ListRestaurantsActivity.class);
			startActivity(intent);
		}
		
	}
	
	
	// Dummy data for testing.  Replace with call to server
	public void getRestaurants(int zip, String keyword) {
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		int i;

		Restaurant a;

		
		for (i = 0; i < 10; i++) {
			a = new Restaurant("Restaurant " + i, i*2 + " Main St", "407-555-1212", "GPS Coords");
			restaurants.add(a);
		}
		
		app.setRestaurants(restaurants);
//		return restaurants;
	}
}
