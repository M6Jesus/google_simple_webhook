package org.norsys.pamela.webhookSimple.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "conversationId", "type", "conversationToken"})
public class Conversation {
	
	@JsonProperty("conversationId")
	private String conversationId;
	public String getConversationId() {
		return conversationId;
	}
	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}
	
	
	@JsonProperty("type")
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@JsonProperty("conversationToken")
	private String conversationToken;
	public String getConversationToken() {
		return conversationToken;
	}

	public void setConversationToken(String conversationToken) {
		this.conversationToken = conversationToken;
	}
	
	

}
