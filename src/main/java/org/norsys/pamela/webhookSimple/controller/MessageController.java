package org.norsys.pamela.webhookSimple.controller;

import java.util.ArrayList;
import java.util.List;

import org.norsys.pamela.webhookSimple.event.ArriverMessage;
import org.norsys.pamela.webhookSimple.model.request.FulfillmentMessages;
import org.norsys.pamela.webhookSimple.model.request.QueryResult;
import org.norsys.pamela.webhookSimple.model.request.Request;
import org.norsys.pamela.webhookSimple.model.request.Text;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2QueryResult;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookRequest;

@RestController
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	// le publisher d'evenement
	public ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	public MessageService messageService;

	public void faisDesTrucsEtPublieDesEvenements(final String message) {
		System.out.println("Publishing custom event ...");
		// le this ici est l'instance meme de la classe
		ArriverMessage arriverMessage = new ArriverMessage(this, message);
		applicationEventPublisher.publishEvent(arriverMessage);
	}

	@PostMapping("/message")
	public ResponseEntity<Reponse> posterUnMessage(@RequestBody Request request) {
		logger.debug("========message arriver a la webhook========================");
		QueryResult queryResult = request.getQueryResult();
//		Parameters parameter = queryResult.getParameters();
//		Map<String, String> param = parameter.getParameters();
		String valeurQuestion = queryResult.getQueryText();
//		String valeurQuestion;
		Reponse reponse = new Reponse();
		
		if (valeurQuestion != null) {
//			valeurQuestion = param.get("question");
			ArriverMessage arriverMessage = new ArriverMessage(this, valeurQuestion);
			// publication de l'evenement
			applicationEventPublisher.publishEvent(arriverMessage);
			
			ResponseEntity<String> reponseApi = messageService.envoiMessage(arriverMessage);
			String reponseApiString = reponseApi.getBody();
			
			SimpleResponse simpleResponse = new SimpleResponse();
			simpleResponse.setTextToSpeech(reponseApiString);
			simpleResponse.setDisplayText(reponseApiString);
			
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
			listess.add(reponseApiString);
			texte.setText(listess);
			fulfillmentMessages.setText(texte);

			List<org.norsys.pamela.webhookSimple.model.response.FulfillmentMessages> listes = new ArrayList<>();
			listes.add(fulfillmentMessages);
			reponse.setFulfillmentMessages(listes);

			

			return ResponseEntity.status(HttpStatus.OK).body(reponse);
		}

		logger.debug("========message arriver a la webhook et publier=============");
		return ResponseEntity.status(HttpStatus.OK).body(reponse);
	}

	@PostMapping("/message2")
	@ResponseBody
	public GoogleCloudDialogflowV2QueryResult posterUnMessage2(
			@RequestBody GoogleCloudDialogflowV2WebhookRequest request) {
		logger.debug("========message arriver a la webhook========================");

		// try (SessionsClient sessionsClient = SessionsClient.create()) {
		// // Set the session name using the sessionId (UUID) and projectID
		// (my-project-id)
		// //SessionName session = SessionName.of(projectId, sessionId);
		// SessionName session = SessionName.of("01", "001");
		// System.out.println("Session Path: " + session.toString());
		//
		// Builder textInput = TextInput.newBuilder().setText("enfin enfin
		// ouuff").setLanguageCode("en-FR");
		// DetectIntentResponse response = sessionsClient.detectIntent(request);
		// }

		GoogleCloudDialogflowV2QueryResult t = request.getQueryResult();
		t.setFulfillmentText("enfin enfin");

		System.out.println("****======**===== le mot est {}");
		// QueryResult aa = request.getQueryResult();
		// QueryResult q = request.getParameter("QueryResult");
		// ArriverMessage arriverMessage = new ArriverMessage(this, message);
		// //publication de l'evenement
		// applicationEventPublisher.publishEvent(arriverMessage);
		//
		logger.debug("========message arriver a la webhook et publier=============");
		//
		// maintenant que le message est arriver et publier il faut que je recupere
		// l'url de l'api ou je l'envoie
		// QueryResult q = new QueryResult();
		// QueryResult.newBuilder().setFulfillmentText("marche oooooohhh!!!!");
		return t;

	}

	// @PostMapping("/message3")
	// @ResponseBody
	// public WebhookResponse essai3(@RequestBody WebhookRequest request) {
	// if (request == null) {
	// System.out.println("nulll");
	// }
	// System.out.println(request.toString());
	// QueryResult q = request.getQueryResult();
	// //Reponse rep = new Reponse("debrouille toi a me repondre");
	// try {
	// MavenXpp3Reader reader = new MavenXpp3Reader();
	// Model model = reader.read(new FileReader("pom.xml"));
	// // System.out.println(model.getId());
	// // System.out.println(model.getGroupId());
	// // System.out.println(model.getArtifactId());
	// // System.out.println(model.getVersion());
	// List<String> list = null;
	// list.add("parle oooo");
	// MessageController.detectIntentTexts(model.getProperties().getProperty("projectId"),
	// list,
	// model.getProperties().getProperty("sessionId"), "en-FR");
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return
	// WebhookResponse.newBuilder().setFulfillmentText(rep.toString()).build();
	// }

	@PostMapping("/message4")
	@ResponseBody
	public ResponseEntity<Request> essai4(@RequestBody Request request) {

		logger.info(request.toString());
		logger.debug("======= la requete recu est {}============================", request.toString());

		String textquery = request.getQueryResult().getQueryText();

		QueryResult queryResult = request.getQueryResult();
		queryResult.setFulfillmentText("tu va parler quand?");
		queryResult.setAllRequiredParamsPresent(false);

		List<String> list = new ArrayList<>();
		list.add("tu va parler merde!!!!");

		Text text = new Text();
		text.setText(list);
		FulfillmentMessages message = new FulfillmentMessages();
		message.setText(text);

		List<FulfillmentMessages> fulfillmentMessages = new ArrayList<>();
		fulfillmentMessages.add(0, message);

		queryResult.setFulfillmentMessages(fulfillmentMessages);

		request.setQueryResult(queryResult);
		//
		//
		//
		// Reponse reponse = new Reponse();
		// FulfillmentText text5 = new FulfillmentText();
		// text5.setFulfillmentText(textquery);
		// reponse.setFulfillmentText(text5);

		return ResponseEntity.status(HttpStatus.OK).body(request);
	}

	@PostMapping("/message5")
	@ResponseBody
	public ResponseEntity<Reponse> essai5(@RequestBody Request request) {

		logger.info(request.toString());
		logger.debug("======= la requete recu est {}============================", request.toString());

		SimpleResponse simpleResponse = new SimpleResponse();
		simpleResponse.setTextToSpeech("je veux que tu apparaisse en speech.....");
		simpleResponse.setDisplayText("je veux que tu apparaisse en texte.....");
		Items items = new Items();
		items.setSimpleResponse(simpleResponse);
		Reponse reponse = new Reponse();
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
		listess.add("essai numero oufff");
		texte.setText(listess);
		fulfillmentMessages.setText(texte);

		List<org.norsys.pamela.webhookSimple.model.response.FulfillmentMessages> listes = new ArrayList<>();
		listes.add(fulfillmentMessages);
		reponse.setFulfillmentMessages(listes);

		if (request.getQueryResult().getAction().equals("debut")) {
			return ResponseEntity.status(HttpStatus.OK).body(reponse);
		}

		return ResponseEntity.status(HttpStatus.OK).body(reponse);

	}

	/**
	 * Returns the result of detect intent with texts as inputs.
	 *
	 * Using the same `session_id` between requests allows continuation of the
	 * conversation.
	 * 
	 * @param projectId
	 *            Project/Agent Id.
	 * @param texts
	 *            The text intents to be detected based on what a user says.
	 * @param sessionId
	 *            Identifier of the DetectIntent session.
	 * @param languageCode
	 *            Language code of the query.
	 */
	// public static void detectIntentTexts(String projectId, List<String> texts,
	// String sessionId, String languageCode)
	// throws Exception {
	// // Instantiates a client
	// try (SessionsClient sessionsClient = SessionsClient.create()) {
	// // Set the session name using the sessionId (UUID) and projectID
	// (my-project-id)
	// SessionName session = SessionName.of(projectId, sessionId);
	// System.out.println("Session Path: " + session.toString());
	//
	// // Detect intents for each text input
	// for (String text : texts) {
	// // Set the text (hello) and language code (en-US) for the query
	// Builder textInput =
	// TextInput.newBuilder().setText(text).setLanguageCode(languageCode);
	//
	// // Build the query with the TextInput
	// //QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
	//
	// // Performs the detect intent request
	//// DetectIntentResponse response = sessionsClient.detectIntent(session,
	// queryInput);
	////
	//// // Display the query result
	//// QueryResult queryResult = response.getQueryResult();
	//
	// System.out.println("====================");
	// System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
	// System.out.format("Detected Intent: %s (confidence: %f)\n",
	// queryResult.getIntent().getDisplayName(),
	// queryResult.getIntentDetectionConfidence());
	// System.out.format("Fulfillment Text: '%s'\n",
	// queryResult.getFulfillmentText());
	// }
	// }
	// }

	@GetMapping("/getIt")
	public ResponseEntity<String> posterUnMessageEnGet() {
		logger.debug("========message arriver a la webhook========================");

		ArriverMessage arriverMessage = new ArriverMessage(this, "message de test via le get");
		return messageService.envoiMessage(arriverMessage);
	}

	// @Override
	// protected void doWebhook(AIWebhookRequest input, Fulfillment output) {
	// String action = input.getResult().getAction();
	//
	// if(actionMap.containsKey(action)){
	// Action botAction = actionMap.get(action);
	// botAction.execute(input.getResult(), output);
	// }
	// }
	// @Override
	// protected void doWebhook(AIWebhookRequest input, Fulfillment output) {
	// String action = input.getResult().getAction();
	//
	// if(action.equals("myAction")){
	// // do something with the action
	// }
	// }
	//

}
