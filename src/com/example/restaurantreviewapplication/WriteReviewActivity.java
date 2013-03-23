package com.example.restaurantreviewapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WriteReviewActivity extends Activity {

	private TextView restaurantNameText;
	private TextView restaurantAddressText;
	private TextView restaurantPhoneNumberText;
	private EditText reviewText;
	private Button AddPictureButton;
	private Button submitReviewButton;
	private Button postToFacebook;
	UserApplication app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write_review);
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
		getMenuInflater().inflate(R.menu.activity_write_review, menu);
		return true;
	}
	
	public void addPictureButtonHandler(View v){
		
		Intent intent = new Intent(this, AddPictureActivity.class);
		startActivity(intent);
		
	}
	
	public void submitReviewButtonHandler(View v){
		
		String review = reviewText.getText().toString();
		Server.addReview(app.getSelectedRestaurant(), review);
		
//		Intent intent = new Intent(this, ManageFriendsActivity.class);
//		startActivity(intent);
		
	}
	
	public void postToFacebookHandler(View v){
		
//		Intent intent = new Intent(this, ManageFriendsActivity.class);
//		startActivity(intent);
		
	}
	
	private void setupViews(){
		restaurantNameText = (TextView) findViewById(R.id.reviewRestaurantName);
		restaurantAddressText = (TextView) findViewById(R.id.reviewRestaurantAddressText);
		restaurantPhoneNumberText = (TextView) findViewById(R.id.reviewPhoneNumber);
		reviewText = (EditText)findViewById(R.id.reviewText);
		AddPictureButton = (Button)findViewById(R.id.AddPictureButton);
		submitReviewButton = (Button)findViewById(R.id.submitReviewButton);
		postToFacebook = (Button)findViewById(R.id.postToFacebook);
	}
	
}
