package com.example.restaurantreviewapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ReadReviewActivity extends Activity{

	private UserApplication app;
	private TextView restaurantNameText;
	private TextView restaurantAddressText;
	private TextView restaurantPhoneNumberText;
	private ArrayList<Review> reviews;
	private ArrayAdapter<Review> adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_review);
		setupViews();
		app = (UserApplication)getApplication();
		Restaurant restaurant = app.getSelectedRestaurant();
		
		restaurantNameText.setText(restaurant.getName());
		restaurantAddressText.setText(restaurant.getAddress());
		restaurantPhoneNumberText.setText(restaurant.getPhone());
		

		reviews = Server.getReviews(restaurant);
		
		ListView reviewList = (ListView) findViewById(R.id.reviewsList);
	
		adapter = new ArrayAdapter<Review>(this, android.R.layout.simple_list_item_1, reviews);	

		reviewList.setAdapter(adapter);
		
		reviewList.setOnItemClickListener(
			new OnItemClickListener(){
				
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					nextScreen(position);
				}
			}
		);
			
	}

	private void nextScreen(int position){
		app.setSelectedReview(reviews.get(position));
		Intent intent = new Intent(this, ReviewDetailsActivity.class);
		startActivity(intent);
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



