package mq.producer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/test/resources/spring/applicationContextTest0.xml"})
public class MessageServiceTest {

	
	final static Logger logger = Logger.getLogger(MessageServiceTest.class);
	
	
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Value("${queue}")
    public String queue;

	@Value("${queue_reply}")
    public String queue_reply;

	
	/**
	 * Test setup.
	 */
	@Before
	public void setUp() {
		 BasicConfigurator.configure();
	}
	
	
	@Test
	public void jsmTemplateNull(){
		assertNotNull(jmsTemplate);
	}
	
	
	@Test
	public void sendMessageSimple() {
		
		
		for (int i = 0; i < 100; i++) {
			final int x = i;
			jmsTemplate.send(new MessageCreator() {
				
				public Message createMessage(Session session) throws JMSException {
					// TODO Auto-generated method stub
					TextMessage message = session.createTextMessage(String.format("sendMessage:%s", x) );
	                return message;
				}
			});
			
		}
		
		assertTrue("sendMessageSimple", true);
	}
	
	
	
	
	@Test
	public void sendMessageReply() {
		

			jmsTemplate.send(queue,new MessageCreator() {
				
				public Message createMessage(Session arg0) throws JMSException {
					
					// TODO Auto-generated method stub
					//String currentCorrId = UUID.randomUUID().toString();
					//System.out.println("sendMessageConcatena");
					TextMessage message = arg0.createTextMessage(String.format("sendMessage2_reply:%s", "test"));
					
					Queue queue = arg0.createQueue(queue_reply);
	                message.setJMSReplyTo(queue);
	                //message.setJMSCorrelationID(currentCorrId);
	                
	                return message;
				}
			});
		
	
		assertTrue("sendMessageReply", true);
	}
	
	
	@Test
	public void sendMessageCorrelationId() {
		
		jmsTemplate.send(queue,new MessageCreator() {
			
			public Message createMessage(Session arg0) throws JMSException {
				
				// TODO Auto-generated method stub
				String currentCorrId = UUID.randomUUID().toString();
				TextMessage message = arg0.createTextMessage("sendMessage2_reply");
				Queue queue = arg0.createQueue(queue_reply);
                message.setJMSReplyTo(queue);
                message.setJMSPriority(0);
                message.setJMSCorrelationID(currentCorrId);
                
                return message;
			}
		});
		
		assertTrue("sendMessageCorrelationId", true);
	}
	
	
	
	
	
	
	
}
