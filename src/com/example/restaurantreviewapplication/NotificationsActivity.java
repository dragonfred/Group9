package com.example.restaurantreviewapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NotificationsActivity extends Activity {

	String[] notifications = {"Notification 1","Notification 2","Notification 3","Notification 4",
							  "Notification 5","Notification 6","Notification 7","Notification 8"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ListView reviewList = (ListView)findViewById(R.id.notificationsList);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, notifications);
		
		reviewList.setAdapter(adapter);
		
		setContentView(R.layout.activity_list_restaurants);
		
		setContentView(R.layout.activity_notifications);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_notifications, menu);
		return true;
	}

}
