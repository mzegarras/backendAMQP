package mq.producer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.BasicConfigurator;
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
		"file:src/test/resources/spring/applicationContextTest0.xml"})
public class MessageServiceTest {

	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Value("${queue}")
    public String queue;

	
	
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
	public void testSendMessage() {
		
		jmsTemplate.send("Q2.RQ",new MessageCreator() {
			
			public Message createMessage(Session arg0) throws JMSException {
				
				// TODO Auto-generated method stub
				String currentCorrId = UUID.randomUUID().toString();
				System.out.println("sendMessageConcatena");
				TextMessage message = arg0.createTextMessage("Test MSG");
				Queue queue = arg0.createQueue("Q2.RS");
                message.setJMSReplyTo(queue);
                message.setJMSCorrelationID(currentCorrId);
                
                return message;
			}
		});
		
		assertTrue("This will succeed.", true);
	}
	
	
}
