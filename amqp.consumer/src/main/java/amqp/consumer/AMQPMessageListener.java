package amqp.consumer;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AMQPMessageListener implements MessageListener {
	


	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		System.out.println("Sending Mail");
		String request = new String(arg0.getBody(), StandardCharsets.UTF_8);
		System.out.println(request.toUpperCase());
		System.out.println("Sended Mail");
		
	}
       

}
