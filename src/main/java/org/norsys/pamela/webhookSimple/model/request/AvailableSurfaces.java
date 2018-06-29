package org.norsys.pamela.webhookSimple.model.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "capabilities"})
public class AvailableSurfaces {
	
	@JsonProperty("capabilities")
	private List<Capabilities> capabilities;
	public List<Capabilities> getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(List<Capabilities> capabilities) {
		this.capabilities = capabilities;
	}
	
	
}
