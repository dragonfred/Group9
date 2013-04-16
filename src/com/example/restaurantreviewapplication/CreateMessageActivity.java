package com.example.restaurantreviewapplication;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.restaurantreviewapplication.Message;

public class CreateMessageActivity extends Activity {

	Message newMessage = new Message();
	private ImageButton cancelMessageButton;
	private ImageButton sendMessageAccount;
	private EditText messageText;
	private UserApplication app;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_message);
		app = (UserApplication) getApplication();
		setupViews();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_message, menu);
		return true;
	}
	
	public void cancelMessageButtonHandler(View v){
		Intent intent = new Intent(this, FriendActivity.class);
		startActivity(intent);
		
	}
	
	public void sendMessageAccountHandler(View v){
		
//		public static int messageFriend(User currentUser, Friend friend,
//				String message) {
//			// 0 for good, 1 for bad
		String messageSent;
		String senderUserId;
		String receiverUserId;
		String textField;
		
		senderUserId = app.getUserId();
		receiverUserId = app.getSelectedFriend().getUserId();
		textField = messageText.getText().toString();
		newMessage.setReceiverUserId(receiverUserId);
		newMessage.setSenderUserId(senderUserId);
		newMessage.setTextField(textField);
		messageSent = Server.messageFriend(newMessage);
		
		if (messageSent.equals("MSG: Sent")){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Message Sent")
					.setMessage("Message Sent")
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
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Error Sending Message")
					.setMessage(messageSent)
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
		}
		

	}
	
	private void goBack(){
		//close current activity and go back.
		finish();
	}
	
	private void setupViews(){
		cancelMessageButton = (ImageButton) findViewById(R.id.cancelMessageButton);
		sendMessageAccount = (ImageButton) findViewById(R.id.sendMessageButton);
		messageText = (EditText) findViewById(R.id.messageText);
	}
	

}
