package mq.ws.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mq.service.sender.ProducerService;

@RestController
@RequestMapping("/service")
public class TransferController {
	
	@Autowired
	ProducerService producerService;
	
	@RequestMapping(value = "/sendMail/{p1}", method = RequestMethod.GET)
	public String send(@PathVariable String p1){
		
		final String currentCorrId = UUID.randomUUID().toString();
		
		
		String id = producerService.sendMessage(p1);
		
		return producerService.receiveMessage2(id);
		
		
	}

}
