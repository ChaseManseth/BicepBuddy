package Messaging;

import java.util.Date;

import User.User;

public class Message {
	private User sender;
	private User receiver;
	private Date sendDate;
	private String text;
	
	public Message() {
		
	}
	
	public Message(User from, User to, Date sendDate, String text) {
		this.sender = from;
		this.receiver = to;
		this.text = text;
		this.sendDate = sendDate;
	}
	
	public Date getSendDate() {
		return sendDate;
	}
	
	public User getSender() {
		return sender;
	}
	
	public User getReceiver() {
		return receiver;
	}
	
	public String getText() {
		return text;
	}
	
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	public void setSender(User sender) {
		this.sender = sender;
	}
	
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	
	public int compareTo(Message other) {
		if(other == null || other.getSendDate() == null) {
			return 0;
		} else {
			return getSendDate().compareTo(other.getSendDate());
		}
	}
}
