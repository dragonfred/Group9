package com.example.restaurantreviewapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import com.example.restaurantreviewapplication.Message;

public class CreateMessageActivity extends Activity {

	Message newMessage = new Message();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_message);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_message, menu);
		return true;
	}

}