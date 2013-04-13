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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;


public class ReadReviewActivity extends Activity{

	private TextView restaurantNameText;
	private TextView restaurantAddressText;
	private TextView restaurantPhoneNumberText;
	//private RatingBar overallRatingBar;
	
	private UserApplication app;
	private ArrayList<Review> reviews; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_review);

		setupViews();
		
		reviews = new ArrayList<Review>();
		app = (UserApplication)getApplication();
		Restaurant restaurant = app.getSelectedRestaurant();
		reviews = Server.getReviews(restaurant);
		
		restaurantNameText.setText(restaurant.getName());
		restaurantAddressText.setText(restaurant.getAddress());
		restaurantPhoneNumberText.setText(restaurant.getPhone());
		
		final ListView list = (ListView) findViewById(R.id.reviews_list);
        list.setAdapter(new ReviewListAdapter(this, reviews));
        
        list.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
        		nextScreen(position);
        	}  
        });
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
		restaurantNameText = (TextView)findViewById(R.id.reviewRestaurantName);
		restaurantAddressText = (TextView)findViewById(R.id.reviewRestaurantAddressText);
		restaurantPhoneNumberText = (TextView)findViewById(R.id.reviewPhoneNumber);
		//overallRatingBar = (RatingBar) findViewById(R.id.overallRating);
		
	}
}