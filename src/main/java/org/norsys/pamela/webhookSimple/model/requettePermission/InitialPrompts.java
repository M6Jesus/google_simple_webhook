package org.norsys.pamela.webhookSimple.model.requettePermission;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "textToSpeech"})
public class InitialPrompts {
	
	@JsonProperty("textToSpeech")
	private String textToSpeech;
	public String getTextToSpeech() {
		return textToSpeech;
	}
	public void setTextToSpeech(String textToSpeech) {
		this.textToSpeech = textToSpeech;
	}
	
}
