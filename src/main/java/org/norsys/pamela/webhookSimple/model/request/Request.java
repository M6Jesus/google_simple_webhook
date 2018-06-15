package org.norsys.pamela.webhookSimple.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Cette classe est une representation de l'objet requête 
 * @author panou
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "responseid", "session", "queryResult", "originalDetectIntentRequest"})
public class Request {
	
	@JsonProperty("responseid")
	private ResponseId responseid;
	public ResponseId getResponseid() {
		return responseid;
	}
	public void setResponseid(ResponseId responseid) {
		this.responseid = responseid;
	}
	
	
	@JsonProperty("session")
	private Session session;
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	
	
	@JsonProperty("queryResult")
	private QueryResult queryResult;
	public QueryResult getQueryResult() {
		return queryResult;
	}
	public void setQueryResult(QueryResult queryResult) {
		this.queryResult = queryResult;
	}
	
	
	@JsonProperty("originalDetectIntentRequest")
	private OriginalDetectIntentRequest originalDetectIntentRequest;
	public OriginalDetectIntentRequest getOriginalDetectIntentRequest() {
		return originalDetectIntentRequest;
	}
	public void setOriginalDetectIntentRequest(OriginalDetectIntentRequest originalDetectIntentRequest) {
		this.originalDetectIntentRequest = originalDetectIntentRequest;
	}
	
	

}
