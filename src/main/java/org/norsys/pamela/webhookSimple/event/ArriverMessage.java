package org.norsys.pamela.webhookSimple.event;

import org.springframework.context.ApplicationEvent;


public class ArriverMessage extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;
	private String message;
	public String getMessage() {
		return message;
	}
	public ArriverMessage(Object source, String message) {
		super(source);
		this.message = message;
		
	}
	
}
