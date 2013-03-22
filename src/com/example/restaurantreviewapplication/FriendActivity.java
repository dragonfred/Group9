package com.example.restaurantreviewapplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FriendActivity extends Activity {

	private Button deleteFriend;
	private Button messageFriendButton;
	private TextView friendTitle;
	UserApplication app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend);
		//setup buttons and text views
		setupViews();
		//connect to global data
		app = (UserApplication)getApplication();
		
		Friend current = app.getSelectedFriend();
		friendTitle.setText(current.getUserId());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_friend, menu);
		return true;
	}
	
	//needs to be implemented
	public void deleteFriendHandler(View v){
		
		Intent intent = new Intent(this, ManageFriendsActivity.class);
		startActivity(intent);
		
	}
	
	//needs to be implemented
	public void messageFriendButtonHandler(View v){
		
		Intent intent = new Intent(this, ManageFriendsActivity.class);
		startActivity(intent);
		
	}
	
	private void setupViews(){
		friendTitle = (TextView) findViewById(R.id.friendTitle);
		deleteFriend = (Button) findViewById(R.id.deleteFriendButton);
		messageFriendButton = (Button) findViewById(R.id.messageFriendButton);
		
	}

}
