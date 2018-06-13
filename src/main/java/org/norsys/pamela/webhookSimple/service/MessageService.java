package org.norsys.pamela.webhookSimple.service;

import org.norsys.pamela.webhookSimple.event.ArriverMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MessageService {
	
	

	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

	private RestTemplate restTemplate;
	
	private String urlApi;

	@Autowired
	public MessageService(RestTemplateBuilder restTemplateBuilder, @Value("${rest.api.url}") String urlApi) {
		this.restTemplate = restTemplateBuilder.build();
		this.urlApi = urlApi;
	}

	@EventListener
	public void messageRecuEventListener(ArriverMessage messageRecuEvent) {
		String messageGoogle = messageRecuEvent.getMessage();

		logger.debug("=======le message de google {} est en train d'etre ecouter dans le service", messageGoogle + "=============");

		// envoie du message a la destination
		envoyerMessageALaDestination(messageRecuEvent.getMessage(), urlApi);

	}
	
	public ResponseEntity<String> envoiMessage(ArriverMessage messageRecuEvent) {
		String messageGoogle = messageRecuEvent.getMessage();

		logger.debug("=======le message de google {} est en train d'etre ecouter dans le service", messageGoogle + "=============");

		// envoie du message a la destination
		return envoyerMessageALaDestination(messageRecuEvent.getMessage(), urlApi);

	}

	private ResponseEntity<String> envoyerMessageALaDestination(String message, String url) {
		logger.debug("debut de l'envoi du message pour la destination {}", url);
		ResponseEntity<String> reponse = null;
		try {
			//HttpHeaders headers = new HttpHeaders();

			//headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
			//HttpEntity<String> request = new HttpEntity<>(message, headers);
			//Thread.sleep(500);
			reponse = restTemplate.getForEntity(url, String.class);
			
			logger.info("réponse de l'api : {}", reponse.getBody());
			
		} catch (Exception ex) {
			logger.info("Le messageGoogleProcessor a la destination a rencontrer une exeption : {}", ex.getMessage());
		}
		return reponse;
	}



}
