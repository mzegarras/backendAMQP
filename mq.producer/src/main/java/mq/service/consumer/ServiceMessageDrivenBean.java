package mq.service.consumer;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;


public class ServiceMessageDrivenBean implements MessageListener {
	
	
	final static Logger logger = Logger.getLogger(ServiceMessageDrivenBean.class);
	
	
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		logger.info("Message Driven Bean: New Message");
		logger.info(message.toString());
	}

}
