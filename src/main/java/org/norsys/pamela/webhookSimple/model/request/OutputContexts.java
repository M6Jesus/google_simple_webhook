package org.norsys.pamela.webhookSimple.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * cette classe represente l'objet OutputContexts d'un requête
 * @author panou
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name" , "lifespanCount", "parameters" })
public class OutputContexts {
	
	@JsonProperty("name")
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("lifespanCount")
	private Integer lifespanCount;
	public Integer getLifespanCount() {
		return lifespanCount;
	}
	public void setLifespanCount(Integer lifespanCount) {
		this.lifespanCount = lifespanCount;
	}
	
	@JsonProperty("parameters")
	private Parameters parameters;
	public Parameters getParameters() {
		return parameters;
	}
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}
	
	

	
	
	
}
