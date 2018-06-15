package org.norsys.pamela.webhookSimple.listenner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * un listener
 * @author panou
 *
 */
@Component
public class MessageListener  implements ApplicationListener<ContextRefreshedEvent>{
	
	private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.debug("========un message viens d'etre publier je l'ecoute actuellement=============");
	}

}
