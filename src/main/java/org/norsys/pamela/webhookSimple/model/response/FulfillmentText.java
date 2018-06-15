package org.norsys.pamela.webhookSimple.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * cette classe represente l'objet fulfillmenttext d'une reponse
 * @author panou
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "fulfillmentText" })
public class FulfillmentText {
	
	@JsonProperty("fulfillmentText")
	private String fulfillmentText;
	public String getFulfillmentText() {
		return fulfillmentText;
	}
	public void setFulfillmentText(String fulfillmentText) {
		this.fulfillmentText = fulfillmentText;
	}
	

}
