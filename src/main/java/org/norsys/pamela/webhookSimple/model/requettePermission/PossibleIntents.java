package org.norsys.pamela.webhookSimple.model.requettePermission;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonPropertyOrder({"intent", "inputValueData"})
public class PossibleIntents {
	
	@JsonProperty("intent")
	private String intent;
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	
	
	@JsonProperty("inputValueData")
	private InputValueData inputValueData;
	public InputValueData getInputValueData() {
		return inputValueData;
	}
	public void setInputValueData(InputValueData inputValueData) {
		this.inputValueData = inputValueData;
	}
	
}
