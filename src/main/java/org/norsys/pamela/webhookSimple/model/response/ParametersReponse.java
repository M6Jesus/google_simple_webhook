package org.norsys.pamela.webhookSimple.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "languageCode", "parameters"})
public class ParametersReponse {
	
	
	@JsonProperty("param")
	private String param;
	public String getParam() {
		return param;
	}
	public void setParam(final String param) {
		this.param = param;
	}
	
}
