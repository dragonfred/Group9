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

public class NotificationsActivity extends Activity {

	private UserApplication app;
	private ArrayList<Message> messages;
	int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notifications);
		app = (UserApplication) getApplication();
		//seedNotification();
		ListView reviewList = (ListView) findViewById(R.id.notificationsList);
		app.setMessages(Server.getMessages());
		messages = app.getMessages();
		
		if (messages.size() == 0)
			reviewList.setBackgroundColor(Color.parseColor("#FFFFFF"));
		else
			reviewList.setBackgroundColor(Color.parseColor("#98ba40"));
		
		ArrayAdapter<Message> adapter = new ArrayAdapter<Message>(this,
				android.R.layout.simple_list_item_1, messages);
		
		if(messages == null){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("No Messages")
					.setMessage("No messages found.")
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
									returnBackToMenu();
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
				nextScreen(position);

			}
		});

	}

	private void returnBackToMenu(){
		Intent intent = new Intent(this, ManageFriendsActivity.class);
		startActivity(intent);
	}
	
	private void nextScreen(int position) {
		this.position = position;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Message")
				//.setMessage("Would you like to reply?")
				.setCancelable(false)
				.setPositiveButton("Reply",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
								replyMessage();
							}
						});
		builder.setNeutralButton("Delete",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						// TODO Auto-generated method stub
						dialog.cancel();
						deleteMessage();
					}
				});
		builder.setNegativeButton("Cancel",
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
		//app.setSelectedRestaurant(restaurants.get(position));
		//Intent intent = new Intent(this, RestaurantActivity.class);
		//startActivity(intent);
	}

	private void replyMessage(){
		Friend sender = new Friend();
		sender.setUserId(messages.get(position).getSenderUserId());
		app.setSelectedFriend(sender);
		Intent intent = new Intent(this, CreateMessageActivity.class);
		startActivity(intent);
	}
	
	private void deleteMessage(){
		Server.deleteMessage(messages.get(position));
		finish();
		Intent intent = new Intent(this, NotificationsActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_notifications, menu);
		return true;
	}
	
	
	//Junk method to add notifications.  Delete once server is established.
	private void seedNotification(){
		ArrayList<Message> l = new ArrayList<Message>();
		Message m;
		String s1, s2;
		String s3 = "";
		
		for(int i = 0 ; i < 3 ; i++){
			s1 = "" + i*2;
			s2 = "" + i*3;
			s3 = s3 + "yo ";
			m = new Message(s1, s2, s3);
			l.add(m);
		}
		app.setMessages(l);
		
	}

}
