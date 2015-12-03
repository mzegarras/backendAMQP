package mq.producer;


import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import campanias.proxy.CampaniasWebService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/test/resources/spring/applicationContextTest2.xml"})
public class WebServiceTest {
	
	
	
	
	
	@Autowired
	CampaniasWebService campaniasWebService;
	
	/**
	 * Test setup.
	 */
	@Before
	public void setUp() {
		//message = "TestSpringJMSMQ test message.";
	}
	
	
	@Test
	public void notNull(){
		
		campaniasWebService.doCall();
		
		assertTrue("Not nullo",campaniasWebService!=null);
	}

}
