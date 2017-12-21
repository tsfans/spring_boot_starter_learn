package com.yl.spring_boot_starter_learn.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.yl.spring_boot_starter_learn.domain.WsMessage;
import com.yl.spring_boot_starter_learn.domain.WsResponse;

@Controller
public class WebSocketController {
	
	@Autowired
	private SimpMessagingTemplate msgTemplate;

	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public WsResponse say(WsMessage message) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new WsResponse("Welcome "+message.getName());
	}
	
	@MessageMapping("/chat")
	public void handleChat(Principal principal,String msg) {
		if(principal.getName().equals("swift")) {
			msgTemplate.convertAndSendToUser("hl", "/queue/notifications", principal.getName()+":"+msg);
		}else {
			msgTemplate.convertAndSendToUser("swift", "/queue/notifications", principal.getName()+":"+msg);
		}
	}
}
