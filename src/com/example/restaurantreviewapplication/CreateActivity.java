package com.example.restaurantreviewapplication;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class CreateActivity extends Activity {


	private EditText  username_text;
	private EditText passwordText;
	private EditText confirmPasswordText;
	private EditText emailText;
	private CheckBox  loginPersist;
	private Button cancelCreateAccount;
	private Button submitCreateAccount;
	UserApplication app;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		setupViews();
		app = (UserApplication)getApplication();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_create, menu);
		return true;
	}
	
	private void setupViews(){
		username_text = (EditText)findViewById(R.id.usernameText);
		passwordText = (EditText)findViewById(R.id.passwordText);
		confirmPasswordText = (EditText)findViewById(R.id.confirmPasswordText);
		emailText = (EditText)findViewById(R.id.emailText);
		loginPersist = (CheckBox)findViewById(R.id.loginPersist);
		cancelCreateAccount = (Button) findViewById(R.id.cancelCreateAccount);
		submitCreateAccount = (Button) findViewById(R.id.submitCreateAccount);
	}
	public void cancelAccountButtonHandler(View v)
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	public void submitCreateAccountHandler(View v)
	{
		String username, password, password2, email, response;
		username = (username_text.getText()).toString();
		password = passwordText.getText().toString();
		password2 = confirmPasswordText.getText().toString();
		email = emailText.getText().toString();
//	//	username = "a";
//		password = "b";
//		password2 = "c";
//		email = "a@b.com";
		
		if(password.compareTo(password2) == 0){
			Server.setUsername(username);
			Server.setPassword(password);
			response = Server.createAccount(email);
			
			System.out.println(response);
			//handle response from server
			
			if(loginPersist.isChecked()){
				app.setCurrentUser(Server.getUser());
			}	
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}else{
			
			//pop up "passwords don't match"
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Passwords do not match")
			.setMessage("Passwords do not match")
			.setCancelable(false)
			.setPositiveButton("OK", 
					new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
			AlertDialog alert = builder.create();
			alert.show();
		}
	}
}
