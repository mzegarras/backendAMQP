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

import hello.wsdl.GetCityForecastByZIPResponse;
import services.client.WeatherClient;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/webcontext/client-config-ws-weather.xml"})
public class WebServiceTest {
	
	
	
	
	
	@Autowired
	WeatherClient weatherClient;
	
	/**
	 * Test setup.
	 */
	@Before
	public void setUp() {
		//message = "TestSpringJMSMQ test message.";
	}
	
	@Test
	public void notNull(){
		assertTrue("Not nullo",weatherClient!=null);
	}
	
	@Test
	public void test01(){
		
		GetCityForecastByZIPResponse response= weatherClient.getCityForecastByZip("94304");
		System.out.println( response.getGetCityForecastByZIPResult().getResponseText());
		System.out.println( response.getGetCityForecastByZIPResult().getCity());
		System.out.println( response.getGetCityForecastByZIPResult().getState());
		assertTrue("Not nullo",response!=null);
	}

}
