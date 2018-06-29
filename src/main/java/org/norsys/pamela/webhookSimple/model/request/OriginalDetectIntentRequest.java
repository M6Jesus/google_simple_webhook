package org.norsys.pamela.webhookSimple.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * cette classe represente l'objet OriginalDetectIntentRequest
 * d'un requête
 * @author panou
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "source", "version", "payload" })
public class OriginalDetectIntentRequest {
	@JsonProperty("source")
	private String source;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
	@JsonProperty("version")
	private String version;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	@JsonProperty("payload")
	private PayloadRequest payloadRequest;
	public PayloadRequest getPayload() {
		return payloadRequest;
	}
	public void setPayload(PayloadRequest payloadRequest) {
		this.payloadRequest = payloadRequest;
	}
	
	
	
	
	
}
