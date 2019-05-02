package Messaging;


import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTextArea;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import User.User;
import User.UserController;
import bicepBuddyPackage.Master;

/**
 * The Class DMController.
 * @authors: Zachary Steudel, Hunter Long, Chase Manseth, Bob Rein, Reece Kemball-Cook
 */
public class DMController{
	
	/** The dm. */
	private DM dm = null;
	
	/** The dm view. */
	private DMView dmView = null;
	
	/** The num messages. */
	private int numMessages = 0;
		
	/** The loading cont. */
	public boolean loadingCont = true;
	
	/**
	 * Gets the num messages.
	 *
	 * @return the num messages
	 */
	public int getNumMessages() {
		return numMessages;
	}

	/**
	 * Sets the num messages.
	 *
	 * @param numMessages the new num messages
	 */
	public void setNumMessages(int numMessages) {
		this.numMessages = numMessages;
	}

	/**
	 * Gets the dm.
	 *
	 * @return the dm
	 */
	public DM getDm() {
		return dm;
	}

	/**
	 * Sets the dm.
	 *
	 * @param dm the new dm
	 */
	public void setDm(DM dm) {
		this.dm = dm;
	}

	/**
	 * Gets the dm view.
	 *
	 * @return the dm view
	 */
	public DMView getDmView() {
		return dmView;
	}

	/**
	 * Sets the dm view.
	 *
	 * @param dmView the new dm view
	 */
	public void setDmView(DMView dmView) {
		this.dmView = dmView;
	}



	/** The controller. */
	// DMController singleton
	private static DMController controller = null;
	
	/** The Constant DATE_FORMAT. */
	// Various constants
	private final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy :: HH:mm ---");
	
	/** The Constant MESSAGE_FILE. */
	private static final String MESSAGE_FILE = "messageDB.csv";
	
	/** The http client. */
	private HttpClient httpClient = HttpClientBuilder.create().build();
	
	// Testing
//		private String baseUrl = "http://localhost:3000/chat/";
		/** The base url. */
	// Production
		private String baseUrl = "http://bb.manseth.com/chat/";
	
	/**
	 * Instantiates a new DM controller.
	 *
	 * @param partner the partner
	 * @param dmView the dm view
	 */
	public DMController(User partner, DMView dmView) {
		dm = new DM();
		dm.setPartner(partner);
		updateMessages();
	}
	
	/**
	 * Updates all messages and the DMView
	 */
	public void updateMessages() {
		Master.appLogger.info(":: Updating messages...");
		this.dm.setMessages(getMessageList(dm.getPartner().getId()));
	}
	
	
	
	/**
	 * Gets the single instance of DMController. Creates instance if it does not exist or the partner changes
	 *
	 * @param partner the partner
	 * @param dmView the dm view
	 * @return single instance of DMController
	 */
	public static DMController getInstance(User partner, DMView dmView) {
		// If not made, make one; if different User, refresh
		if(controller == null) {
			controller = new DMController(partner, dmView);
		}
		else if(!controller.dm.getPartner().getId().equals(partner.getId())) {
			controller = new DMController(partner, dmView);
		}
		
		return controller;
	}
	
	/**
	 * Gets the single instance of DMController.
	 * Should only be used to get an already existing DMController instance
	 *
	 * @return single instance of DMController
	 */
	public static DMController getInstance() {
		return controller;
	}
	
	/**
	 * Send message and add it to the database.
	 *
	 * @param msg the msg
	 */
	@SuppressWarnings("unchecked")
	public void sendMessage(Message msg) {
		this.setNumMessages(this.getNumMessages() + 1);
		JSONObject chat = new JSONObject();
		
		chat.put("userId", msg.getSender());
		chat.put("otherUserId", msg.getReceiver());
		chat.put("message", msg.getText());
		
		HttpPost request = new HttpPost(baseUrl);
		try {
			// Add JSON to the body and headers indicating type
			StringEntity params = new StringEntity(chat.toJSONString());
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);

		    // Execute the request
		    HttpResponse response = httpClient.execute(request);

		    // Get the body of the response
		    HttpEntity entity = response.getEntity();
		    String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);

		    JSONParser parse = new JSONParser();
		    JSONObject o = (JSONObject) parse.parse(json);
		    
