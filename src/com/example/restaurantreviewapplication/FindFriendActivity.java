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
import android.widget.Toast;

public class FindFriendActivity extends Activity {
	private EditText usernameSearch;
	private Button friendSearchButton;
	private Friend foundFriend;
	private UserApplication app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_friend);
		setupViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_find_friend, menu);
		app = (UserApplication) getApplication();
		return true;
	}

	public void friendSearchButtonHandler(View v) {
		String possibleFriend = usernameSearch.getText().toString();
		if (possibleFriend.trim().length() == 0) {
			Toast.makeText(getApplicationContext(), "Please enter a username.",
					Toast.LENGTH_SHORT).show();
		} else {
			foundFriend = Server.findFriend(possibleFriend);

			if (foundFriend != null) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Friend Found")
						.setMessage(
								"User " + foundFriend.getUserId()
										+ " found. Would you like to add them?")
						.setCancelable(false)
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										addFriend();
									}
								});
				builder.setNegativeButton("No",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								dialog.cancel();
							}
						});

				AlertDialog alert = builder.create();
				alert.show();
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Friend not found")
						.setMessage("No user by that name found.")
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

	private void addFriend() {
		Server.addFriend(app.getCurrentUser(), foundFriend);
	}

	private void setupViews() {
		usernameSearch = (EditText) findViewById(R.id.UsernameSearch);
		friendSearchButton = (Button) findViewById(R.id.FriendSearchButton);

	}
}
