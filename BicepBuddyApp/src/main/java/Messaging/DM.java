package Messaging;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import User.User;
import User.UserController;
import bicepBuddyPackage.Master;

public class DM {
	private Set<Message> messages; // Conversation with accepted match
	private User partner; // Current accepted match the user is conversing with
	
	public DM() {
		messages = new HashSet<>();
		
	}
	
	public List<Message> getSorted() {
		List<Message> sorted = messages.stream().collect(Collectors.toList());
		sorted.sort((m1, m2) -> m1.compareTo(m2));
		return sorted;
	}
	
	public User getPartner() {
		return partner;
	}
	
	public void setPartner(User partner) {
		this.partner = partner;
	}
	
	public Set<Message> getMessages() {
		return messages;
	}
	
	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	
	public void add(Message message) {
		messages.add(message);
	}
	
	public List<User> getAcceptedMatches() {
		// TODO: Retrieve list of accepted matches
		return new ArrayList<User>();
	}
}
