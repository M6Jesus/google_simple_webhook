package org.norsys.pamela.webhookSimple.model.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * cette classe reprensente l'objet queryResult d'une requête
 * @author panou
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "queryText", "action", "parameters", "allRequiredParamsPresent", "fulfillmentText", "fulfillmentMessages", "outputContexts", 
	"intent", "intentDetectionConfidence", "diagnosticInfo",   "languageCode", "originalDetectIntentRequest"})
public class QueryResult {
	
	@JsonProperty("queryText")
	private String queryText;
	public String getQueryText() {
		return queryText;
	}
	public void setQueryText(final String queryText) {
		this.queryText = queryText;
	}

	@JsonProperty("action")
	public String action;
	public String getAction() {
		return action;
	}
	public void setAction(final String action) {
		this.action = action;
	}
	

	@JsonProperty("parameters")
	private Parameters parameters;
	public Parameters getParameters() {
		return parameters;
	}
	public void setParameters(final Parameters parameters) {
		this.parameters = parameters;
	}
	
	@JsonProperty("allRequiredParamsPresent")
	private boolean allRequiredParamsPresent;
	public boolean isAllRequiredParamsPresent() {
		return allRequiredParamsPresent;
	}
	public void setAllRequiredParamsPresent(final boolean allRequiredParamsPresent) {
		this.allRequiredParamsPresent = allRequiredParamsPresent;
	}
	
	
	@JsonProperty("fulfillmentText")
	private String fulfillmentText;
	public String getFulfillmentText() {
		return fulfillmentText;
	}
	public void setFulfillmentText(final String fulfillmentText) {
		this.fulfillmentText = fulfillmentText;
	}
	
	
	@JsonProperty("fulfillmentMessages")
	private List<FulfillmentMessages> fulfillmentMessages;
	public List<FulfillmentMessages> getFulfillmentMessages() {
		return fulfillmentMessages;
	}
	public void setFulfillmentMessages(final List<FulfillmentMessages> fulfillmentMessages) {
		this.fulfillmentMessages = fulfillmentMessages;
	}


	@JsonProperty("outputContexts")
	private List<OutputContexts> outputContexts;
	public List<OutputContexts> getOutputContexts() {
		return outputContexts;
	}
	public void setOutputContexts(final List<OutputContexts> outputContexts) {
		this.outputContexts = outputContexts;
	}

	@JsonProperty("intent")
	private Intent intent;
	public Intent getIntent() {
		return intent;
	}
	public void setIntent(final Intent intent) {
		this.intent = intent;
	}
	
	
	@JsonProperty("intentDetectionConfidence")
	private double intentDetectionConfidence;
	public double getIntentDetectionConfidence() {
		return intentDetectionConfidence;
	}
	public void setIntentDetectionConfidence(final double intentDetectionConfidence) {
		this.intentDetectionConfidence = intentDetectionConfidence;
	}
	
	
	@JsonProperty("diagnosticInfo")
	private DiagnosticInfo diagnosticInfo;
	public DiagnosticInfo getDiagnosticInfo() {
		return diagnosticInfo;
	}
	public void setDiagnosticInfo(final DiagnosticInfo diagnosticInfo) {
		this.diagnosticInfo = diagnosticInfo;
	}
	
	
	@JsonProperty("languageCode")
	private String languageCode;
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(final String languageCode) {
		this.languageCode = languageCode;
	}








	
	
	

}
