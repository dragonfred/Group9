package com.example.restaurantreviewapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends Activity {
	private Button findRestaurantsButton;
	private Button manageFriendsButton;
	private Button manageAccountButton;
	private Button logOutButton;
	UserApplication app;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		setupViews();
		app = (UserApplication)getApplication();
	}

	public void findRestaurantsButtonHandler(View v){
		Intent intent = new Intent(this, FindRestaurantsActivity.class);
		startActivity(intent);
	}
	
	public void manageFriendsButtonHandler(View v){
		Intent intent = new Intent(this, ManageFriendsActivity.class);
		startActivity(intent);	
	}
	
	public void manageAccountButtonHandler(View v){
		Intent intent = new Intent(this, ManageAccountActivity.class);
		startActivity(intent);
	}
	
	public void logOutButtonHandler(View v){
		Server.LogOut(app.getCurrentUser());
		app.setCurrentUser(null);
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_menu, menu);
		return true;
	}
	
	//setup buttons
	private void setupViews(){
		findRestaurantsButton = (Button) findViewById(R.id.findRestaurants);
		manageFriendsButton = (Button) findViewById(R.id.manageFriends);
		manageAccountButton = (Button) findViewById(R.id.manageAccount);
		logOutButton = (Button) findViewById(R.id.FriendSearchButton);
		
	}
}
