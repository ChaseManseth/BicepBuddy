package bicepBuddyPackage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class DM {
	private List<Message> messages;
	
	public DM() {
		messages = new ArrayList<>();
		for(int i = 0; i < 20; i++) {
			messages.add(new Message(null, null, "test" + i, Calendar.getInstance().getTime()));
		}
	}
	
	public List<Message> getSorted() {
		List<Message> sorted = new ArrayList<>(messages);
		sorted.sort((m1, m2) -> m1.compareTo(m2));
		return sorted;
	}
}
