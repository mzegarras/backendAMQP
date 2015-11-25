package amqp.consumer;


import java.nio.charset.StandardCharsets;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RPCServerListener implements MessageListener {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	private static MessageConverter messageConverter = new SimpleMessageConverter();
	



	private static int producto(int n) {
		if (n == 0) {
			return 0;
		}
		return n * 2;
	}



	public void onMessage(Message requestMessage) {
		// TODO Auto-generated method stub
		
		String request = new String(requestMessage.getBody(), StandardCharsets.UTF_8);
		
		Object obj = request;// messageConverter.fromMessage(requestMessage);
		String str = (String) obj;
		int n = Integer.parseInt(str);
		System.out.println(" [.] producto*2(" + requestMessage + ")");
		String response = "" + producto(n);
		String replyTo = requestMessage.getMessageProperties().getReplyTo();
		rabbitTemplate.send(
				"", 
				replyTo, 
				messageConverter.toMessage(response, null));
		
	}

}