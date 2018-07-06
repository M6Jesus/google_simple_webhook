/**
 * 
 */
package org.norsys.pamela.webhookSimple.action;

import org.norsys.pamela.webhookSimple.model.InterfaceReponses.DifferentTypeReponses;
import org.norsys.pamela.webhookSimple.model.request.QueryResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author panou
 *
 */
@Component
public class SimpleAction implements WebhookAction {

	@Override
	public ResponseEntity<DifferentTypeReponses> dealAction(final QueryResult queryResult) {
		// TODO Auto-generated method stub
		return null;
	}

}
