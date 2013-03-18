package com.example.restaurantreviewapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.EditText;

public class CreateActivity extends Activity {

	private EditText _usernameText;
	private EditText _passwordText;
	private EditText _confirmPasswordText;
	private EditText _emailText;
	private CheckBox _loginPersist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		
		_usernameText = (EditText)findViewById(R.id.Username);
		_passwordText = (EditText)findViewById(R.id.Password);
		_confirmPasswordText = (EditText)findViewById(R.id.confirmPasswordText);
		_emailText = (EditText)findViewById(R.id.emailText);
		_loginPersist = (CheckBox)findViewById(R.id.loginPersist);
		
		
		// THERE IS AN ERROR IN THESE!  Not sure what -Jason
//		_usernameText.setText("Username");
//		_passwordText.setText("Password");
//		_confirmPasswordText.setText("Confirm Password");
//		_emailText.setText("Email");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_create, menu);
		return true;
	}

}
