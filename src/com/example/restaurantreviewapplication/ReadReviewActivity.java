package com.example.restaurantreviewapplication;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.restaurantreviewapplication.Review;

public class ReadReviewActivity extends Activity{
		
	private ArrayList<Review> reviews;
	ArrayAdapter<Review> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ListView reviewList = (ListView)findViewById(R.id.reviewsList);
		
		adapter = new ArrayAdapter<Review>(this, android.R.layout.simple_list_item_1, reviews);	
		reviewList.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_read_review, menu);
		return true;
	}
	
	// Dummy data for testing.  Replace with call to server
	public void getRestaurants(int zip, String keyword) {		
		int i;
		
		for (i = 0; i < 10; i++) {
			
			//creates a new review using the review class. 
			Review review = new Review("Review text" + i + " here.", 0.0f, 0.0f, 0.0f, 0.0f);
			
			reviews.add(review);
		}
		
//		return reviews;
	}
}