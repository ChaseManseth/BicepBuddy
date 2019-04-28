package Messaging;

import java.util.Date;

import User.User;


// TODO: Auto-generated Javadoc
/**
 * The Class Message.
 *
 * @authors Zachary Steudel, Chase Manseth, Hunter Long, Bob Rein, Reece Kemball-Cook
 */
public class Message {
	
	/** The sender. */
	private String sender;
	
	/** The receiver. */
	private String receiver;
	
	/** The send date. */
	private Date sendDate;
	
	/** The text. */
	private String text;
	
	/** The id. */
	private String id;
	
	/**
	 * Instantiates a new message.
	 */
	public Message() {
		
	}
	
	/**
	 * Instantiates a new message.
	 *
	 * @param from the from
	 * @param to the to
	 * @param sendDate the send date
	 * @param text the text
	 */
	public Message(String from, String to, Date sendDate, String text) {
		this.sender = from;
		this.receiver = to;
		this.text = text;
		this.sendDate = sendDate;
	}
	
	/**
	 * Gets the send date.
	 *
	 * @return the send date
	 */
	public Date getSendDate() {
		return sendDate;
	}
	
	
	
	/**
	 * Gets the sender.
	 *
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * Gets the receiver.
	 *
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Sets the sender.
	 *
	 * @param sender the new sender
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * Sets the receiver.
	 *
	 * @param receiver the new receiver
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Sets the send date.
	 *
	 * @param sendDate the new send date
	 */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	
	/**
	 * Compare to.
	 *
	 * @param other the other
	 * @return the int
	 */
	public int compareTo(Message other) {
		if(other == null || other.getSendDate() == null) {
			return 0;
		} else {
			return getSendDate().compareTo(other.getSendDate());
		}
	}
}
