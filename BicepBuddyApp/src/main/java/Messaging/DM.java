/*
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
package Messaging;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import User.User;

/**
 * The Class DM.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class DM {
	
	/** The messages. */
	private Set<Message> messages; // Conversation with accepted match
	
	/** The partner. */
	private User partner; // Current accepted match the user is conversing with
	
	/**
	 * Instantiates a new dm.
	 */
	public DM() {
		messages = new HashSet<>();
		
	}
	
	/**
	 * Gets the sorted array of messages.
	 *
	 * @return the sorted
	 */
	public List<Message> getSorted() {
		List<Message> sorted = messages.stream().collect(Collectors.toList());
		sorted.sort((m1, m2) -> m1.compareTo(m2));
		return sorted;
	}
	
	/**
	 * Gets the partner user object.
	 *
	 * @return the partner
	 */
	public User getPartner() {
		return partner;
	}
	
	/**
	 * Sets the partner user object.
	 *
	 * @param partner the new partner
	 */
	public void setPartner(User partner) {
		this.partner = partner;
	}
	
	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	public Set<Message> getMessages() {
		return messages;
	}
	
	/**
	 * Sets the messages.
	 *
	 * @param messages the new messages
	 */
	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	
	/**
	 * Adds the.
	 *
	 * @param message the message
	 */
	public void add(Message message) {
		messages.add(message);
	}
	
	/**
	 * Gets the accepted matches.
	 *
	 * @return the accepted matches
	 */
	public List<User> getAcceptedMatches() {
		return new ArrayList<User>();
	}
}
