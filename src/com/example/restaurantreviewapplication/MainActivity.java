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
		app = (UserApplication) getApplication();


		usernameText = (EditText) findViewById(R.id.Username);
		passwordText = (EditText) findViewById(R.id.Password);
		loginButton = (Button) findViewById(R.id.LogInButton);
		skipLoginButton = (Button) findViewById(R.id.SkipLoginButton);
		forgotPassword = (Button) findViewById(R.id.ForgotPasswordButton);
		exitButton = (Button) findViewById(R.id.ExitApplicationButton);
		createButton = (Button) findViewById(R.id.CreateAccountButton);


		builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you would like to exit the application?");
		builder.setCancelable(false);
		builder.setTitle("Exit Application?");
		
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
		Intent intent = new Intent(this, FindRestaurantsActivity.class);
		startActivity(intent);
	}

	// OnClickListener for "Create Account" Button
	public void createAccountButtonHandler(View v) {
		Intent intent = new Intent(this, CreateActivity.class);
		startActivity(intent);
	}

	// OnClickListener for "Exit" Button
	public void exitButtonHandler(View V) {
		alert.show();
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

			// this is in place of code below
			Intent intent = new Intent(this, MainMenuActivity.class);
			startActivity(intent);
			User fake = new User();
			fake.setUserId("john");
			app.setCurrentUser(fake);
			/*
			 * Having trouble getting below code to work.  Doesn't find
			 * a user.  Not sure if bad username/password or bad
			 * connection.
			 */
			/*
			// if user found continue else pop up dialog saying no user found
			if (currentUser != null) {
				app.setCurrentUser(currentUser);
				Intent intent = new Intent(this, MainMenuActivity.class);
				startActivity(intent);
				finish();
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("No user found")
						.setMessage("Cannot find username/password on server")
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
			*/
		}
	}
}
