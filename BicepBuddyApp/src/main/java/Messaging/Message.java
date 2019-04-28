package Messaging;

import java.util.Date;

import User.User;

public class Message {
	private String sender;
	private String receiver;
	private Date sendDate;
	private String text;
	private String id;
	
	public Message() {
		
	}
	
	public Message(String from, String to, Date sendDate, String text) {
		this.sender = from;
		this.receiver = to;
		this.text = text;
		this.sendDate = sendDate;
	}
	
	public Date getSendDate() {
		return sendDate;
	}
	
	
	
	public String getSender() {
		return sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public String getText() {
		return text;
	}
	
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	
	public int compareTo(Message other) {
		if(other == null || other.getSendDate() == null) {
			return 0;
		} else {
			return getSendDate().compareTo(other.getSendDate());
		}
	}
}
