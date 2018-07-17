package org.norsys.pamela.webhookSimple.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.norsys.pamela.webhookSimple.action.WebhookAction;
import org.norsys.pamela.webhookSimple.event.ArriverMessage;
import org.norsys.pamela.webhookSimple.model.InterfaceReponses.DifferentTypeReponses;
import org.norsys.pamela.webhookSimple.model.demandePermission.Data;
import org.norsys.pamela.webhookSimple.model.demandePermission.DemandePermission;
import org.norsys.pamela.webhookSimple.model.demandePermission.GoogleDemandePermission;
import org.norsys.pamela.webhookSimple.model.demandePermission.PayloadDemandePermission;
import org.norsys.pamela.webhookSimple.model.demandePermission.SystemIntent;
import org.norsys.pamela.webhookSimple.model.request.Arguments;
import org.norsys.pamela.webhookSimple.model.request.Inputs;
import org.norsys.pamela.webhookSimple.model.request.Location;
import org.norsys.pamela.webhookSimple.model.request.OutputContexts;
import org.norsys.pamela.webhookSimple.model.request.PayloadRequest;
import org.norsys.pamela.webhookSimple.model.request.QueryResult;
import org.norsys.pamela.webhookSimple.model.request.Request;
import org.norsys.pamela.webhookSimple.model.response.Google;
import org.norsys.pamela.webhookSimple.model.response.Items;
import org.norsys.pamela.webhookSimple.model.response.OutputContextsReponse;
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
 * cette classe est le controlleur point d'entrée des données provenant de
 * dialogflow
 * 
 * @author panou
 *
 */
