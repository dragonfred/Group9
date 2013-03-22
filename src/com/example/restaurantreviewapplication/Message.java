/**
 * 
 */
package com.example.restaurantreviewapplication;

/**
 * @author Matt
 *
 */
public class Message {

	private String senderUserId;
	private String receiverUserId;
	private String textField;
	//private String addFriendBinary;
	
	/**
	 * 
	 */
	public Message() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Message(String senderUserId, String receiverUserId, String textField) {
		super();
		this.senderUserId = senderUserId;
		this.receiverUserId = receiverUserId;
		this.textField = textField;
	}



	/**
	 * @return the senderUserId
	 */
	public String getSenderUserId() {
		return senderUserId;
	}

	/**
	 * @param senderUserId the senderUserId to set
	 */
	public void setSenderUserId(String senderUserId) {
		this.senderUserId = senderUserId;
	}

	/**
	 * @return the receiverUserId
	 */
	public String getReceiverUserId() {
		return receiverUserId;
	}

	/**
	 * @param receiverUserId the receiverUserId to set
	 */
	public void setReceiverUserId(String receiverUserId) {
		this.receiverUserId = receiverUserId;
	}

	/**
	 * @return the textField
	 */
	public String getTextField() {
		return textField;
	}

	/**
	 * @param textField the textField to set
	 */
	public void setTextField(String textField) {
		this.textField = textField;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return senderUserId + " - " + textField;
	}

	
}
