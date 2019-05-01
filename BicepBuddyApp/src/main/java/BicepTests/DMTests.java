package BicepTests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.swing.JTextArea;

import org.junit.jupiter.api.Test;

import Messaging.DM;
import Messaging.DMController;
import Messaging.DMView;
import Messaging.Message;
import User.UserController;

class DMTests {

	@Test
	void testDMSort() {
		DM d = new DM();
		Message m1 = new Message();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			fail();
		}
		
		Message m2 = new Message();
		
		d.add(m2);
		d.add(m1);
		
		List<Message> dms = d.getSorted();
		assertTrue(!dms.isEmpty());
	}

	@Test
	public void newDM() {
		UserController.getInstance().validateLogin("zacharysteudel@gmail.com", "password");
		DMController dc = new DMController(UserController.getUser(), new DMView(UserController.getUser()));
		
		assertTrue(dc.getDm() != null);
		assertTrue(dc.getDm().getPartner().getEmail().contentEquals("zacharysteudel@gmail.com"));
		UserController.setUser(null);
	}
	
	@Test
	public void getMessageByIDWorks() {
		UserController.getInstance().validateLogin("zacharysteudel@gmail.com", "password");
		DMController dc = new DMController(UserController.getUser(), new DMView(UserController.getUser()));
		Message m = dc.getChatById("5cc8e841caacc31bf5117643");
		
		assertTrue(!m.getText().isEmpty());
		UserController.setUser(null);
	}
	
	@Test
	public void getListOfMessages() {
		UserController.getInstance().validateLogin("zacharysteudel@gmail.com", "password");
		DMController dc = new DMController(UserController.getUser(), new DMView(UserController.getUser()));
		Set<Message> ms = dc.getMessageList(UserController.getUser().getId());
		
		assertTrue(ms.isEmpty());
		UserController.setUser(null);
	}
	
	@Test
	public void populateTheMessageField() {
		UserController.getInstance().validateLogin("zacharysteudel@gmail.com", "password");
		DMController dc = new DMController(UserController.getUser(), new DMView(UserController.getUser()));
		
		JTextArea jt = new JTextArea();
		dc.populateMessages(jt);
		
		assertTrue(jt.getText().isEmpty());
		
		UserController.setUser(null);
	}
	
	@Test
	public void addMessageTest() {
		UserController.getInstance().validateLogin("zacharysteudel@gmail.com", "password");
		DMController dc = new DMController(UserController.getUser(), new DMView(UserController.getUser()));
		
		JTextArea jt = new JTextArea();
		dc.populateMessages(jt);
		
		int b4Size = jt.getText().length();
		
		Message m = new Message();
		m.setText("yo");
		m.setSender("yoyo");
		m.setSendDate(Calendar.getInstance().getTime());
		
		dc.addMessage(jt, m);
		
		int afterSize = jt.getText().length();
		
		assertTrue(afterSize > b4Size);
		
		UserController.setUser(null);
	}
}
