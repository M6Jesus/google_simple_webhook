package org.norsys.pamela.webhookSimple.model.demandePermission;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonPropertyOrder({"google"})
public class PayloadDemandePermission {
	
	@JsonProperty("google")
	private GoogleDemandePermission googleDemandePermission;
	public GoogleDemandePermission getGoogle() {
		return googleDemandePermission;
	}
	public void setGoogle(GoogleDemandePermission googleDemandePermission) {
		this.googleDemandePermission = googleDemandePermission;
	}
	
}
