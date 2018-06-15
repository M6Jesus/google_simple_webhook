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
	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	@JsonProperty("action")
	public String action;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	

	@JsonProperty("parameters")
	private Parameters parameters;
	public Parameters getParameters() {
		return parameters;
	}
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}
	
	@JsonProperty("allRequiredParamsPresent")
	private boolean allRequiredParamsPresent;
	public boolean isAllRequiredParamsPresent() {
		return allRequiredParamsPresent;
	}
	public void setAllRequiredParamsPresent(boolean allRequiredParamsPresent) {
		this.allRequiredParamsPresent = allRequiredParamsPresent;
	}
	
	
	@JsonProperty("fulfillmentText")
	private String fulfillmentText;
	public String getFulfillmentText() {
		return fulfillmentText;
	}
	public void setFulfillmentText(String fulfillmentText) {
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


	@JsonProperty("outputContexts")
	private OutputContexts outputContexts[];
	public OutputContexts[] getOutputContexts() {
		return outputContexts;
	}
	public void setOutputContexts(OutputContexts[] outputContexts) {
		this.outputContexts = outputContexts;
	}


	@JsonProperty("intent")
	private Intent intent;
	public Intent getIntent() {
		return intent;
	}
	public void setIntent(Intent intent) {
		this.intent = intent;
	}
	
	
	@JsonProperty("intentDetectionConfidence")
	private double intentDetectionConfidence;
	public double getIntentDetectionConfidence() {
		return intentDetectionConfidence;
	}
	public void setIntentDetectionConfidence(double intentDetectionConfidence) {
		this.intentDetectionConfidence = intentDetectionConfidence;
	}
	
	
	@JsonProperty("diagnosticInfo")
	private DiagnosticInfo diagnosticInfo;
	public DiagnosticInfo getDiagnosticInfo() {
		return diagnosticInfo;
	}
	public void setDiagnosticInfo(DiagnosticInfo diagnosticInfo) {
		this.diagnosticInfo = diagnosticInfo;
	}
	
	
	@JsonProperty("languageCode")
	private String languageCode;
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}








	
	
	

}
