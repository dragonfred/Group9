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
import android.widget.ImageButton;
import android.widget.TextView;

public class FriendActivity extends Activity {

	private ImageButton deleteFriend;
	private ImageButton messageFriendButton;
	private TextView friendTitle;
	UserApplication app;
	Friend currentFriend;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend);
		FriendBackController.getInstance().setActivity2(this);
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
	
	public void onBackPressed() {
    	FriendBackController.getInstance().closeAllActivities();
        super.onBackPressed();
	}
	
	private void deleteFriend(){
		String response = Server.deleteFriend(app.getCurrentUser(), currentFriend);

		if (response.equals("MSG: Friend deleted")) {
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
					.setMessage(response)
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
		FriendBackController.getInstance().closeAllActivities();
	}
	
	private void setupViews(){
		friendTitle = (TextView) findViewById(R.id.friendTitle);
		deleteFriend = (ImageButton) findViewById(R.id.deleteFriend);
		messageFriendButton = (ImageButton) findViewById(R.id.messageFriend);
		
	}

}
