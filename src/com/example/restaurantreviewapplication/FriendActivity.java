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
import android.widget.TextView;

public class FriendActivity extends Activity {

	private Button deleteFriend;
	private Button messageFriendButton;
	private TextView friendTitle;
	UserApplication app;
	Friend currentFriend;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend);
		//setup buttons and text views
		setupViews();
		//connect to global data
		app = (UserApplication)getApplication();
		
		currentFriend = app.getSelectedFriend();
		friendTitle.setText(currentFriend.getUserId());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_friend, menu);
		return true;
	}
	
	//needs to be implemented
	public void deleteFriendButtonHandler(View v){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Delete Friend")
		.setMessage("Would you like to delete: " + currentFriend.getUserId())
		.setCancelable(false)
		.setPositiveButton("Yes", 
				new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						deleteFriend();
					}
				});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	private void deleteFriend(){
		int response = Server.deleteFriend(app.getCurrentUser(), currentFriend);

		if (response == 0) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Friend Deleted")
					.setMessage("You are no longer friends with: " + currentFriend.getUserId())
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
									returnToMenu();

								}
							});
			AlertDialog alert = builder.create();
			alert.show();
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Problem deleting friend")
					.setMessage("There was a problem deleting this friend")
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
									returnToMenu();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();
		}
	}
	
	//needs to be implemented
	public void messageFriendButtonHandler(View v){
		Intent intent = new Intent(this, CreateMessageActivity.class);
		startActivity(intent);
	}
	
	private void returnToMenu(){
		Intent intent2 = new Intent(this, ManageFriendsActivity.class);
		startActivity(intent2);
	}
	
	private void setupViews(){
		friendTitle = (TextView) findViewById(R.id.friendTitle);
		deleteFriend = (Button) findViewById(R.id.deleteFriend);
		messageFriendButton = (Button) findViewById(R.id.messageFriend);
		
	}

}
