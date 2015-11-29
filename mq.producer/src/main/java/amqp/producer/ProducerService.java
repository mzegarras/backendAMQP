package amqp.producer;


import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

	@Autowired
	JmsTemplate jmsTemplate;

	public void sendMessage(final String p1) {
		jmsTemplate.send(new MessageCreator() {
			
			public Message createMessage(Session arg0) throws JMSException {
				
				// TODO Auto-generated method stub
				System.out.println("==============================");
				System.out.println("ProducerService");
				TextMessage message = arg0.createTextMessage(p1);
				Queue queue = arg0.createQueue("Q1.RS");
                message.setJMSReplyTo(queue);
                System.out.println(p1);
                System.out.println("setJMSReplyTo");
                System.out.println(queue);
                System.out.println("ProducerService END");
                System.out.println("==============================");
                return message;
			}
		});
	}
	
	
	
	public String sendMessage2(final String p1) {
		
		System.out.println("==============================");
		System.out.println("======sendMessage2=======================");
		
		final String currentCorrId = UUID.randomUUID().toString();
		System.out.println("======currentCorrId:" + currentCorrId);
		
		jmsTemplate.send(new MessageCreator() {
			
			public Message createMessage(Session arg0) throws JMSException {
				
				// TODO Auto-generated method stub
				
				System.out.println("ProducerService");
				TextMessage message = arg0.createTextMessage(p1);
				Queue queue = arg0.createQueue("Q1.RS");
                message.setJMSReplyTo(queue);
                message.setJMSCorrelationID(currentCorrId);
                System.out.println(p1);
                System.out.println("setJMSReplyTo");
                System.out.println(queue);
                System.out.println("ProducerService END");
                System.out.println("==============================");
                return message;
			}
		});
		
		return currentCorrId;
	}
	
	
	public String receiveMessage2(final String currentCorrId) {
		
		System.out.println("==============================");
		System.out.println("======receiveMessage2=======================");
		System.out.println("======currentCorrId:" + currentCorrId);
		
		
		Message m = jmsTemplate.receiveSelected("queue://QM1/Q1.RS", "JMSCorrelationID='" + currentCorrId + "'");
		System.out.println(m);
		
		if(m==null){
			return null;
		}else{
			
			String message;
			try {
				message = ((TextMessage) m).getText().toUpperCase();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("error");
				message = null;
				
			}
			
			return message;
		}
		
		
	}
}
