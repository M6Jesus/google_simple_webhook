package org.norsys.pamela.webhookSimple.model.demandePermission;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize
@JsonPropertyOrder({"expectUserResponse", "systemIntent"})

public class GoogleDemandePermission {
	
	@JsonProperty("expectUserResponse")
	private boolean expectUserResponse;
	public boolean isExpectUserResponse() {
		return expectUserResponse;
	}
	public void setExpectUserResponse(boolean expectUserResponse) {
		this.expectUserResponse = expectUserResponse;
	}
	
	
	
	@JsonProperty("systemIntent")
	private SystemIntent systemIntent;
	public SystemIntent getSystemIntent() {
		return systemIntent;
	}

	public void setSystemIntent(SystemIntent systemIntent) {
		this.systemIntent = systemIntent;
	}
	
	
}