@RestController
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	String adresse;
	String displayName;
	String prenom;
	String codeSecret = "1234";
	
	String prenomSecurite;
	/**
	 * le publisher de l'evenement
	 */
	@Autowired
	public ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	public Map<String, WebhookAction> actions;

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
	 * @param request:
	 *            reprensente l'objet requete provenant de dialogflow
	 * @return ResponseEntity<DifferentTypeReponses> a la requette
	 */
	@PostMapping("/webhook")
	public ResponseEntity<DifferentTypeReponses> posterUnMessage(@RequestBody final Request request) {
		logger.debug("========message arriver a la webhook========================");
		QueryResult queryResult = request.getQueryResult();
		String valeurQuestion = queryResult.getQueryText();

		String action = queryResult.getAction();

		// je recupere l'action et je regarde si c'est elle contient l'action
		// request_permission de l'intent request_permission ou l'action user_info de
		// l'intent user_info
		if (StringUtils.isNotBlank(action)) {
			
			//*************************traitement des noms non reconnues
			//*********************************************************//
			if (action.equals("UserProvides_firstName")) {
				Reponse reponse = new Reponse();
				String nouveauContexte = RecupereEtRenvoieLeNouveauOutputContext(request, "awaiting_LastName");
				reponse = creationReponse("nous avons rencontrer un probleme interne. Merci de réessayer plus tard",
						null, true);
				String prenoms = valeurQuestion.toLowerCase();
				if (prenoms.equals("pamela")) {
					reponse = creationReponse("Merci. vous m'avez dit " + prenoms + ". Pourriez vous me donner votre nom s'il vous plait?", nouveauContexte, false);
					prenomSecurite = prenoms;
				}
				else {
					//***si le prenom n'est pas reconnu je le notifie et ferme la conversation ****///
					reponse = creationReponse("Desoler. vous m'avez dit " + prenoms + ". ce prénom n'est pas connu dans l'application", null, true);
				}
				return ResponseEntity.status(HttpStatus.OK).body(reponse);
			}
			
			
			if (action.equals("UserProvides_LastName")) {
				Reponse reponse = new Reponse();
				String nouveauContexte = RecupereEtRenvoieLeNouveauOutputContext(request, "awaiting_codeSecret");
				reponse = creationReponse("nous avons rencontrer un probleme interne. Merci de réessayer plus tard",
						null, true);
				String noms = valeurQuestion.toLowerCase();
				if (noms.equals("anou")) {
					reponse = creationReponse("vous m'avez dit " + noms + ". j'enregistre Ce nom. Merci de me donner le code secret associé à ce nom pour que j'établisse une connexion sécurisé", nouveauContexte, false);
					prenomSecurite = noms;
				}
				else {
					//***si le nom n'est pas reconnu, je le notifie et je ferme la connexion ****//
					reponse = creationReponse("Desoler. vous m'avez dit " + prenomSecurite + " "+ noms + ". ce prénom n'est pas connu dans l'application", null, true);
					
				}
				return ResponseEntity.status(HttpStatus.OK).body(reponse);
			}
			
			
			
			
			
			// ***********************traitement de la question de la securite
			// *********************************************//

			if (action.equals("UserProvides_secretCode")) {
				// PayloadRequest PayloadRequest =
				// request.getOriginalDetectIntentRequest().getPayload();
				// Inputs input = PayloadRequest.getInputs().get(0);
				// Arguments arguments = input.getArguments().get(0);
				Reponse reponse = new Reponse();
				// String codeRentrer = arguments.getTextValue();

				reponse = creationReponse("nous avons rencontrer un probleme interne. Merci de réessayer plus tard",
						null, false);
				if (valeurQuestion.equals(codeSecret)) {
					String nouveauContexte = RecupereEtRenvoieLeNouveauOutputContext(request, "connexion_oki");
					reponse = creationReponse(
							"Vous avez rentrer le bon code. la connexion viens d'être établi avec l'application ",
							nouveauContexte, false);
				} else {
					String nouveauContexte = RecupereEtRenvoieLeNouveauOutputContext(request, "awaiting_codeSecret2");
					reponse = creationReponse("vous avez dit " + valeurQuestion
							+ " ceci n'est malhereusement pas le code associé à ce compte. Veuillez me donner un nouveau code. il vous reste "
							+ "deux" + " tentatives", nouveauContexte, false);
				}
				return ResponseEntity.status(HttpStatus.OK).body(reponse);
			}

			if (action.equals("UserProvides_secretCode2")) {
				Reponse reponse = new Reponse();
				reponse = creationReponse("nous avons rencontrer un probleme interne. Merci de réessayer plus tard",
						null, false);
				if (valeurQuestion.equals(codeSecret)) {
					String nouveauContexte = RecupereEtRenvoieLeNouveauOutputContext(request, "connexion_oki");
					reponse = creationReponse(
							"Vous avez rentrer le bon code. la connexion viens d'être établi avec l'application ",
							nouveauContexte, false);
				} else {
					String nouveauContexte = RecupereEtRenvoieLeNouveauOutputContext(request, "endOf_conversation");
					reponse = creationReponse("vous avez dit " + valeurQuestion
							+ " ceci n'est malhereusement pas le code associé à ce compte. Veuillez me donner un nouveau code. il vous reste "
							+ "une" + " tentative", nouveauContexte, false);
				}
				return ResponseEntity.status(HttpStatus.OK).body(reponse);
			}
			if (action.equals("EndOf_conversation")) {
				Reponse reponse = new Reponse();
				reponse = creationReponse("nous avons rencontrer un probleme interne. Merci de réessayer plus tard",
						null, false);
				if (valeurQuestion.equals(codeSecret)) {
					String nouveauContexte = RecupereEtRenvoieLeNouveauOutputContext(request, "connexion_oki");
					reponse = creationReponse(
							"Vous avez rentrer le bon code. la connexion viens d'être établi avec l'application ",
							nouveauContexte, false);
				} else {
					reponse = creationReponse(
							"Désoler vous n'avez pas donner le bon code. vous n'êtes pas autoriser à vous connecter. Aurevoir!",
							null, false);
				}
				return ResponseEntity.status(HttpStatus.OK).body(reponse);
			}
			

			// traitement de la demande de permission pour acces aux données personnelles
			// **********************************************//

			if (action.equals("request_permission")) {
				// ici je dois envoyer une reponse equivalente a une requestPermission pour que
				// action on Google active mon intent user_info qui contient l'evenement
				// actions_intent_PERMISSION et demande
				// tout seul a l'utilisateur la permission d'acceder oui ou non a ses données
				// personnelles

				DemandePermission demandePermission = demandePermission();
				return ResponseEntity.status(HttpStatus.OK).body(demandePermission);

			}

			if (action.equals("user_info")) {
				// ici la requete a des information en plus sur l'utilisateur en fonction de sa
				// reponse, si elle est oui ou non
				PayloadRequest payload = request.getOriginalDetectIntentRequest().getPayload();
				Inputs input = payload.getInputs().get(0);
				Arguments arguments = input.getArguments().get(0);

				if (arguments.getTextValue().equals("true") && arguments.isBoolValue()) {
					// ici l'utilisateur a dit oui, je dois retourner son adresse
					Location location = payload.getDevice().getLocation();
					displayName = payload.getUser().getProfile().getDisplayName();
					prenom = payload.getUser().getProfile().getGivenName();
					adresse = location.getFormattedAddress();
					String ville = location.getCity();
					// je retourne un objet reponse avec l'adresse
					Reponse reponse = creationReponse("Vous vous trouvez actuellement au " + adresse
							+ " votre ville est " + ville + " et votre nom est  " + displayName, null, false);
					return ResponseEntity.status(HttpStatus.OK).body(reponse);
				} else if (arguments.getTextValue().equals("false")) {
					Reponse reponse = creationReponse(
							"Vous ne m'avez pas donner de permission pour acceder à vos informations personnelles",
							null, false);
					return ResponseEntity.status(HttpStatus.OK).body(reponse);
				}
			}

			// ***************** pour l'action SIGN IN
			// ******************************************************//

			if (action.equals("sign_in")) {
				// je retourne une reponse pour activer l'intent qui contient l'event
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
				return ResponseEntity.status(HttpStatus.OK).body(demandePermission);
			}
			
			
			//*** ici je recupere le nom et le prenom de l'utilisateur en fonction de son accord ***//
			

			if (action.equals("conversation-intent")) {
				
				// retourner le nom de l'utilisateur s'il le demande
				if (valeurQuestion.contains("nom") || valeurQuestion.contains("prénom")
						|| valeurQuestion.contains("m’appelle")) {
					//si le nom et le prenom sont deja enregistrer
					if (displayName != null) {
						Reponse reponse = creationReponse("Vous vous appelez " + displayName, null, false);
						if (valeurQuestion.contains("prénom")) {
							reponse = creationReponse("Votre prénom est " + prenom, null, false);
							return ResponseEntity.status(HttpStatus.OK).body(reponse);
						}
						return ResponseEntity.status(HttpStatus.OK).body(reponse);
					} else {
						// si le nom et prenom n'est pas encore enregistrer, je fais la demande de permission pour reuperer 
						DemandePermission demandePermission = demandePermission();
						return ResponseEntity.status(HttpStatus.OK).body(demandePermission);
					}

				}

				// *************************** requettes effectives a la base de donnée
				// *****************************************//

				// message de fin au cas ou je recois "merci google"
				if (valeurQuestion.contains("merci")) {
					Reponse reponse = creationReponse("je t'en prie pamela. Qu'est ce que je peux faire pour toi",
							null, false);
					return ResponseEntity.status(HttpStatus.OK).body(reponse);

				} else {
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
						Reponse reponse = creationReponse(reponseApiString, null, false);
						return ResponseEntity.status(HttpStatus.OK).body(reponse);
					} else {
						Reponse reponse = creationReponse("desoler je n'ai pas cette information", null, false);

						return ResponseEntity.status(HttpStatus.OK).body(reponse);
					}
				}
			}
		}
		Reponse reponse = creationReponse("nous n'avons pas d'action de ce genre", null, false);
		return ResponseEntity.status(HttpStatus.OK).body(reponse);
	}

