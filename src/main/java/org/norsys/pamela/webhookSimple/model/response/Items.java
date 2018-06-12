package org.norsys.pamela.webhookSimple.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"simpleResponse" })
public class Items {
	
	@JsonProperty("simpleResponse")
	private SimpleResponse simpleResponse;
	public SimpleResponse getSimpleResponse() {
		return simpleResponse;
	}
	public void setSimpleResponse(SimpleResponse simpleResponse) {
		this.simpleResponse = simpleResponse;
	}
	
}
