package com.example.restaurantreviewapplication;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText usernameText;
	private EditText passwordText;
	private Button loginButton;
	private Button skipLoginButton;
	private Button forgotPassword;
	private Button exitButton;
	private Button createButton;
	private AlertDialog.Builder builder;
	private AlertDialog alert;
	UserApplication app;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		app = (UserApplication)getApplication();
		
		
		usernameText = (EditText)findViewById(R.id.Username);
		passwordText = (EditText)findViewById(R.id.Password);
		loginButton = (Button)findViewById(R.id.LogInButton);
		skipLoginButton = (Button)findViewById(R.id.SkipLoginButton);
		forgotPassword = (Button)findViewById(R.id.ForgotPasswordButton);
		exitButton = (Button)findViewById(R.id.ExitApplicationButton);
		createButton = (Button)findViewById(R.id.CreateAccountButton);
		
		
		
		builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you would like to exit the application?");
		builder.setCancelable(false);
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {		
				finish();				
				System.exit(0);
			}
		});
		
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		
		alert = builder.create();
		
		usernameText.setSingleLine();
		passwordText.setSingleLine();
		
		//OnClickListener for "Login" Button
//		loginButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				
//				if ((usernameText.getText().toString() == "") && (passwordText.getText().toString() == ""))
//				{
//					Toast.makeText(getApplicationContext(), 
//									"Please enter a username and password.", 
//									Toast.LENGTH_SHORT).show();	
//				}
//			}
//			
//		});

//		//OnClickListener for "Skip Login" Button
//		skipLoginButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				
//			}
//			
//		});
		
//		//OnClickListener for "Forgot Password?" Button
//		forgotPassword.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				
//			}
//			
//		});
		
//		//OnClickListener for "Exit" Button
//		exitButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				alert.show();
//			}
//			
//		});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	//OnClickListener for "Forgot Password?" Button
	public void forgotPasswordButton(View v){
		
	}
	
	//OnClickListener for "Skip Login" Button
	public void skipLoginButton(View V){
		Intent intent = new Intent(this, FindRestaurantsActivity.class);
		startActivity(intent);
	}

	//OnClickListener for "Create Account" Button
	public void createAccountButtonHandler(View v){
		
		Intent intent = new Intent(this, CreateActivity.class);
		startActivity(intent);
		
	}
	
	//OnClickListener for "Exit" Button
	public void exitButtonHandler(View V){
		alert.show();
	}
	
	//OnClickListener for "Login" Button
	public void logInButtonHandler(View v){
		
		boolean result;
		
		// check user and password is not length zero
		if ((usernameText.getText().toString().trim().length() == 0)|| (passwordText.getText().toString().trim().length() == 0))
		{
			Toast.makeText(getApplicationContext(), 
							"Please enter a username and password.", 
							Toast.LENGTH_SHORT).show();	
		}else{
			
			// this needs to be changed to a server call:
//			app.setUsername("" + usernameText.getText());
//			app.setPassword("" + passwordText.getText());
//			result = serverConnection.checkUserPassword();
			
			//remove once server call is in
			result = true;

			if (result) {
				Intent intent = new Intent(this, MainMenuActivity.class);
				startActivity(intent);
				finish();
			} else {
				// pop up notice that user/password bad
			}
			
		}
		
	}
	
	

}
