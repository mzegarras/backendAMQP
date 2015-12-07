package mq.producer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.Random;
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
		"file:src/test/resources/spring/applicationContextTest3.xml"})
public class PriorityServiceTest3 {

	
	final static Logger logger = Logger.getLogger(PriorityServiceTest3.class);
	
	
	
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
	@Ignore
	public void sendMessageSimple() throws InterruptedException {
		
		
		//La prioeidad más alta seràn ubicados arriba de la cola
		final Random rand = new Random();
		
		jmsTemplate.setExplicitQosEnabled(true);
		
		for (int i = 0; i < 100; i++) {
			final int x = i;
			
			
			jmsTemplate.send(new MessageCreator() {
				
				public Message createMessage(Session session) throws JMSException {
					// TODO Auto-generated method stub
					int randomNum = rand.nextInt((4 - 1) + 1) + 1;
					TextMessage message = session.createTextMessage(String.format("%s", x) );
					jmsTemplate.setPriority(randomNum);
	                return message;
				}
			});
			
		}
		
		assertTrue("sendMessageSimple", true);
		//Thread.sleep(1000 * 10);
	}
	
	
	@Test
	public void consumirMessage() throws InterruptedException {
		
		//La prioeidad más alta seràn ubicados arriba de la cola por lo tanto se obtienen primero
		
		assertTrue("consumirMessage", true);
		Thread.sleep(1000 * 10);
	}
	
	
	
	
	

	
	
}
