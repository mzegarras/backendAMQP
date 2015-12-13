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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/test/resources/spring/applicationContextTest1.xml"})
@Ignore
public class MessageServiceTopics {

	
	final static Logger logger = Logger.getLogger(MessageServiceTopics.class);
	
	
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	@Qualifier("jmsDestinationTopic")
	private Destination jmsDestination;

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
		jmsTemplate.send(jmsDestination,new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				TextMessage message = session.createTextMessage("A");
                return message;
			}
		});
		
		assertTrue("sendMessageSimple", true);
	}
	
	
	

	
	@Test
	public void sendMessageCorrelationId() {
		
		jmsTemplate.send(jmsDestination,new MessageCreator() {
			
			public Message createMessage(Session arg0) throws JMSException {
				
				// TODO Auto-generated method stub
				String currentCorrId = UUID.randomUUID().toString();
				TextMessage message = arg0.createTextMessage("B");
                message.setJMSCorrelationID(currentCorrId);
                
                return message;
			}
		});
		
		assertTrue("sendMessageCorrelationId", true);
	}
	
	
	
	
	
	
	
}
