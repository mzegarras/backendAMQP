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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;






@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/test/resources/spring/applicationContextTest5.xml"})
public class ConsumerMessageTest {
	
	
	@Value("${queue_reply}")
    public String queue_reply;
	
	@Value("${queue}")
    public String queue;
	
	
	
	final static Logger logger = Logger.getLogger(ConsumerMessageTest.class);
	

	
	/**
	 * Test setup.
	 */
	@Before
	public void setUp() {
		 BasicConfigurator.configure();
		 logger.info(queue_reply);
		 logger.info(queue);
	}
	

	@Test
	public void consumirMessage() throws InterruptedException {
	
		
		assertTrue("consumirMessage", true);
		Thread.sleep(1000 * 500);
	}
	
	
	
	
	
}
