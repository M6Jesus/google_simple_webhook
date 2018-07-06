package org.norsys.pamela.webhookSimple.action;

import org.norsys.pamela.webhookSimple.model.InterfaceReponses.DifferentTypeReponses;
import org.norsys.pamela.webhookSimple.model.request.QueryResult;
import org.springframework.http.ResponseEntity;

public interface WebhookAction {

	ResponseEntity<DifferentTypeReponses> dealAction(QueryResult queryResult);
}
