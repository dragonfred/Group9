package com.example.restaurantreviewapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ListRestaurantsActivity extends Activity {

	private TextView searchResultsText;
	private UserApplication app;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restaurants);
        
        searchResultsText = (TextView) findViewById(R.id.SearchResultsText);
        
        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		app = (UserApplication)getApplication();
		restaurants = app.getRestaurants();
        
		searchResultsText.setText("Search results for \"" + app.getSearchText() + "\"");
		
        final ListView list = (ListView) findViewById(R.id.restaurants_list);
        list.setAdapter(new RestaurantListAdapter(this, restaurants));
        
        list.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
        		nextScreen(position);
        	}  
        });
    }
    
    public void nextScreen(int position)
    {
    	app.setSelectedRestaurant(app.getRestaurants().get(position));
		Intent intent = new Intent(this, RestaurantActivity.class);
		startActivity(intent);
    }
}