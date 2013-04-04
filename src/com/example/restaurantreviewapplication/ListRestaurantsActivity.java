package com.example.restaurantreviewapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListRestaurantsActivity extends Activity {

	private UserApplication app;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restaurants);
        
        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		app = (UserApplication)getApplication();
		restaurants = app.getRestaurants();
        
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