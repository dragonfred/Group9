package com.example.restaurantreviewapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFriendRequests extends Activity {
	private UserApplication app;
	private ArrayList<Friend> friendRequests;
	private int selectedFriend;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_friend_requests);

		friendRequests = new ArrayList<Friend>();
		app = (UserApplication) getApplication();
		//seedFriends();
		
		ListView reviewList = (ListView) findViewById(R.id.friend_request_list);
		//friends = app.getFriendList();
		friendRequests = Server.getUnconfirmedFriends();
		
		if (friendRequests.size() == 0)
			reviewList.setBackgroundColor(Color.parseColor("#FFFFFF"));
		else
			reviewList.setBackgroundColor(Color.parseColor("#98ba40"));
		
		ArrayAdapter<Friend> adapter = new ArrayAdapter<Friend>(this,
				android.R.layout.simple_list_item_1, friendRequests);
		
		if(friendRequests.size() == 0){
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("No Friends Found")
					.setMessage("No Friend Requests Found")
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
									goBack();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();
			
		}else{
			reviewList.setAdapter(adapter);
		}
		reviewList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				confirmFriend(position);

			}
		});
	}

	public void goBack(){
		Intent intent = new Intent(this, ManageFriendsActivity.class);
		startActivity(intent);
	}
	
	public void confirmFriend(int position) {
		selectedFriend = position;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Confirm Friend")
				.setMessage("Would you like to accept this friend request?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
								confirmAndReload();
								
							}
						});
		builder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
				denyAndReload();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
		
	}
	
	public void confirmAndReload(){
		Friend newFriend = friendRequests.get(selectedFriend);
		Server.confirmFriend(newFriend);
		finish();
		Intent intent = new Intent(this, ListFriendRequests.class);
		startActivity(intent);
	}
	public void denyAndReload(){
		Friend newFriend = friendRequests.get(selectedFriend);
		Server.rejectFriend(newFriend);
		finish();
		Intent intent = new Intent(this, ListFriendRequests.class);
		startActivity(intent);
	}
}
