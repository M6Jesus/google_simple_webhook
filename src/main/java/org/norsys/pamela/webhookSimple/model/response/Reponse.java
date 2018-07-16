package org.norsys.pamela.webhookSimple.model.response;

import java.util.List;

import org.norsys.pamela.webhookSimple.model.InterfaceReponses.DifferentTypeReponses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * cette classe represente l'objet Reponse
 * @author panou
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "fulfillmentText", "fulfillmentMessages", "source", "payload", "outputContexts", "followupEventInput" })
public class Reponse implements DifferentTypeReponses{
	
	@JsonProperty("fulfillmentText")
	private FulfillmentText fulfillmentText;
	public FulfillmentText getFulfillmentText() {
		return fulfillmentText;
	}
	public void setFulfillmentText(FulfillmentText fulfillmentText) {
		this.fulfillmentText = fulfillmentText;
	}
	
	
	@JsonProperty("fulfillmentMessages")
	private List<FulfillmentMessages> fulfillmentMessages;
	public List<FulfillmentMessages> getFulfillmentMessages() {
		return fulfillmentMessages;
	}
	public void setFulfillmentMessages(List<FulfillmentMessages> fulfillmentMessages) {
		this.fulfillmentMessages = fulfillmentMessages;
	}
	
	
	
	@JsonProperty("source")
	private String source;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
	@JsonProperty("payload")
	private Payload payload;
	public Payload getPayload() {
		return payload;
	}
	public void setPayload(Payload payload) {
		this.payload = payload;
	}
	
	
	@JsonProperty("outputContexts")
	private List<OutputContextsReponse> outputContextsReponse;
	public List<OutputContextsReponse> getOutputContexts() {
		return outputContextsReponse;
	}
	public void setOutputContexts(List<OutputContextsReponse> outputContextsReponse) {
		this.outputContextsReponse = outputContextsReponse;
	}
	
	
	@JsonProperty("followupEventInput")
	private FollowupEventInput followupEventInput;
	public FollowupEventInput getFollowupEventInput() {
		return followupEventInput;
	}
	public void setFollowupEventInput(FollowupEventInput followupEventInput) {
		this.followupEventInput = followupEventInput;
	}
	
	






	
	
	
	
	
	
	
}
