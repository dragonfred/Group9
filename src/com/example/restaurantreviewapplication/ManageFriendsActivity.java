package com.example.restaurantreviewapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ManageFriendsActivity extends Activity {

	private Button showFriendsButton;
	private Button findFriendsButton;
	private Button viewNotificationsButton;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_friends);
		setupViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_manage_friends, menu);
		return true;
	}
	
	public void showFriendsButtonHandler(View v){
		
		Intent intent = new Intent(this, CurrentFriendsActivity.class);
		startActivity(intent);
		
	}
	
	public void findFriendsButtonHandler(View v){
		
		Intent intent = new Intent(this, FindFriendActivity.class);
		startActivity(intent);
		
	}
	
	public void viewNotificationsButtonHandler(View v){
		
		Intent intent = new Intent(this, NotificationsActivity.class);
		startActivity(intent);
		
	}

	private void setupViews(){
		showFriendsButton = (Button) findViewById(R.id.showFriendsButton);
		findFriendsButton = (Button) findViewById(R.id.findFriendsButton);
		viewNotificationsButton = (Button) findViewById(R.id.viewNotificationsButton);
		
	}
}
