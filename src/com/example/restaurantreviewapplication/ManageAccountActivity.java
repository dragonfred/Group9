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
import android.widget.EditText;
import android.widget.TextView;

public class ManageAccountActivity extends Activity {

	private EditText usernameText;
	private EditText oldPasswordText;
	private EditText newPasswordText;
	private EditText newPasswordConfirmText;
	private Button updatePasswordButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_account);
		setupViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_manage_account, menu);
		return true;
	}
	
	
	public void updatePasswordButtonHandler(View v) {
		String username = usernameText.getText().toString();
		String oldpassword = oldPasswordText.getText().toString();
		String newPassword = newPasswordText.getText().toString();
		String newPassword2 = newPasswordText.getText().toString();
		int result;
		if(newPassword.compareTo(newPassword2) == 0){
			Server.setPassword(oldpassword);
			Server.setUsername(username);
			result = Server.changePassword(newPassword);
			if(result == 0){
				Intent intent = new Intent(this, MainMenuActivity.class);
				startActivity(intent);
			}else{
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Error changing password")
						.setMessage(
								"Error changing the password.")
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
		}else{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Passwords Mismatch")
					.setMessage(
							"New passwords don't match")
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
	private void setupViews(){
		usernameText = (EditText) findViewById(R.id.usernameText);
		oldPasswordText = (EditText) findViewById(R.id.oldPasswordText);
		newPasswordText = (EditText) findViewById(R.id.newPasswordText);
		newPasswordConfirmText = (EditText) findViewById(R.id.newPasswordConfirmText);
		updatePasswordButton = (Button) findViewById(R.id.updatePasswordButton);
			
	}

}
