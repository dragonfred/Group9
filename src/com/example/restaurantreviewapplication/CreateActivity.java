package com.example.restaurantreviewapplication;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CreateActivity extends Activity {

	private EditText username_text;
	private EditText passwordText;
	private EditText confirmPasswordText;
	private EditText emailText;
	private CheckBox loginPersist;
	private ImageButton cancelCreateAccount;
	private ImageButton submitCreateAccount;
	UserApplication app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		setupViews();
		app = (UserApplication) getApplication();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_create, menu);
		return true;
	}

	private void setupViews() {
		username_text = (EditText) findViewById(R.id.usernameText);
		passwordText = (EditText) findViewById(R.id.passwordText);
		confirmPasswordText = (EditText) findViewById(R.id.confirmPasswordText);
		emailText = (EditText) findViewById(R.id.emailText);
		loginPersist = (CheckBox) findViewById(R.id.loginPersist);
		cancelCreateAccount = (ImageButton)findViewById(R.id.CancelCreateAccountButton);
		submitCreateAccount = (ImageButton)findViewById(R.id.SubmitCreateAccountButton);
	}

	public void cancelAccountButtonHandler(View v) {
		//close the current activity and go back to calling activity
		finish();
	}

	public void submitCreateAccountHandler(View v) {
		String username, password, password2, email, response;
		username = (username_text.getText()).toString();
		password = passwordText.getText().toString();
		password2 = confirmPasswordText.getText().toString();
		email = emailText.getText().toString();

		if (password.compareTo(password2) == 0) {
			Server.setUsername(username);
			Server.setPassword(password);
			response = Server.createAccount(email);

			//commented out error handling until find out how to get server message
			if (response.compareToIgnoreCase("MSG: User created.") == 0) {

//				Toast.makeText(getApplicationContext(),
//						response, Toast.LENGTH_SHORT)
//						.show();

				if (loginPersist.isChecked()) {
					app.setCurrentUser(Server.getUser());
				}
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Account Created")
				.setMessage("Account Created")
				.setCancelable(false)
				.setPositiveButton("OK", 
						new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.cancel();								
								returnHome();
							}
						});
				AlertDialog alert = builder.create();
				alert.show();				
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle(response)
						.setMessage(response)
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,	int which) {
										dialog.cancel();
									}
								});
				AlertDialog alert = builder.create();
				alert.show();
			}
		} else {

			// pop up "passwords don't match"
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Passwords do not match")
					.setMessage("Passwords do not match")
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.cancel();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();
		}
	}
	public void returnHome(){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

}
