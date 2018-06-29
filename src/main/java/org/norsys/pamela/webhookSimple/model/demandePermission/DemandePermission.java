package org.norsys.pamela.webhookSimple.model.demandePermission;

import org.norsys.pamela.webhookSimple.model.InterfaceReponses.DifferentTypeReponses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonPropertyOrder({"payload"})
public class DemandePermission implements DifferentTypeReponses {
	
	@JsonProperty("payload")
	private PayloadDemandePermission payloadDemandePermission;
	public PayloadDemandePermission getPayload() {
		return payloadDemandePermission;
	}
	public void setPayload(PayloadDemandePermission payloadDemandePermission) {
		this.payloadDemandePermission = payloadDemandePermission;
	}
	

}
