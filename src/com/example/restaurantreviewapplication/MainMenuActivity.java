package com.example.restaurantreviewapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class MainMenuActivity extends Activity {
	private ImageButton findRestaurantsButton;
	private ImageButton manageFriendsButton;
	private ImageButton manageAccountButton;
	private ImageButton logOutButton;
	UserApplication app;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
		
		//end activity
		finish();
		
		//call MainActivity.class
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
		findRestaurantsButton = (ImageButton) findViewById(R.id.findRestaurants);
		manageFriendsButton = (ImageButton) findViewById(R.id.manageFriends);
		manageAccountButton = (ImageButton) findViewById(R.id.manageAccount);
		logOutButton = (ImageButton) findViewById(R.id.FriendSearchButton);
		
	}
}
