package org.norsys.pamela.webhookSimple.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "rawText", "textValue", "name", "boolValue"})
public class Arguments {
	

	@JsonProperty("rawText")
	private String rawText;
	public String getRawText() {
		return rawText;
	}
	public void setRawText(String rawText) {
		this.rawText = rawText;
	}


	@JsonProperty("textValue")
	private String textValue;
	public String getTextValue() {
		return textValue;
	}
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}


	@JsonProperty("name")
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("boolValue")
	private boolean boolValue;
	public boolean isBoolValue() {
		return boolValue;
	}
	public void setBoolValue(boolean boolValue) {
		this.boolValue = boolValue;
	}
	
	
}
