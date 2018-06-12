package org.norsys.pamela.webhookSimple.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"textToSpeech", "displayText" })
public class SimpleResponse {
	
	@JsonProperty("textToSpeech")
	private String textToSpeech;
	public String getTextToSpeech() {
		return textToSpeech;
	}
	public void setTextToSpeech(String textToSpeech) {
		this.textToSpeech = textToSpeech;
	}
	
	@JsonProperty("displayText")
	private String displayText;
	public String getDisplayText() {
		return displayText;
	}
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	

}
