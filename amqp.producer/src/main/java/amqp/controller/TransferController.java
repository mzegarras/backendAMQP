package amqp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import amqp.producer.ProducerService;

@RestController
@RequestMapping("/service")
public class TransferController {
	
	@Autowired
	ProducerService producerService;
	
	@RequestMapping(value = "/sendMail/{p1}", method = RequestMethod.GET)
	public String send(@PathVariable String p1){
		producerService.sendMessage(p1);
		return "OK";
	}
	
	@RequestMapping(value = "/calculate/{p1}", method = RequestMethod.GET)
	public String sendRecieve(@PathVariable String p1){
		producerService.sendRecieve(p1);
		return "OK";
	}

}