		    if(response.getStatusLine().getStatusCode() == 201) {
		    	JSONObject chatter = ((JSONObject) o.get("chat"));
		    	
		    	String id = (String) chatter.get("_id");
		    	msg.setId(id);
		    			    	
		    }
		    else {
		    	Master.appLogger.info("" + response.getStatusLine().getStatusCode());
		    }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			request.releaseConnection();
		}
		
		dm.add(msg);
	}
	
	/**
	 * Gets the chat by id from the database.
	 *
	 * @param id the id
	 * @return the chat by id
	 */
	public Message getChatById(String id) {
		HttpGet request = new HttpGet(baseUrl + id);
		
		try {
			// Add JSON to the body and headers indicating type
		    request.addHeader("content-type", "application/json");

		    // Execute the request
		    HttpResponse response = httpClient.execute(request);

		    // Get the body of the response
		    HttpEntity entity = response.getEntity();
		    String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);

		    JSONParser parse = new JSONParser();
		    JSONObject o = (JSONObject) parse.parse(json);
		    
		    if(response.getStatusLine().getStatusCode() == 200) {
		    	JSONObject chatter = (JSONObject) o.get("chat");
		    	Message msg = new Message();
		    	
		    	msg.setId((String) chatter.get("_id"));
		    	long msTime =  ((Long) chatter.get("dateCreated"));
		    	msg.setSendDate(new Date(msTime));
		    	msg.setReceiver((String) chatter.get("otherUserId"));
		    	msg.setSender((String) chatter.get("userId"));
		    	msg.setText((String) chatter.get("message"));
		    	
		    	return msg;
		    }
		    else {
		    	Master.appLogger.info(":: Problem with get for message..");
		    }
		}
		catch(Exception e) {
			
		}
		finally {
			request.releaseConnection();
		}
		
		return null;
	}
	
	/**
	 * Gets the message list from the database.
	 *
	 * @param otherID the other ID
	 * @return the message list
	 */
	public Set<Message> getMessageList(String otherID) {
		Set<Message> messages = new HashSet<Message>();
		//first request: all the messages from logged in user to the other user.
		HttpGet request = new HttpGet(baseUrl + "from/" + UserController.getInstance().getUser().getId()
				+ "/to/" + otherID);
		
		Master.appLogger.info(":: Getting all messages between " + UserController.getUser().getfName()
				+ " and " + dm.getPartner().getfName());
		
		try {
			// Add JSON to the body and headers indicating type
		    request.addHeader("content-type", "application/json");

		    // Execute the request
		    HttpResponse response = httpClient.execute(request);

		    // Get the body of the response
		    HttpEntity entity = response.getEntity();
		    String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);

		    JSONParser parse = new JSONParser();
		    
		    if(response.getStatusLine().getStatusCode() == 200) {
		    	JSONArray msgArray = (JSONArray) parse.parse(json);
		    	
		    	//loop through each message
		    	for(int i = 0; i < msgArray.size(); i++) {
		    		JSONObject chatter = (JSONObject) msgArray.get(i);
			    	Message msg = new Message();
			    	
			    	msg.setId((String) chatter.get("_id"));
			    	long msTime =  ((Long) chatter.get("dateCreated"));
			    	msg.setSendDate(new Date(msTime));
			    	msg.setReceiver((String) chatter.get("otherUserId"));
			    	msg.setSender((String) chatter.get("userId"));
			    	msg.setText((String) chatter.get("message"));
			    	
			    	messages.add(msg);
		    	}
		    	
		    }
		    else {
		    	Master.appLogger.info(":: Problem with get for message..");
		    }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			request.releaseConnection();
		}
		
		//second request: all the messages from other user to the logged in user.
		request = new HttpGet(baseUrl + "from/" + otherID
				+ "/to/" + UserController.getInstance().getUser().getId());
		
		try {
			// Add JSON to the body and headers indicating type
		    request.addHeader("content-type", "application/json");

		    // Execute the request
		    HttpResponse response = httpClient.execute(request);

		    // Get the body of the response
		    HttpEntity entity = response.getEntity();
		    String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);

		    JSONParser parse = new JSONParser();
		    
		    if(response.getStatusLine().getStatusCode() == 200) {
		    	JSONArray msgArray = (JSONArray) parse.parse(json);
		    	
		    	//loop through each message
		    	for(int i = 0; i < msgArray.size(); i++) {
		    		JSONObject chatter = (JSONObject) msgArray.get(i);
			    	Message msg = new Message();
			    	
			    	msg.setId((String) chatter.get("_id"));
			    	long msTime =  ((Long) chatter.get("dateCreated"));
			    	msg.setSendDate(new Date(msTime));
			    	msg.setReceiver((String) chatter.get("otherUserId"));
			    	msg.setSender((String) chatter.get("userId"));
			    	msg.setText((String) chatter.get("message"));
			    	
			    	messages.add(msg);
		    	}
		    	
		    }
		    else {
		    	Master.appLogger.info(":: Problem with get for message..");
		    }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			request.releaseConnection();
		}
		
		return messages;
	}

	/**
	 * Populate messages to the JtextArea upon receiving updated data.
	 *
	 * @param messageField the message field
	 */
	public void populateMessages(JTextArea messageField) {
		// first get the sorted message list
		updateMessages();
		List<Message> sortedMessages = dm.getSorted();
		if(getNumMessages() != sortedMessages.size()) {
			Master.appLogger.info(":: Populating messages");
			setNumMessages(sortedMessages.size());
			messageField.setText("");
			
			for(int i = 0; i < sortedMessages.size(); i++) {
				//for each message, print the date, then the sender, then the message.
				addMessage(messageField, sortedMessages.get(i));
			}
			messageField.setCaretPosition(messageField.getDocument().getLength());
		}
		
	}
	
	/**
	 * Adds the message to the JTextField.
	 *
	 * @param messageField the message field
	 * @param message the message
	 */
	public void addMessage(JTextArea messageField, Message message) {
		if(message.getSender().contentEquals(UserController.getUser().getId())) {
			//this is us
			messageField.setText(messageField.getText() + "\n\n" + DATE_FORMAT.format(message.getSendDate()) + 
					" " + "You: " + message.getText());
		}
		else {
			messageField.setText(messageField.getText() + "\n\n" + DATE_FORMAT.format(message.getSendDate()) + 
					" " + dm.getPartner().getfName() + " " + dm.getPartner().getlName() + ": " + message.getText());
		}
	}
}