package org.norsys.pamela.webhookSimple.model.requettePermission;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
