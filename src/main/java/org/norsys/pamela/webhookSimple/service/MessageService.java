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
	
	//CHAMP REQUETE POST SUR ACTION ON GOOGLE
	
	/**
	 * id unique pour la requete
	 */
	private String responseId;
	/**
	 * l'unique id de la session
	 */
	private String session;
	
/*
 * 
 * queryResult: object qui est le resultat de la requete ou du process de l'event sur action on google, il comprends tous les params suivant
*/
//============================QUERYRESULT==================================================
	/**
	 * le texte original de la requete
	 */
	private String queryText;
	
	/**
	 * parameters: object --Consists of parameter_name:parameter_value pairs.
	 */
	
	/**
	 * Ce parametre est mis a faux si tous les parametres ne sont pas present
	 */
	private boolean allRequiredParamsPresent;
	
	/**
	 *le texte a prononcer a l'utilisateur ou a afficher sur l'ecran 
	 */
	private String fulfillmentText;
	
	/**
	 * fulfillmentMessages : object -- Une collection de "riche message" a montrer a l'utilisateur exple 
	 * "messages": [
    	{
      		object(Message)
    	}
  		],
	 */
	
	
	/**
	 * outputContexts: object -- une collection de outputs contexte
	 * example : 
	 * {
  			"name": string,
  			"lifespanCount": number,
  			"parameters": {
    			object
  			}
		}
		voir la documentation 
	 */
	
	/**
	 * intent : object -- reprensente l'intent qui correpond a la requete de l'utilisateur
	 * example
	 * 
	 * {
		  "--name": string --requis pour toutes les methides sauf "create" Format: projects/<Project ID>/agent/intents/<Intent ID>. ,
		  "--displayName": string -- le nom de l'intent,
		  "--webhookState": enum(WebhookState),
		  "priority": number,
		  "isFallback": boolean,
		  "mlEnabled": boolean,
		  "mlDisabled": boolean,
		  "inputContextNames": [
		    string
		  ],
		  "events": [
		    string
		  ],
		  "trainingPhrases": [
		    {
		      object(TrainingPhrase)
		    }
		  ],
		  "action": string,
		  "outputContexts": [
		    {
		      object(Context)
		    }
		  ],
		  "resetContexts": boolean,
		  "parameters": [
		    {
		      object(Parameter)
		    }
		  ],
		  "messages": [
		    {
		      object(Message)
		    }
		  ],
		  "defaultResponsePlatforms": [
		    enum(Platform)
		  ],
		  "--rootFollowupIntentName": string-- ce sont des id voir doc j'i pas bien compris,
		  "--parentFollowupIntentName": string --ce sont des id voir la doc, j'ai pas bien compris,
		  "followupIntentInfo": [
		    {
		      object(FollowupIntentInfo)
		    }
		    
		    tous les champs ne sont pas required
  		  ]
	 */
	
	/**
	 * le matching score de l'intent, entre 0-1
	 */
	private double intentDetectionConfidence;
	
	/**
	 * diagnosticInfo : object -- free form diagnostic info
	 */
	
	
	/**
	 * le langage qui a ete declencher durant le matching de l'intent
	 */
	private String languageCode;
//===================================fin du query result==================================	
	/**
	 * originalDetectIntentRequest : object -- full request venant d'une plateforme integrer (facebook messenger, slac, ..)
	 */
	
	
	/**
	 * EXAMPLE DE REPONSE PAR LE SERVICE
	 * 
	 * POST https://my-service.com/action

Headers:
//user defined headers
Content-type: application/json

POST body:
{
  "responseId": "ea3d77e8-ae27-41a4-9e1d-174bd461b68c",
  "session": "projects/your-agents-project-id/agent/sessions/88d13aa8-2999-4f71-b233-39cbf3a824a0",
  "queryResult": {
    "queryText": "user's original query to your agent",
    "parameters": {
      "param": "param value"
    },
    "allRequiredParamsPresent": true,
    "fulfillmentText": "Text defined in Dialogflow's console for the intent that was matched",
    "fulfillmentMessages": [
      {
        "text": {
          "text": [
            "Text defined in Dialogflow's console for the intent that was matched"
          ]
        }
      }
    ],
    "outputContexts": [
      {
        "name": "projects/your-agents-project-id/agent/sessions/88d13aa8-2999-4f71-b233-39cbf3a824a0/contexts/generic",
        "lifespanCount": 5,
        "parameters": {
          "param": "param value"
        }
      }
    ],
    "intent": {
      "name": "projects/your-agents-project-id/agent/intents/29bcd7f8-f717-4261-a8fd-2d3e451b8af8",
      "displayName": "Matched Intent Name"
    },
    "intentDetectionConfidence": 1,
    "diagnosticInfo": {},
    "languageCode": "en"
  },
  "originalDetectIntentRequest": {}
}

	 */
	
	
	//LA REPONSE --enlever les A devant les vaiables pour etre conforme au variables de action on google--
	/**
	 * Optional. The text to be shown on the screen. This value is passed directly to --QueryResult.fulfillment_text.--
	 */
	private String fulfillmentTextA;
	

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
