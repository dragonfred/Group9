package com.example.restaurantreviewapplication;

import java.util.List;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddPictureActivity extends Activity {

	private Button takePictureButton;
	private Button browseImagesButton;
	private Button attachImageButton;
	private ImageView displayImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_picture);
		setupViews();
	
		//dispatchTakePictureIntent(1);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_picture, menu);
		return true;
	}
	public void takePictureButtonHandler(View v) {
		
	}
	
	public void browseImagesButtonHandler(View v) {
		
	}
	
	public void attachImageButtonHandler(View v) {
		
	}
	
	private void setupViews(){
		takePictureButton = (Button) findViewById(R.id.takePictureButton);
		browseImagesButton = (Button) findViewById(R.id.browseImagesButton);
		attachImageButton = (Button) findViewById(R.id.attachImageButton);
		displayImageView = (ImageView) findViewById(R.id.displayImageView);
					
	}

}
