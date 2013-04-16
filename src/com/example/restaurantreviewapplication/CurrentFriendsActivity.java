package com.example.restaurantreviewapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CurrentFriendsActivity extends Activity {

	private UserApplication app;
	private ArrayList<Friend> friends;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_current_friends);
				
		app = (UserApplication) getApplication();

		friends = Server.getFriends();
		app.setFriendList(friends);
		ListView friendList = (ListView) findViewById(R.id.friendsList);
				
		if (friends.size() == 0)
			friendList.setBackgroundColor(Color.parseColor("#FFFFFF"));
		else
			friendList.setBackgroundColor(Color.parseColor("#98ba40"));
		
		ArrayAdapter<Friend> adapter = new ArrayAdapter<Friend>(this,
				android.R.layout.simple_list_item_1, friends);
		
		if(friends == null){
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("No Friends Found")
					.setMessage("You have no friends")
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
			friendList.setAdapter(adapter);
		}
		friendList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				nextScreen(position);

			}
		});

	}

	private void goBack(){
		finish();
	}
	
	private void nextScreen(int position) {
		app.setSelectedFriend(friends.get(position));
		Intent intent = new Intent(this, FriendActivity.class);
		startActivity(intent);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_current_friends, menu);
		return true;
	}
	
//	private void seedFriends(){
//		ArrayList<Friend> l = new ArrayList<Friend>();
//		Message m;
//		Friend a = new Friend();
//		String s1 = "bob";
//		a.setUserId(s1);
//		l.add(a);
//		
//		a = new Friend();
//		s1 = "elmo";
//		a.setUserId(s1);
//		l.add(a);
//		
//		a = new Friend();
//		s1 = "Yo Mamma";
//		a.setUserId(s1);
//		l.add(a);
//		
//		app.setFriendList(l);
//		
//	
//	}
}