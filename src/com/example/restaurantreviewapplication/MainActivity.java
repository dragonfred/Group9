package com.example.restaurantreviewapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String username, password;
	private EditText usernameText;
	private EditText passwordText;
	private CheckBox loginPersist;
	private SharedPreferences loginPreferences;
	private SharedPreferences.Editor loginPrefsEditor;
	private Boolean saveLogin;
	UserApplication app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		app = (UserApplication) getApplication();
		if(app.getCurrentUser() != null){
			Intent intent = new Intent(this, MainMenuActivity.class); // not sure on
			startActivity(intent);
			finish();
		} 
		
		usernameText = (EditText) findViewById(R.id.Username);
		passwordText = (EditText) findViewById(R.id.Password);
		loginPersist = (CheckBox) findViewById(R.id.LoginPersist);
		loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
		loginPrefsEditor = loginPreferences.edit();
		
		saveLogin = loginPreferences.getBoolean("saveLogin", false);
		
		if (saveLogin)
		{
			usernameText.setText(loginPreferences.getString("username", ""));
			passwordText.setText(loginPreferences.getString("password", ""));
			loginPersist.setChecked(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// OnClickListener for "Forgot Password?" Button
	public void forgotPasswordButtonHandler(View v) {
		String username;
		int result;
		username = usernameText.getText().toString();
		
		if (username.trim().length() == 0) {
			Toast.makeText(getApplicationContext(), "Please enter a username.",
					Toast.LENGTH_SHORT).show();
		} else {
			
			Server.setUsername(usernameText.getText().toString());
			result = Server.resetPassword();
			if (result == 0) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Password Reset")
						.setMessage(
								"Your password has been reset.  Please check your email.")
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Password Reset")
						.setMessage(
								"There was a problem resetting your password")
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
			}
		}
	}

	// OnClickListener for "Skip Login" Button
	public void skipLoginButtonHandler(View V) {
		Server.logOut();
		Intent intent = new Intent(this, FindRestaurantsActivity.class);
		startActivity(intent);
	}

	// OnClickListener for "Create Account" Button
	public void createAccountButtonHandler(View v) {
		Intent intent = new Intent(this, CreateActivity.class);
		startActivity(intent);
	}

	// OnClickListener for "Login" Button
	public void logInButtonHandler(View v) {	
		User currentUser;	

		// check user and password is not length zero
		if ((usernameText.getText().toString().trim().length() == 0)
				|| (passwordText.getText().toString().trim().length() == 0)) {
			Toast.makeText(getApplicationContext(),
					"Please enter a username and password.", Toast.LENGTH_SHORT)
					.show();
		} else {
			
			// set up server and see if the username and password is valid
			Server.setUsername(usernameText.getText().toString());
			Server.setPassword(passwordText.getText().toString());
			currentUser = Server.getUser();
			
			
			// if user found continue else pop up dialog saying no user found
			if (currentUser != null) {
				
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	            imm.hideSoftInputFromWindow(usernameText.getWindowToken(), 0);

				username = Server.getUser().getUsername();
				password = Server.getUser().getPassword();
	            
				if (loginPersist.isChecked())
				{
					loginPrefsEditor.putBoolean("saveLogin", true);
					loginPrefsEditor.putString("username", username);
					loginPrefsEditor.putString("password", password);
				} 
				else
				{
					loginPrefsEditor.clear();
					loginPrefsEditor.commit();
				}
				
//				
//				if (loginPersist.isChecked()) {
//					app.setCurrentUser(Server.getUser());
//				}
//				
				app.setCurrentUser(currentUser);
				Intent intent = new Intent(this, MainMenuActivity.class);
				startActivity(intent);
				finish();
			} else {
				String message = Server.error();
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Invalid information")
						.setMessage(message)
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
			}
			
		}
	}
}
