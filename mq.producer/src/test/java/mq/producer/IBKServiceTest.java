package mq.producer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.junit.Ignore;
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
		"file:src/test/resources/spring/applicationContextTest6.xml"})
public class IBKServiceTest {

	
	final static Logger logger = Logger.getLogger(IBKServiceTest.class);
	
	
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Value("${queue1_req}")
    public String req1;
	
	@Value("${queue1_res}")
    public String res1;
	/**
	 * Test setup.
	 */
	@Before
	public void setUp() {
		 BasicConfigurator.configure();
		 logger.debug(String.format("req1:%s", req1));
		 logger.debug(String.format("res1:%s", res1));
	}
	
	
	@Test
	public void jsmTemplateNull(){
		assertNotNull(jmsTemplate);
	}
	
	
	@Test
	public void sendMessageSimple() {
		
		
		for (int i = 0; i < 10; i++) {
			final int x = i;
			jmsTemplate.send(new MessageCreator() {
				
				public Message createMessage(Session session) throws JMSException {
					// TODO Auto-generated method stub
					TextMessage message = session.createTextMessage(String.format("sendMessage Charly Chon:%s", x) );
	                return message;
				}
			});
			
		}
		
		assertTrue("sendMessageSimple", true);
	}
	
	@Test
	public void consumirMessage() throws InterruptedException {
		
		assertTrue("consumirMessage", true);
		Thread.sleep(1000 * 10);
	}
	
	
	
	
	/*@Test
	@Ignore
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
	@Ignore
	public void sendMessageCorrelationId() {
		
		jmsTemplate.send(queue,new MessageCreator() {
			
			public Message createMessage(Session arg0) throws JMSException {
				
				// TODO Auto-generated method stub
				String currentCorrId = UUID.randomUUID().toString();
				TextMessage message = arg0.createTextMessage(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
				Queue queue = arg0.createQueue(queue_reply);
                message.setJMSReplyTo(queue);
                message.setJMSPriority(0);
                message.setJMSCorrelationID(currentCorrId);
                
                return message;
			}
		});
		
		assertTrue("sendMessageCorrelationId", true);
	}*/
	
	
	
	
	
	
	
}
