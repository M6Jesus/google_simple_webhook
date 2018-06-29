package org.norsys.pamela.webhookSimple.model.requettePermission;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonPropertyOrder({ "state", "data" })
public class ConversationToken {
	
	@JsonProperty("state")
	private String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	@JsonProperty("data")
	private Data data;
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	
	
	
	
	@JsonCreator
	public ConversationToken(@JsonProperty("state") String state, @JsonProperty("data") Data data) {
		this.state = state;
		this.data = data;
	}

	


	
}
