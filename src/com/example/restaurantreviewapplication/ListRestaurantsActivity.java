package com.example.restaurantreviewapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListRestaurantsActivity extends Activity {
	
	String[] restaurants = {"Restaurant 1","Restaurant 2","Restaurant 3","Restaurant 4",
						"Restaurant 5","Restaurant 6","Restaurant 7","Restaurant 8"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ListView reviewList = (ListView)findViewById(R.id.restaurantsList);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, restaurants);
		
		reviewList.setAdapter(adapter);
		
		setContentView(R.layout.activity_list_restaurants);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_restaurants, menu);
		return true;
	}

}
