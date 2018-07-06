package org.norsys.pamela.webhookSimple.action;

import org.norsys.pamela.webhookSimple.model.InterfaceReponses.DifferentTypeReponses;
import org.norsys.pamela.webhookSimple.model.demandePermission.Data;
import org.norsys.pamela.webhookSimple.model.demandePermission.DemandePermission;
import org.norsys.pamela.webhookSimple.model.demandePermission.GoogleDemandePermission;
import org.norsys.pamela.webhookSimple.model.demandePermission.PayloadDemandePermission;
import org.norsys.pamela.webhookSimple.model.demandePermission.SystemIntent;
import org.norsys.pamela.webhookSimple.model.request.QueryResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component("request_permission")
public class RequestPermissionAction implements WebhookAction {

	@Override
	public ResponseEntity<DifferentTypeReponses> dealAction(final QueryResult queryResult) {
		DemandePermission demandePermission = demandePermission();
		return ResponseEntity.status(HttpStatus.OK).body(demandePermission);
	}

	public DemandePermission demandePermission() {
		Data data = new Data();
		data.setType("type.googleapis.com/google.actions.v2.PermissionValueSpec");
		data.setOptContext("connaitre ta position");
		String[] permissions = { "NAME", "DEVICE_PRECISE_LOCATION" };
		data.setPermissions(permissions);

		SystemIntent systemIntent = new SystemIntent();
		systemIntent.setData(data);
		systemIntent.setIntent("actions.intent.PERMISSION");

		GoogleDemandePermission googleDemandePermission = new GoogleDemandePermission();
		googleDemandePermission.setExpectUserResponse(true);
		googleDemandePermission.setSystemIntent(systemIntent);

		PayloadDemandePermission payloadDemandePermission = new PayloadDemandePermission();
		payloadDemandePermission.setGoogle(googleDemandePermission);

		DemandePermission demandePermission = new DemandePermission();
		demandePermission.setPayload(payloadDemandePermission);
		return demandePermission;
	}
}
