package com.example.restaurantreviewapplication;

import java.util.ArrayList;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
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
	
	// needs work	
	public void searchNearbyButtonHandler(View V){
		searchNearby.setText("Waiting on GPS");
		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				// Called when a new location is found by the network location
				// provider.
				makeUseOfNewLocation(location);
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};

		// Register the listener with the Location Manager to receive location
		// updates
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
	}

	public void searchButtonHandler(View V){
		String keyword;
		int zip = 0;
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		
		keyword = keywordText.getText().toString();

		if (!(keyword.trim().length() == 0)) {
			String rawzip = zipCodeText.getText().toString();

			if (!(rawzip.trim().length() == 0)) {
				zip = Integer.parseInt(rawzip);
			}
			//serverConnection.getRestaurants(zip, keyword);
			
			restaurants = Server.getRestaurantsByZipKeyword(zip, keyword);
			if(restaurants != null){
				app.setRestaurants(restaurants);
				Intent intent = new Intent(this, ListRestaurantsActivity.class);
				startActivity(intent);
			}else{
				//pop up no restaurants found
			}
		}else{
			//pop up please enter keyword
		}
		
	}
	
	protected void makeUseOfNewLocation(Location location) {
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		restaurants = Server.getRestaurantsByLocation(location);
	
		searchNearby.setText("Search Nearby");
		
		if (restaurants != null) {
			app.setRestaurants(restaurants);

			Intent intent = new Intent(this, ListRestaurantsActivity.class);
			startActivity(intent);
		} else {
			// pop up message saying no restaurants found

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
