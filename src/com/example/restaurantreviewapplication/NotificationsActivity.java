package com.example.restaurantreviewapplication;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class NotificationsActivity extends Activity {

	String[] notifications = { "Notification 1", "Notification 2",
			"Notification 3", "Notification 4", "Notification 5",
			"Notification 6", "Notification 7", "Notification 8" };

	private UserApplication app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notifications);
		app = (UserApplication) getApplication();
		seedNotification();
		ListView reviewList = (ListView) findViewById(R.id.notificationsList);
		ArrayList<Message> messages = app.getMessages();
		ArrayAdapter<Message> adapter = new ArrayAdapter<Message>(this,
				android.R.layout.simple_list_item_1, messages);
		
		if(messages == null){
			// show message "no new messages"
			Intent intent = new Intent(this, ManageFriendsActivity.class);
			startActivity(intent);
		}else{
			reviewList.setAdapter(adapter);
		}
		reviewList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				nextScreen(position);

			}
		});

	}

	private void nextScreen(int position) {
		//app.setSelectedRestaurant(restaurants.get(position));
		//Intent intent = new Intent(this, RestaurantActivity.class);
		//startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_notifications, menu);
		return true;
	}
	
	
	//Junk method to add notifications.  Delete once server is established.
	private void seedNotification(){
		ArrayList<Message> l = new ArrayList<Message>();
		Message m;
		String s1, s2;
		String s3 = "";
		
		for(int i = 0 ; i < 3 ; i++){
			s1 = "" + i*2;
			s2 = "" + i*3;
			s3 = s3 + "yo ";
			m = new Message(s1, s2, s3);
			l.add(m);
		}
		app.setMessages(l);
		
	}

}
