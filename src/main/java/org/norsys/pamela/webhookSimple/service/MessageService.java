package org.norsys.pamela.webhookSimple.service;

import org.norsys.pamela.webhookSimple.event.ArriverMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MessageService {

	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

	private RestTemplate restTemplate;

	private String urlApi;

	@Value("${resource.tousLesMedecins}")
	private String tousLesMedecins;

	@Value("${resource.estDisponible}")
	private String estDisponible;

	@Value("${resource.specialite}")
	private String specialite;

	@Value("${resource.hopital}")
	private String hopital;

	@Value("${resource.nomParSpecialite}")
	private String nomParSpecialite;

	@Value("${resource.nomParHopital}")
	private String nomParHopital;

	@Value("${resource.nomParDisponibilite}")
	private String nomParDisponibilite;

	@Value("${resource.tousLesVacins}")
	private String tousLesVacins;

	@Value("${resource.estObligatoire}")
	private String estObligatoire;

	@Value("${resource.vaccinParPays}")
	private String vaccinParPays;
	
	/**
	 * Construction du restTemplate
	 * @param restTemplateBuilder
	 */
	@Autowired
	public MessageService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@EventListener
	public void messageRecuEventListener(ArriverMessage messageRecuEvent) {
		String messageGoogle = messageRecuEvent.getMessage();

		logger.debug("=======le message de google {} est en train d'etre ecouter dans le service",
				messageGoogle + "=============");
		// envoie du message a la destination
		// envoyerMessageALaDestination(messageRecuEvent.getMessage(), urlApi);

	}
	/**
	 * 
	 * @param messageRecuEvent : le message de l'utilisateur sur dialogflow
	 * @return une reponse
	 */
	public ResponseEntity<String> envoiMessage(ArriverMessage messageRecuEvent) {
		String messageGoogle = messageRecuEvent.getMessage();

		logger.debug("=======le message de google {} est en train d'etre ecouter dans le service",
				messageGoogle + "=============");
		this.urlApi = null;
		
		String question = messageRecuEvent.getMessage().toLowerCase();
		if (question.contains("medecin")) {
			if (question.contains("tous") && !isSpecialityPresent(question) && !isHopitalNamePresent(question)) {
				this.urlApi = tousLesMedecins;
			}
			else if(isSpecialityPresent(question)) {
				String specialite = findSpecialityName(question);
				this.urlApi = nomParSpecialite + "?specialite="+ specialite ;
			}
			else if(isHopitalNamePresent(question)) {
				String hopital = findHopitalName(question);
				this.urlApi = nomParHopital + "?hopital=" + hopital;
			}
			else if(question.contains("disponible")) {
				this.urlApi = nomParDisponibilite;
			}
		}else if(isDoctorNamePresent(question)) {
			String NomMedecin = findDoctorName(question);
			if(question.contains("disponible")) {
				this.urlApi = estDisponible + "?nomMedecin=" + NomMedecin;
			}
			else if(question.contains("specialite")) {
				this.urlApi = specialite + "?nomMedecin=" + NomMedecin;
			}
			else if(question.contains("hopital") || question.contains("centre") || question.contains("clinique")) {
				this.urlApi = hopital + "?nomMedecin=" + NomMedecin;
			}
		}
		else if(question.contains("obligatoire") && isVaccinNamePresent(question)) {
				String nomVaccin = findVaccinName(question);
				this.urlApi = estObligatoire + "?nomVaccin=" + nomVaccin;
			
		}else if(question.contains("vaccin")) {
			if( (question.contains("tous") || question.contains("liste") || question.contains("different") ) && (!isCountryPresent(question)) && (!question.contains("obligatoire")) ){
				this.urlApi = tousLesVacins;
			}
			else if(isCountryPresent(question)) {
				String pays = findCountryName(question);
				this.urlApi = vaccinParPays + "?Pays=" + pays;
			}
		}
		// envoie du message a la destination
		return envoyerMessageALaDestination(messageRecuEvent.getMessage(), urlApi);

	}
	/**
	 * 
	 * @param message : le message a envoyer
	 * @param url : l'adresse url
	 * @return a reponse de l'api
	 */
	private ResponseEntity<String> envoyerMessageALaDestination(String message, String url) {
		logger.debug("debut de l'envoi du message pour la destination {}", url);
		ResponseEntity<String> reponse = null;
		try {
			if (url != null) {
				reponse = restTemplate.getForEntity(url, String.class);
			} else {
				reponse = null;
			}

			logger.info("réponse de l'api : {}", reponse.getBody());

		} catch (Exception ex) {
			logger.info("L'envoie du message a la destination a rencontrer une exeption : {} a l'url : {}", ex.getMessage(), url);
		}
		return reponse;
	}

	
	//============================================= LES METHODES =======================================================================
	
	
	/**
	 * 
	 * @param question: la question de l'utilisateur
	 * @return true ou false si un nom de docteur est present
	 */
	private boolean isDoctorNamePresent(String question) {
		if (question.contains("professeur dupuis") || question.contains("docteur anne eyouk")
				|| question.contains("docteur dupont") || question.contains("docteur grace")
				|| question.contains("docteur oben") || question.contains("docteur ansen")
				|| question.contains("docteur chance") || question.contains("professeur soigne")
				|| question.contains("docteur legrand") || question.contains("professeur lefin")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param question: la question de l'utilisateur
	 * @return true ou false si un nom de vaccin est present dans la question
	 */
	private boolean isVaccinNamePresent(String question) {
		if (question.contains("bcg tuberculose") || question.contains("tétanos") || question.contains("hépatite B")
				|| question.contains("diphtérie") || question.contains("poliomyélite")
				|| question.contains("haemophilus influenzae b") || question.contains("coqueluche")
				|| question.contains("fièvre jaune") || question.contains("gastro-entérite à rotavirus")
				|| question.contains("grippe saisonnière") || question.contains("hépatite A")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param question: la question de l'utilisateur
	 * @return true ou false si un nom de specialite est present dans la question
	 */
	private boolean isSpecialityPresent(String question) {
		if (question.contains("gynécologue") || question.contains("generaliste") || question.contains("ophtalmologue")
				|| question.contains("nutritioniste") || question.contains("gastro-enterologue")
				|| question.contains("dentiste") || question.contains("chirurgien opthtalmologue")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param question : la question de l'utilisateur
	 * @return true ou false si un nom d'hopital est present dans la question
	 */
	private boolean isHopitalNamePresent(String question) {
		if (question.contains("hopital lyon-sud") || question.contains("hopital edouard heriot")
				|| question.contains("clinique natecia") || question.contains("hopital gratte ciel")
				|| question.contains("hopital mere et enfant")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param question: la question de l'utilisateur
	 * @return true ou false si un nom de pays est present dans la question
	 */
	private boolean isCountryPresent(String question) {
		if (question.contains("cameroun") || question.contains("france") || question.contains("espagne")
				|| question.contains("italie") || question.contains("gabon") || question.contains("chine")
				|| question.contains("japon") || question.contains("congo")) {
			return true;
		}
		return false;
	}

	
	/**
	 * 
	 * @param question: la question de l'utilisateur
	 * @return le nom du docteur present dans la question
	 */
	private String findDoctorName(String question) {
		String retour = null;
		if (question.contains("professeur dupuis")) {
			retour = "professeur dupuis";
		} else if (question.contains("docteur anne eyouk")) {
			retour = "Docteur anne eyouk";
		} else if (question.contains("docteur dupont")) {
			retour = "Docteur dupont";
		} else if (question.contains("docteur grace")) {
			retour = "Docteur grace";
		} else if (question.contains("docteur oben")) {
			retour = "Docteur OBEN";
		} else if (question.contains("docteur ansen")) {
			retour = "Docteur ansen";
		} else if (question.contains("docteur chance")) {
			retour = "Docteur chance";
		} else if (question.contains("professeur soigne")) {
			retour = "professeur soigne";
		} else if (question.contains("docteur legrand")) {
			retour = "Docteur legrand";
		} else if (question.contains("professeur lefin")) {
			retour = ("professeur lefin");
		}
		return retour;
	}
	
	/**
	 * 
	 * @param question: la question de l'utilisateur
	 * @return le nom du vaccin present dans la question
	 */
	private String findVaccinName(String question) {
		String retour = null;
		if (question.contains("bcg tuberculose")) {
			retour = "BCG tuberculose";
		} else if (question.contains("tétanos")) {
			retour = "Tétanos";
		} else if (question.contains("hépatite B")) {
			retour = "Hépatite B";
		} else if (question.contains("diphtérie")) {
			retour = "Diphtérie";
		} else if (question.contains("poliomyélite")) {
			retour = "Poliomyélite";
		} else if (question.contains("haemophilus influenzae b")) {
			retour = "Haemophilus influenzae b";
		} else if (question.contains("coqueluche")) {
			retour = "Coqueluche";
		} else if (question.contains("fièvre jaune")) {
			retour = "Fièvre jaune";
		} else if (question.contains("gastro-entérite à rotavirus")) {
			retour = "Gastro-entérite à rotavirus";
		} else if (question.contains("hépatite A")) {
			retour = ("Hépatite A");
		} else if (question.contains("grippe saisonnière")) {
			retour = ("Grippe saisonnière");
		}
		return retour;
	}
	
	/**
	 * 
	 * @param question: la question de l'utilisateur
	 * @return le nom de l'hopital present dans la question
	 */
	private String findHopitalName(String question) {
		String retour = null;
		if (question.contains("hopital lyon-sud")) {
			retour = "hopital lyon-sud";
		} else if (question.contains("hopital edouard heriot")) {
			retour = "hopital edouard heriot";
		} else if (question.contains("hopital gratte ciel")) {
			retour = "hopital gratte ciel";
		} else if (question.contains("clinique natecia")) {
			retour = "Clinique natecia";
		} else if (question.contains("hopital mere et enfant")) {
			retour = "hopital mere et enfant";
		}
		return retour;
	}
	
	/**
	 * 
	 * @param question: la question de l'utilisateur
	 * @return le nom de la specialite present dans la question
	 */
	private String findSpecialityName(String question) {
		String retour = null;
		if (question.contains("gynécologue")) {
			retour = "gynécologue";
		} else if (question.contains("generaliste")) {
			retour = "generaliste";
		} else if (question.contains("ophtalmologue")) {
			retour = "ophtalmologue";
		} else if (question.contains("nutritioniste")) {
			retour = "nutritioniste";
		} else if (question.contains("dentiste")) {
			retour = "dentiste";
		} else if (question.contains("gastro-enterologue")) {
			retour = "gastro-enterologue";
		} else if (question.contains("chirurgien opthtalmologue")) {
			retour = "chirurgien opthtalmologue";
		}
		return retour;

	}
	
	/**
	 * 
	 * @param question: la question de l'utilisateur
	 * @return le nom dy pays present dans la question
	 */
	private String findCountryName(String question) {
		String retour = null;
		if (question.contains("cameroun")) {
			retour = "cameroun";
		} else if (question.contains("france")) {
			retour = "france";
		} else if (question.contains("espagne")) {
			retour = "espagne";
		} else if (question.contains("italie")) {
			retour = "italie";
		} else if (question.contains("gabon")) {
			retour = "gabon";
		} else if (question.contains("chine")) {
			retour = "chine";
		} else if (question.contains("japon")) {
			retour = "japon";
		} else if (question.contains("congo")) {
			retour = "congo";
		}
		return retour;
	}

}
