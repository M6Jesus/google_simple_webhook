package org.norsys.pamela.webhookSimple.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * cette classe reprensente l'objet OutputContexts d'une reponse
 * @author panou
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "lifespanCount", "parameters" })
public class OutputContextsReponse {
	
	@JsonProperty("name")
	private String name;
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	
	
	@JsonProperty("lifespanCount")
	private int lifespanCount;
	public int getLifespanCount() {
		return lifespanCount;
	}
	public void setLifespanCount(final int lifespanCount) {
		this.lifespanCount = lifespanCount;
	}
	
	
	@JsonProperty("parameters")
	private ParametersReponse parameters;
	public ParametersReponse getParameters() {
		return parameters;
	}
	public void setParameters(final ParametersReponse parameters) {
		this.parameters = parameters;
	}
	
	
}
