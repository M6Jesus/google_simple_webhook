package org.norsys.pamela.webhookSimple.model.reponsePermission;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "intent", "rawInputs", "arguments" })
public class Inputs {
	
	@JsonProperty("intent")
	private String intent;
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	
	
	@JsonProperty("rawInputs")
	private List<RawInputs> rawInputs;
	public List<RawInputs> getRawInputs() {
		return rawInputs;
	}
	public void setRawInputs(List<RawInputs> rawInputs) {
		this.rawInputs = rawInputs;
	}
	
	
	@JsonProperty("arguments")
	private List<Arguments> arguments;
	public List<Arguments> getArguments() {
		return arguments;
	}
	public void setArguments(List<Arguments> arguments) {
		this.arguments = arguments;
	}
	
	
}
