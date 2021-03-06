package mq.service.consumer;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class BalanceMessageService implements javax.jms.MessageListener {
	

	@Autowired
	JmsTemplate jmsTemplate;

	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		
		if(msg instanceof TextMessage) {
			System.out.println(BalanceMessageService.class.getCanonicalName());
			
			try {
				final String message = ((TextMessage) msg).getText() + "balance";
				final String correlationID = msg.getJMSCorrelationID();
				final String messageID = msg.getJMSMessageID();
				
				System.out.println(message);
				System.out.println(msg.getJMSReplyTo());
				
				System.out.println("correlationID");
				System.out.println(correlationID);
				System.out.println("messageID");
				System.out.println(messageID);
				System.out.println("send");
				
				jmsTemplate.send(msg.getJMSReplyTo(), new MessageCreator() {
					
					public Message createMessage(Session session) throws JMSException {
						// TODO Auto-generated method stub
						TextMessage textMessage = session.createTextMessage(message + "Balance Data");
						textMessage.setJMSCorrelationID(correlationID);
						textMessage.setJMSMessageID(messageID);
						return textMessage ;
						
					}
				});
				
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				System.out.println(BalanceMessageService.class.getCanonicalName() + "error");
				System.err.println(e);
			}
			
			
			System.out.println(BalanceMessageService.class.getCanonicalName() + "end");
		}else{
			System.out.println(BalanceMessageService.class.getCanonicalName() + "null");
		}
	}

}
