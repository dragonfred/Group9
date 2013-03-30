package com.example.restaurantreviewapplication;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class WriteReviewActivity extends Activity {

	private TextView restaurantNameText;
	private TextView restaurantAddressText;
	private TextView restaurantPhoneNumberText;
	private EditText reviewText;
	private TextView charactersRemaining;
	
	private Button addPictureButton;
	private Button submitReviewButton;
	private Button postToFacebook;
	
	private RatingBar tasteRatingBar;
	private RatingBar serviceRatingBar;
	private RatingBar cleanlinessRatingBar;
	private RatingBar overallRatingBar;
	private float overallRating = 0.0f;
	private final float maxRating = 12.0f;
	
	UserApplication app;
	
	private int maxChars = 200;
	private int charsLeft= maxChars;
	
	private AlertDialog.Builder builder;
	private AlertDialog alert;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write_review);	
		
		//setup buttons and text views
		setupViews();
		
		builder = new AlertDialog.Builder(this);
		builder.setMessage("Submit Review?");
		builder.setCancelable(false);
		
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {		
				finish();				
				
				//submit review to server
				String review = reviewText.getText().toString();
				Review reviewObj = new Review();
				reviewObj.setReview(review);
				Server.addReview(app.getSelectedRestaurant(), reviewObj);
				
				Toast.makeText(getApplicationContext(), 
						"Thank you for your review!", 
						Toast.LENGTH_SHORT).show();	
			}
		});
		
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		
		addListenerOnRatingBar();
		
		alert = builder.create();
		
		//connect to global data
		app = (UserApplication)getApplication();
		//get the restaurant selected in previous screen
		Restaurant current = app.getSelectedRestaurant();	
		//output restaurant info onto screen
		restaurantNameText.setText(current.getName());
		restaurantAddressText.setText(current.getAddress());
		restaurantPhoneNumberText.setText(current.getPhone());
		
		reviewText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				charsLeft = maxChars - reviewText.length();
				charactersRemaining.setText(charsLeft + " characters remaining");
				
				if (charsLeft <= 25)
				{
					charactersRemaining.setTextColor(Color.RED);
				}
				else
					charactersRemaining.setTextColor(Color.BLACK);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				// NOT IMPLEMENTED
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				// NOT IMPLEMENTED
			}
		});
	}

	public void addListenerOnRatingBar() {
		 
		tasteRatingBar = (RatingBar) findViewById(R.id.tasteRating);
		serviceRatingBar = (RatingBar) findViewById(R.id.serviceRating);
		cleanlinessRatingBar = (RatingBar) findViewById(R.id.cleanlinessRating);
		overallRatingBar = (RatingBar) findViewById(R.id.overallRating);
	 
		//if taste rating value is changed, calculate and update overall rating.
		tasteRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
					
				overallRatingBar.setRating(CalculateRating());
			}
		});
		
		serviceRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
					
				overallRatingBar.setRating(CalculateRating());
			}
		});
		
		cleanlinessRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
					
				overallRatingBar.setRating(CalculateRating());
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_write_review, menu);
		return true;
	}
	
	private float CalculateRating()
	{
		overallRating = ((tasteRatingBar.getRating() + serviceRatingBar.getRating() + cleanlinessRatingBar.getRating()) / maxRating) * 4.0f;
		return overallRating;
	}
	
	public void addPictureButtonHandler(View v){
		
		Intent intent = new Intent(this, AddPictureActivity.class);
		startActivity(intent);
		
	}
	
	public void submitReviewButtonHandler(View v){
		alert.show();
	}
	
	public void postToFacebookHandler(View v){
		
//		Intent intent = new Intent(this, ManageFriendsActivity.class);
//		startActivity(intent);
		
	}
	
	private void setupViews(){
		restaurantNameText = (TextView)findViewById(R.id.RestaurantNameText);
		restaurantAddressText = (TextView)findViewById(R.id.RestaurantAddressText);
		restaurantPhoneNumberText = (TextView)findViewById(R.id.RestaurantPhoneNumberText);
		reviewText = (EditText)findViewById(R.id.ReviewText);
		charactersRemaining = (TextView)findViewById(R.id.CharsLeftText);
		addPictureButton = (Button)findViewById(R.id.AddPictureButton);
		submitReviewButton = (Button)findViewById(R.id.SubmitReviewButton);
		postToFacebook = (Button)findViewById(R.id.PostToFacebook);
	}
	
}
