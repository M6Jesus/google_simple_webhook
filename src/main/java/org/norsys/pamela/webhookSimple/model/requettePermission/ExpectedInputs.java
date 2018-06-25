package org.norsys.pamela.webhookSimple.model.requettePermission;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "inputPrompt", "possibleIntents" })
public class ExpectedInputs {
	
	@JsonProperty("inputPrompt")
	private InputPrompt inputPrompt;
	public InputPrompt getInputPrompt() {
		return inputPrompt;
	}
	public void setInputPrompt(InputPrompt inputPrompt) {
		this.inputPrompt = inputPrompt;
	}
	
	
	@JsonProperty("possibleIntents")
	private List<PossibleIntents> possibleIntents;
	public List<PossibleIntents> getPossibleIntents() {
		return possibleIntents;
	}
	public void setPossibleIntents(List<PossibleIntents> possibleIntents) {
		this.possibleIntents = possibleIntents;
	}
	
}
