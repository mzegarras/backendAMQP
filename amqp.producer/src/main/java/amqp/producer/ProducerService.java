package amqp.producer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
	
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public void sendMessage(String p1){
		String exhangeName = "EXCHANGE.MAIL";
		MessageProperties messageProperties = new MessageProperties();
		Message message = new Message(p1.getBytes(), messageProperties);
		rabbitTemplate.send(exhangeName, "", message);
	}
	
	public void sendRecieve(String p1){
			MessageProperties messageProperties = new MessageProperties();
		Message message = new Message(p1.getBytes(), messageProperties);
		
		String requestQueueName = "math.producto";
		//String message = "10";
		//Message reply = rabbitTemplate.sendAndReceive("", requestQueueName, new SimpleMessageConverter().toMessage(message, null));
		Message reply = rabbitTemplate.sendAndReceive(requestQueueName, "", message);
		//
		if (reply == null) {
			System.out.println("null");
		} else {
			System.out.println("La respuesta es:");
			System.out.println("============");
			System.out.println(reply);
			System.out.println("============");
		
	}

}
}
