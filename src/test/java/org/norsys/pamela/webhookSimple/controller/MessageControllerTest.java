package org.norsys.pamela.webhookSimple.controller;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.norsys.pamela.webhookSimple.model.InterfaceReponses.DifferentTypeReponses;
import org.norsys.pamela.webhookSimple.model.demandePermission.Data;
import org.norsys.pamela.webhookSimple.model.demandePermission.DemandePermission;
import org.norsys.pamela.webhookSimple.model.demandePermission.GoogleDemandePermission;
import org.norsys.pamela.webhookSimple.model.demandePermission.PayloadDemandePermission;
import org.norsys.pamela.webhookSimple.model.demandePermission.SystemIntent;
import org.norsys.pamela.webhookSimple.model.request.Arguments;
import org.norsys.pamela.webhookSimple.model.request.Device;
import org.norsys.pamela.webhookSimple.model.request.Inputs;
import org.norsys.pamela.webhookSimple.model.request.Location;
import org.norsys.pamela.webhookSimple.model.request.OriginalDetectIntentRequest;
import org.norsys.pamela.webhookSimple.model.request.PayloadRequest;
import org.norsys.pamela.webhookSimple.model.request.Profile;
import org.norsys.pamela.webhookSimple.model.request.QueryResult;
import org.norsys.pamela.webhookSimple.model.request.Request;
import org.norsys.pamela.webhookSimple.model.request.User;
import org.norsys.pamela.webhookSimple.model.response.Google;
import org.norsys.pamela.webhookSimple.model.response.Items;
import org.norsys.pamela.webhookSimple.model.response.Payload;
import org.norsys.pamela.webhookSimple.model.response.Reponse;
import org.norsys.pamela.webhookSimple.model.response.RichResponse;
import org.norsys.pamela.webhookSimple.model.response.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessageControllerTest {

	@Autowired
	private MessageController messageController;

	@Test
	public void devrais_poster_un_message_simple() {
		// arrange
		Request request = creationRequeteSimple("merci", "conversation-intent");
		Reponse reponse = CreationReponseSimple("je t'en prie pamela. Qu'est ce que je peux faire pour toi");
		ResponseEntity<DifferentTypeReponses> expected = ResponseEntity.ok().body(reponse);

		// act
		ResponseEntity<DifferentTypeReponses> reponseController = messageController.posterUnMessage(request);

		// assert
		Assertions.assertThat(reponseController).isEqualToComparingFieldByFieldRecursively(expected);

	}

	@Test
	public void devrais_poster_un_message_permission() {
		// arrange
		Request request = creationRequeteSimple("trouve ma position", "request_permission");
		DemandePermission demandePermission = demandePermission();
		ResponseEntity<DifferentTypeReponses> expected = ResponseEntity.status(HttpStatus.OK).body(demandePermission);
		// act
		ResponseEntity<DifferentTypeReponses> reponseController = messageController.posterUnMessage(request);
		// assert
		Assertions.assertThat(reponseController).isEqualToComparingFieldByFieldRecursively(expected);

	}

	@Test
	public void devrais_poster_un_message_User_info_apres_une_permission() {
		// arrange
		Request request = creationRequeteUserInfo("trouve ma position", "user_info", true, "true");
		String adresse = "Atrium, boulevard vivier merle, 107 109 Boulevard Marius Vivier Merle, 69003 Lyon, France";
		String ville = "Lyon";
		String displayName = "PAMELA ANOU";
		Reponse reponse = CreationReponseSimple("Vous vous trouvez actuellement au " + adresse + " votre ville est "
				+ ville + " et votre nom est  " + displayName);
		ResponseEntity<DifferentTypeReponses> expected = ResponseEntity.status(HttpStatus.OK).body(reponse);

		// act
		ResponseEntity<DifferentTypeReponses> reponseController = messageController.posterUnMessage(request);

		// assert
		Assertions.assertThat(reponseController).isEqualToComparingFieldByFieldRecursively(expected);

	}

	@Test
	public void devrais_poster_un_message_user_info_en_cas_de_non_permission() {
		// arrange
		Request request = creationRequeteUserInfo("trouve ma position", "user_info", false, "false");
		Reponse reponse = CreationReponseSimple(
				"Vous ne m'avez pas donner de permission pour acceder à vos informations personnelles");
		ResponseEntity<DifferentTypeReponses> expected = ResponseEntity.status(HttpStatus.OK).body(reponse);
		// act
		ResponseEntity<DifferentTypeReponses> reponseController = messageController.posterUnMessage(request);
		// assert
		Assertions.assertThat(reponseController).isEqualToComparingFieldByFieldRecursively(expected);

	}

	@Test
	public void devrais_poster_un_message_Sign_in() {
		// arrange
		Request request = creationRequeteSimple("connecte-moi à mon compte Google", "sign_in");
		DemandePermission demandePermission =creationReponseGoogleSignIn();
		ResponseEntity<DifferentTypeReponses> expected = ResponseEntity.status(HttpStatus.OK).body(demandePermission);
		//act
		ResponseEntity<DifferentTypeReponses> reponseController = messageController.posterUnMessage(request);
		// assert
		Assertions.assertThat(reponseController).isEqualToComparingFieldByFieldRecursively(expected);

	}
	
	
	//************************** quelques test avec les valeurs des questions de la plateforme *************************************** //
	
	//
	@Test
	public void devrais_renvoyer_le_nom_de_user() {
		//arrange
		Request request = creationRequeteSimple("quel est mon nom?", "conversation-intent");
		String displayName = "PAMELA ANOU";
		Reponse reponse = CreationReponseSimple("Vous vous appelez " + displayName);
		ResponseEntity<DifferentTypeReponses> expected = ResponseEntity.ok().body(reponse);
		
		//act
		messageController.setDisplayName(displayName);
		ResponseEntity<DifferentTypeReponses> reponseController = messageController.posterUnMessage(request);
		
		//Assert
		Assertions.assertThat(reponseController).isEqualToComparingFieldByFieldRecursively(expected);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//******************************** les methodes ********************************************************************************** //
	public Request creationRequeteSimple(final String queryText, final String action) {
		Request request = new Request();
		QueryResult queryResult = new QueryResult();
		queryResult.setQueryText(queryText);
		queryResult.setAllRequiredParamsPresent(true);
		queryResult.setLanguageCode("fr");
		if (action != null) {
			queryResult.setAction(action);
		}
		request.setQueryResult(queryResult);
		return request;
	}

	public Request creationRequeteUserInfo( final String queryText,  final String action,  final boolean boolValue,
			 final String textValue) {
		Request request = new Request();

		QueryResult queryResult = new QueryResult();
		queryResult.setQueryText(queryText);
		queryResult.setAllRequiredParamsPresent(true);
		queryResult.setLanguageCode("fr");
		queryResult.setAction(action);
		request.setQueryResult(queryResult);

		OriginalDetectIntentRequest originalDetectIntentRequest = new OriginalDetectIntentRequest();
		PayloadRequest payloadRequest = new PayloadRequest();
		User user = new User();
		Profile profile = new Profile();
		profile.setDisplayName("PAMELA ANOU");
		profile.setFamilyName("ANOU");
		profile.setGivenName("PAMELA");
		user.setProfile(profile);
		payloadRequest.setUser(user);
		Inputs input = new Inputs();

		Arguments argument = new Arguments();
		argument.setBoolValue(boolValue);
		argument.setTextValue(textValue);
		argument.setName("PERMISSION");
		List<Arguments> arguments = new ArrayList<>();
		arguments.add(argument);

		input.setArguments(arguments);
		List<Inputs> inputs = new ArrayList<>();
		inputs.add(input);

		payloadRequest.setInputs(inputs);

		Device device = new Device();
		Location location = new Location();
		location.setFormattedAddress(
				"Atrium, boulevard vivier merle, 107 109 Boulevard Marius Vivier Merle, 69003 Lyon, France");
		location.setCity("Lyon");
		device.setLocation(location);

		payloadRequest.setDevice(device);
		originalDetectIntentRequest.setPayload(payloadRequest);
		request.setOriginalDetectIntentRequest(originalDetectIntentRequest);

		return request;
	}
	
	public DemandePermission creationReponseGoogleSignIn() {
		GoogleDemandePermission googleDemandePermission = new GoogleDemandePermission();
		googleDemandePermission.setExpectUserResponse(true);

		Data data = new Data();
		SystemIntent systemIntent = new SystemIntent();
		systemIntent.setIntent("actions.intent.SIGN_IN");
		systemIntent.setData(data);

		googleDemandePermission.setSystemIntent(systemIntent);
		PayloadDemandePermission payloadDemandePermission = new PayloadDemandePermission();
		payloadDemandePermission.setGoogle(googleDemandePermission);

		DemandePermission demandePermission = new DemandePermission();
		demandePermission.setPayload(payloadDemandePermission);
		return demandePermission;
	}

	public Reponse CreationReponseSimple(final String messageReponse) {
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

		return reponse;
	}

	public DemandePermission demandePermission() {
		Data data = new Data();
		data.setType("type.googleapis.com/google.actions.v2.PermissionValueSpec");
		data.setOptContext("connaitre ta position");
		String[] permissions = { "NAME", "DEVICE_PRECISE_LOCATION" };
		data.setPermissions(permissions);

		SystemIntent systemIntent = new SystemIntent();
		systemIntent.setData(data);
		systemIntent.setIntent("actions.intent.PERMISSION");

		GoogleDemandePermission googleDemandePermission = new GoogleDemandePermission();
		googleDemandePermission.setExpectUserResponse(true);
		googleDemandePermission.setSystemIntent(systemIntent);

		PayloadDemandePermission payloadDemandePermission = new PayloadDemandePermission();
		payloadDemandePermission.setGoogle(googleDemandePermission);

		DemandePermission demandePermission = new DemandePermission();
		demandePermission.setPayload(payloadDemandePermission);
		return demandePermission;
	}

}
