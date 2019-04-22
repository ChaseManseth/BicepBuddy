package Messaging;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import User.User;
import User.UserController;

public class DM {
	private Set<Message> messages;
	private User partner;
	
	public DM() {
		messages = new HashSet<>();
		for(int i = 0; i < 40; i++) {
			Message message;
			if((i % 2) == 0) {
				message = new Message(UserController.getUser(), null, "There's a lady who's sure; All that glitters is gold; And she's buying a stairway to heaven;" + i, Calendar.getInstance().getTime());
			} else {
				message = new Message(null, null, "test " + i, Calendar.getInstance().getTime());
			}
			messages.add(message);
		}
	}
	
	public List<Message> getSorted() {
		List<Message> sorted = messages.stream().collect(Collectors.toList());
		sorted.sort((m1, m2) -> m1.compareTo(m2));
		return sorted;
	}
	
	public User getPartner() {
		return partner;
	}
	
	public void add(Message message) {
		messages.add(message);
	}
}
