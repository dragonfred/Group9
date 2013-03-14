package com.example.restaurantreviewapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CurrentFriendsActivity extends Activity {

	String[] friends = {"friend 1", "friend 2", "friend 3", "friend 4", "friend 5",
						"friend 6", "friend 7", "friend 8", "friend 9", "friend 10", };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ListView reviewList = (ListView)findViewById(R.id.restaurantsList);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, friends);
		
		reviewList.setAdapter(adapter);
		
		setContentView(R.layout.activity_list_restaurants);
		
		setContentView(R.layout.activity_current_friends);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_current_friends, menu);
		return true;
	}

}