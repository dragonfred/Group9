package com.example.restaurantreviewapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RestaurantActivity extends Activity {

	
	private Button viewMapButton;
	private Button readReviewsButton;
	private Button writeReviewsButton;
	private Button checkInButton;
	private TextView restaurantNameText;
	private TextView restaurantAddressText;
	private TextView restaurantPhoneNumberText;
	UserApplication app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant);
		//setup buttons and text views
		setupViews();
		//connect to global data
		app = (UserApplication)getApplication();
		//get the restaurant selected in previous screen
		Restaurant current = app.getSelectedRestaurant();	
		//output restaurant info onto screen
		restaurantNameText.setText(current.getName());
		restaurantAddressText.setText(current.getAddress());
		restaurantPhoneNumberText.setText(current.getPhone());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_restaurant, menu);
		return true;
	}

	public void viewMapButtonHandler(View v){
		
		Intent intent = new Intent(this, MapActivity.class);
		startActivity(intent);
	}
	
	public void readReviewsButtonHandler(View v){
		
		Intent intent = new Intent(this, ReadReviewActivity.class);
		startActivity(intent);
		
	}
	
	public void writeReviewsButtonHandler(View v){
		
		Intent intent = new Intent(this, WriteReviewActivity.class);
		startActivity(intent);
	}
	
	public void checkInButtonHandler(View v){
		
		//check if logged in to facebook account and make post..also increment # check-ins for restaurant by 1
		
//		Intent intent = new Intent(this, ManageFriendsActivity.class);
//		startActivity(intent);
		
	}
	
	private void setupViews(){
		restaurantNameText = (TextView) findViewById(R.id.reviewRestaurantName);
		restaurantAddressText = (TextView) findViewById(R.id.reviewRestaurantAddressText);
		restaurantPhoneNumberText = (TextView) findViewById(R.id.reviewPhoneNumber);
		viewMapButton = (Button) findViewById(R.id.MapButton);
		readReviewsButton = (Button) findViewById(R.id.readReviewsButton);
		writeReviewsButton = (Button) findViewById(R.id.writeReviewsButton);
		checkInButton = (Button) findViewById(R.id.checkInButton);
		
	}
	
}