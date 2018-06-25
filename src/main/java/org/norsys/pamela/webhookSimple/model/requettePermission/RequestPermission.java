package org.norsys.pamela.webhookSimple.model.requettePermission;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "conversationToken", "expectUserResponse", "expectedInputs" })
public class RequestPermission {
	
	
	@JsonProperty("conversationToken")
	private ConversationToken conversationToken;
	public ConversationToken getConversationToken() {
		return conversationToken;
	}
	public void setConversationToken(ConversationToken conversationToken) {
		this.conversationToken = conversationToken;
	}

	
	@JsonProperty("expectUserResponse")
	private boolean expectUserResponse;
	public boolean isExpectUserResponse() {
		return expectUserResponse;
	}
	public void setExpectUserResponse(boolean expectUserResponse) {
		this.expectUserResponse = expectUserResponse;
	}

	
	@JsonProperty("expectedInputs")
	private List<ExpectedInputs> expectedInputs;
	public List<ExpectedInputs> getExpectedInputs() {
		return expectedInputs;
	}
	public void setExpectedInputs(List<ExpectedInputs> expectedInputs) {
		this.expectedInputs = expectedInputs;
	}

}
