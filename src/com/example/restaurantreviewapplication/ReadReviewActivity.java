package com.example.restaurantreviewapplication;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ReadReviewActivity extends Activity{

//	String[] reviews = {"Review 1","Review 2","Review 3","Review 4",
//						"Review 5","Review 6","Review 7","Review 8"};
	private UserApplication app;
	private TextView restaurantNameText;
	private TextView restaurantAddressText;
	private TextView restaurantPhoneNumberText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_review);
		setupViews();
		app = (UserApplication)getApplication();
		Restaurant restaurant = app.getSelectedRestaurant();
		
		restaurantNameText.setText(restaurant.getName());
		restaurantAddressText.setText(restaurant.getAddress());
		restaurantPhoneNumberText.setText(restaurant.getPhone());
		
		ArrayList<Review> reviews = Server.getReviews(restaurant);
		
		ListView reviewList = (ListView) findViewById(R.id.reviewsList);
		ArrayAdapter<Review> adapter = new ArrayAdapter<Review>(this, android.R.layout.simple_list_item_1, reviews);				
		reviewList.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_read_review, menu);
		return true;
	}
	
	private void setupViews(){
		restaurantNameText = (TextView) findViewById(R.id.reviewRestaurantName);
		restaurantAddressText = (TextView) findViewById(R.id.reviewRestaurantAddressText);
		restaurantPhoneNumberText = (TextView) findViewById(R.id.reviewPhoneNumber);
	}

}
