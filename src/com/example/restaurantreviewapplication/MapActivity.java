package com.example.restaurantreviewapplication;


import java.util.Locale;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity {

	private ImageButton mapButton;
	/**
     * Note that this may be null if the Google Play services APK is not available.
     */
    private GoogleMap mMap;
    private UserApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        app = (UserApplication)getApplication();
        setupViews();
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    public void onBackPressed() {
    	finish();
    }
    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not have been
     * completely destroyed during this process (it is likely that it would only be stopped or
     * paused), {@link #onCreate(Bundle)} may not be called again so we should call this method in
     * {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
    	double lat, lon;
    	String latLon = app.getSelectedRestaurant().getLocation();
    	String[] latLonArray = latLon.split(",");
    	lat = Double.parseDouble(latLonArray[0]);
    	lon = Double.parseDouble(latLonArray[1]);
    	    	
    	final LatLng location = new LatLng(lat, lon);
    	
    	System.out.println(app.getSelectedRestaurant().getLocation());
 
        mMap.addMarker(new MarkerOptions()
        .position(location)
        .title(app.getSelectedRestaurant().getName())
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));        
    }
    

	
	public void mapButtonHandler(View v){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Open Google Maps")
		.setMessage("This will leave this app and open in Google Maps")
		.setCancelable(false)
		.setPositiveButton("OK", 
				new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						openGoogleMaps();
					}
				});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	private void openGoogleMaps(){
		double lat, lon;
    	String latLon = app.getSelectedRestaurant().getLocation();
    	String[] latLonArray = latLon.split(",");
    	lat = Double.parseDouble(latLonArray[0]);
    	lon = Double.parseDouble(latLonArray[1]);
    	
		String uri = String.format(Locale.ENGLISH, "geo:%f,%f", lat, lon);
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
		startActivity(intent);
	}
	
	private void setupViews(){
		mapButton = (ImageButton) findViewById(R.id.mapExternalButton);
	}


	
}
