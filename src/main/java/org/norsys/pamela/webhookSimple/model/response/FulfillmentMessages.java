package org.norsys.pamela.webhookSimple.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "text" })
public class FulfillmentMessages {
	
	@JsonProperty("text")
	private Text text;
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	

}
