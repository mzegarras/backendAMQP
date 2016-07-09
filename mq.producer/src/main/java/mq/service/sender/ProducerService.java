package mq.service.sender;


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

	/*public void sendMessageUpper(final String p1) {
		jmsTemplate.send("Q1.RQ",new MessageCreator() {
			public Message createMessage(Session arg0) throws JMSException {
				
				// TODO Auto-generated method stub
				System.out.println("==============================");
				System.out.println("sendMessageUpper");
				TextMessage message = arg0.createTextMessage(p1);
				Queue queue = arg0.createQueue("Q1.RS");
                message.setJMSReplyTo(queue);
                System.out.println(p1);
                System.out.println("setJMSReplyTo");
                System.out.println(queue);
                System.out.println("sendMessageUpper END");
                System.out.println("==============================");
                return message;
			}
		});
	}
	
	public void sendMessageBalance(final String p1) {
		jmsTemplate.send("Q3.RQ",new MessageCreator() {
			public Message createMessage(Session arg0) throws JMSException {
				
				// TODO Auto-generated method stub
				System.out.println("==============================");
				System.out.println("sendMessageBalance");
				TextMessage message = arg0.createTextMessage(p1);
				Queue queue = arg0.createQueue("Q3.RS");
                message.setJMSReplyTo(queue);
                System.out.println(p1);
                System.out.println("setJMSReplyTo");
                System.out.println(queue);
                System.out.println("sendMessageBalance END");
                System.out.println("==============================");
                return message;
			}
		});
	}

	public void sendMessageConcatena(final String p1) {
		jmsTemplate.send("Q2.RQ",new MessageCreator() {
			public Message createMessage(Session arg0) throws JMSException {
				
				// TODO Auto-generated method stub
				System.out.println("==============================");
				System.out.println("sendMessageConcatena");
				TextMessage message = arg0.createTextMessage(p1);
				Queue queue = arg0.createQueue("Q2.RS");
                message.setJMSReplyTo(queue);
                System.out.println(p1);
                System.out.println("setJMSReplyTo");
                System.out.println(queue);
                System.out.println("sendMessageConcatena END");
                System.out.println("==============================");
                return message;
			}
		});
	}*/
	
	
	
	public String sendMessage(final String p1) {
		
		System.out.println("==============================");
		System.out.println("======sendMessage2=======================");
		
		final String currentCorrId = UUID.randomUUID().toString();
		System.out.println("======currentCorrId:" + currentCorrId);
		
		jmsTemplate.send("Q1.RQ",new MessageCreator() {
			
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
		
		jmsTemplate.send("Q2.RQ",new MessageCreator() {
			
			public Message createMessage(Session arg0) throws JMSException {
				
				// TODO Auto-generated method stub
				
				System.out.println("sendMessageConcatena");
				TextMessage message = arg0.createTextMessage(p1);
				Queue queue = arg0.createQueue("Q2.RS");
                message.setJMSReplyTo(queue);
                message.setJMSCorrelationID(currentCorrId);
                System.out.println(p1);
                System.out.println("setJMSReplyTo");
                System.out.println(queue);
                System.out.println("sendMessageConcatena END");
                System.out.println("==============================");
                return message;
			}
		});
		
		jmsTemplate.send("Q3.RQ",new MessageCreator() {
			
			public Message createMessage(Session arg0) throws JMSException {
				
				// TODO Auto-generated method stub
				
				System.out.println("sendMessageBalance");
				TextMessage message = arg0.createTextMessage(p1);
				Queue queue = arg0.createQueue("Q3.RS");
                message.setJMSReplyTo(queue);
                message.setJMSCorrelationID(currentCorrId);
                System.out.println(p1);
                System.out.println("setJMSReplyTo");
                System.out.println(queue);
                System.out.println("sendMessageBalance END");
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
		
		
		Message m1 = jmsTemplate.receiveSelected("queue://QM1/Q1.RS", "JMSCorrelationID='" + currentCorrId + "'");
		Message m2 = jmsTemplate.receiveSelected("queue://QM1/Q2.RS", "JMSCorrelationID='" + currentCorrId + "'");
		Message m3 = jmsTemplate.receiveSelected("queue://QM1/Q3.RS", "JMSCorrelationID='" + currentCorrId + "'");
		
		
		if(m1==null){
			return null;
		}else{
			String message = "nullo";
			String message1,message2,message3;
			try {
				message1 = ((TextMessage) m1).getText();
				message2 = ((TextMessage) m2).getText();
				message3 = ((TextMessage) m3).getText();
				
				message = message1 +"|" + message2 + "|" + message3;
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("error");
				message1 = null;
				
			}
			
			return message;
		}
		
		
	}
}
