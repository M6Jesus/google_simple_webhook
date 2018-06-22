package org.norsys.pamela.webhookSimple.controller;

import java.util.ArrayList;
import java.util.List;

import org.norsys.pamela.webhookSimple.event.ArriverMessage;
import org.norsys.pamela.webhookSimple.model.request.QueryResult;
import org.norsys.pamela.webhookSimple.model.request.Request;
import org.norsys.pamela.webhookSimple.model.response.Google;
import org.norsys.pamela.webhookSimple.model.response.Items;
import org.norsys.pamela.webhookSimple.model.response.Payload;
import org.norsys.pamela.webhookSimple.model.response.Reponse;
import org.norsys.pamela.webhookSimple.model.response.RichResponse;
import org.norsys.pamela.webhookSimple.model.response.SimpleResponse;
import org.norsys.pamela.webhookSimple.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * cette classe est le controlleur point d'entrée des données provenant de dialogflow
 * @author panou
 *
 */
@RestController
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	/**
	 * le publisher de l'evenement
	 */
	@Autowired
	public ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	public MessageService messageService;

	public void faisDesTrucsEtPublieDesEvenements(final String message) {
		System.out.println("Publishing custom event ...");
		// le this ici est l'instance meme de la classe
		ArriverMessage arriverMessage = new ArriverMessage(this, message);
		applicationEventPublisher.publishEvent(arriverMessage);
	}
	/**
	 * 
	 * @param request: reprensente l'objet requete provenant de dialogflow
	 * @return  ResponseEntity<Reponse> a la requette
	 */
	@PostMapping("/message")
	public ResponseEntity<Reponse> posterUnMessage(@RequestBody Request request) {
		logger.debug("========message arriver a la webhook========================");
		QueryResult queryResult = request.getQueryResult();
		String valeurQuestion = queryResult.getQueryText();
		// message de fin qu cas ou je recois "merci google"

		if (valeurQuestion.contains("merci") || valeurQuestion.contains("google")) {
			Reponse reponse = creationReponse("je t'en prie pamela. Qu'est ce que je peux faire pour toi");
			return ResponseEntity.status(HttpStatus.OK).body(reponse);
			
		}else {
			ArriverMessage arriverMessage = new ArriverMessage(this, valeurQuestion);
			// publication de l'evenement
			applicationEventPublisher.publishEvent(arriverMessage);
			ResponseEntity<String> reponseApi = messageService.envoiMessage(arriverMessage);
			if (reponseApi != null) {
				String reponseApiString = reponseApi.getBody();

				// au cas ou j'ai une reponse en anglais true ou false
				if (reponseApiString.contains("true")) {
					reponseApiString = reponseApiString.replace("true", "oui, effectivement");
				} else if (reponseApiString.contains("false")) {
					reponseApiString = reponseApiString.replace("false", "non, pas du tout");
				}
				Reponse reponse = creationReponse(reponseApiString);
				return ResponseEntity.status(HttpStatus.OK).body(reponse);
			} else {
				Reponse reponse = creationReponse("desoler je n'ai pas cette information");

				return ResponseEntity.status(HttpStatus.OK).body(reponse);
			}
		}

	}
	/**
	 * 
	 * @param messageReponse le message à retourner à l'utilisateur sur dialogflow
	 * @return une reponse creer comportant le message a retourner
	 */
	private Reponse creationReponse(String messageReponse) {
		Reponse reponse = new Reponse();
		SimpleResponse simpleResponse = new SimpleResponse();
		simpleResponse.setTextToSpeech(messageReponse);
		simpleResponse.setDisplayText(messageReponse);

		Items items = new Items();
		items.setSimpleResponse(simpleResponse);

		RichResponse richResponse = new RichResponse();
		List<Items> liste = new ArrayList<>();
		liste.add(items);
		richResponse.setItems(liste);
		Google google = new Google();
		google.setExpectUserResponse(true);
		google.setRichResponse(richResponse);
		Payload payload = new Payload();
		payload.setGoogle(google);
		reponse.setPayload(payload);

		// List<FulfillmentMessages> fulfillmentMessages = new ArrayList<>();
		org.norsys.pamela.webhookSimple.model.response.FulfillmentMessages fulfillmentMessages = new org.norsys.pamela.webhookSimple.model.response.FulfillmentMessages();

		org.norsys.pamela.webhookSimple.model.response.Text texte = new org.norsys.pamela.webhookSimple.model.response.Text();

		List<String> listess = new ArrayList<>();
		listess.add(messageReponse);
		texte.setText(listess);
		fulfillmentMessages.setText(texte);

		List<org.norsys.pamela.webhookSimple.model.response.FulfillmentMessages> listes = new ArrayList<>();
		listes.add(fulfillmentMessages);
		reponse.setFulfillmentMessages(listes);
		logger.debug("========message arriver a la webhook et publier=============");

		return reponse;
	}
}
