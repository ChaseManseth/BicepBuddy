package BicepTests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import Messaging.DM;
import Messaging.Message;

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

}