// ************************************** methodes utilisées plus haut pour encapsulation******************************************//
	
	/**
	 * 
	 * @param messageReponse: la reponse audible a retourner à l'utilisateur
	 * @param outputContext: la valeur du parametre "name" de l'outputContext
	 * @param isEndOfConversation: booleen qui permet de creer une reponse qui est une fin de conversation
	 * @return un objet reponse pour la plateforme dialogflow
	 */
	private Reponse creationReponse(final String messageReponse, final String outputContext, final boolean isEndOfConversation) {
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
		if(isEndOfConversation == true) {
			//** dans le cas ou je souhaite fermer la conversation***//
			google.setExpectUserResponse(false);
		}
		google.setRichResponse(richResponse);
		Payload payload = new Payload();
		payload.setGoogle(google);
		reponse.setPayload(payload);

		org.norsys.pamela.webhookSimple.model.response.FulfillmentMessages fulfillmentMessages = new org.norsys.pamela.webhookSimple.model.response.FulfillmentMessages();

		org.norsys.pamela.webhookSimple.model.response.Text texte = new org.norsys.pamela.webhookSimple.model.response.Text();

		List<String> listess = new ArrayList<>();
		listess.add(messageReponse);
		texte.setText(listess);
		fulfillmentMessages.setText(texte);

		List<org.norsys.pamela.webhookSimple.model.response.FulfillmentMessages> listes = new ArrayList<>();
		listes.add(fulfillmentMessages);
		reponse.setFulfillmentMessages(listes);
		if (outputContext != null) {
			OutputContextsReponse outputCont = new OutputContextsReponse();
			outputCont.setLifespanCount(1);
			if(outputContext.contains("connexion_oki")) {
				outputCont.setLifespanCount(5);
			}
			outputCont.setName(outputContext);
			List<OutputContextsReponse> leOutput = new ArrayList<>();
			leOutput.add(outputCont);
			reponse.setOutputContexts(leOutput);
		}
		return reponse;
	}
	
	
	/**
	 * 
	 * @param request un objet requete provenant de la plateforme dialogflow
	 * @param nouveauOUtPutContext le nom de l'ouputContexte vers lequel je veux faire pointé mon intent
	 * @return la valeur du parametre "name" dans l'objet reponse
	 */
	public String RecupereEtRenvoieLeNouveauOutputContext(final Request request, final String nouveauOUtPutContext) {
		OutputContexts outputContexts = request.getQueryResult().getOutputContexts().get(0);
		// ici je recupere le nom de l'ouputcontext avec toutes données de projetId,
		// session... ce que je veux c'est changer la fin de cette String en mettant le
		// nouveau contexte souhaiter
		String name = outputContexts.getName();
		// enlever le contexte present
		int indexDernierSlash = name.lastIndexOf('/');
		String chaineSansContexte = name.substring(0, indexDernierSlash + 1);
		// je concatene le nouveau contexte
		String nouveauName = chaineSansContexte + nouveauOUtPutContext;
		return nouveauName;
	}
	
	/**
	 * 
	 * @return un objet reponse qui est une demande de permission
	 * a l'utilisateur pour l'utilisation de ses données personelles
	 */
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
