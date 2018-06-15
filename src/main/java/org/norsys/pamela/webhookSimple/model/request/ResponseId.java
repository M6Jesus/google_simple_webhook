package org.norsys.pamela.webhookSimple.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * cette classe represente l'objet responseId d'une requête
 * @author panou
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "responseId" })
public class ResponseId {
	
	@JsonProperty("responseId")
	private String responseId;
	public String getResponseId() {
		return responseId;
	}	
	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	
	public ResponseId(String responseId) {
		this.responseId = responseId;
	}

	
	
	

}
