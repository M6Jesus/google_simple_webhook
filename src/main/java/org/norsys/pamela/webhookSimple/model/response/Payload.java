package org.norsys.pamela.webhookSimple.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * cette classe represente l'objet Payload d'une reponse
 * @author panou
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"google" })
public class Payload {
	
	@JsonProperty("google")
	private Google google;
	public Google getGoogle() {
		return google;
	}
	public void setGoogle(Google google) {
		this.google = google;
	}
	
	

}
