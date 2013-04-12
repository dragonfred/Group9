package com.example.restaurantreviewapplication;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ReviewDetailsActivity extends Activity {

	private TextView restaurantNameText;
	private TextView restaurantAddressText;
	private TextView restaurantPhoneNumberText;
	private TextView reviewText;
	private TextView reviewByText;

	private RatingBar tasteRatingBar;
	private RatingBar serviceRatingBar;
	private RatingBar cleanlinessRatingBar;
	private RatingBar overallRatingBar;

	private ImageView displayImageView;

	private TextView ratingText;
	
	private UserApplication app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review_details);
		
		setupViews();
	
		app = (UserApplication)getApplication();
		Restaurant currentRestaurant = app.getSelectedRestaurant();
		
		restaurantNameText.setText(currentRestaurant.getName());
		restaurantAddressText.setText(currentRestaurant.getAddress());
		restaurantPhoneNumberText.setText(currentRestaurant.getPhone());
		
		Review currentReview = app.getSelectedReview();
		
		reviewText.setText(currentReview.getReview());
		reviewByText.setText("Reviewed by: " + currentReview.getReviewer());
		tasteRatingBar.setRating(currentReview.getTasteRating());
		serviceRatingBar.setRating(currentReview.getServiceRating());
		cleanlinessRatingBar.setRating(currentReview.getCleanlinessRating());
		overallRatingBar.setRating(currentReview.getOverallRating());
		
		displayImageView.setImageBitmap(currentReview.getImage());
		
		DecimalFormat df = new DecimalFormat("#.#");
		ratingText.setText(df.format(currentReview.getOverallRating()) + " out of 4 stars");
		
	}
	
	public void nextScreen(int position)
	{
		app.setSelectedRestaurant(app.getRestaurants().get(position));
		Intent intent = new Intent(this, RestaurantActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.review_details, menu);
		return true;
	}
	
	private void setupViews(){
		restaurantNameText = (TextView)findViewById(R.id.RestaurantNameText);
		restaurantAddressText = (TextView)findViewById(R.id.RestaurantAddressText);
		restaurantPhoneNumberText = (TextView)findViewById(R.id.RestaurantPhoneNumberText);
		reviewByText = (TextView)findViewById(R.id.ReviewByText);

		reviewText = (TextView)findViewById(R.id.ReviewText);
		
		tasteRatingBar = (RatingBar) findViewById(R.id.tasteRating);
		serviceRatingBar = (RatingBar) findViewById(R.id.serviceRating);
		cleanlinessRatingBar = (RatingBar) findViewById(R.id.cleanlinessRating);
		overallRatingBar = (RatingBar) findViewById(R.id.overallRating);

		displayImageView = (ImageView)findViewById(R.id.DisplayImageView);
		
		ratingText = (TextView)findViewById(R.id.RatingText);
		
	}
	

}
