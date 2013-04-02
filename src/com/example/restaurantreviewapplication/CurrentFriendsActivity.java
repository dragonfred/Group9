package com.example.restaurantreviewapplication;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class CurrentFriendsActivity extends Activity {

	
	private UserApplication app;
	private ArrayList<Friend> friends;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_current_friends);
	//	ListView reviewList = (ListView)findViewById(R.id.restaurantsList);
		
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//				this, android.R.layout.simple_list_item_1, friends);
//		
////		reviewList.setAdapter(adapter);
//		
//		setContentView(R.layout.activity_list_restaurants);
				
		app = (UserApplication) getApplication();
		seedFriends();
		
		ListView reviewList = (ListView) findViewById(R.id.friendsList);
		friends = app.getFriendList();
		ArrayAdapter<Friend> adapter = new ArrayAdapter<Friend>(this,
				android.R.layout.simple_list_item_1, friends);
		
		if(friends == null){
			// show message "no new messages"
			Intent intent = new Intent(this, ManageFriendsActivity.class);
			startActivity(intent);
		}else{
			reviewList.setAdapter(adapter);
		}
		reviewList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				nextScreen(position);

			}
		});

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
	
	private void seedFriends(){
		ArrayList<Friend> l = new ArrayList<Friend>();
		Message m;
		Friend a = new Friend();
		String s1 = "bob";
		a.setUserId(s1);
		l.add(a);
		
		a = new Friend();
		s1 = "elmo";
		a.setUserId(s1);
		l.add(a);
		
		a = new Friend();
		s1 = "Yo Mamma";
		a.setUserId(s1);
		l.add(a);
		
		app.setFriendList(l);
		
	
	}

}