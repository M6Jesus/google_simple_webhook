package org.norsys.pamela.webhookSimple.model.demandePermission;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonPropertyOrder({"intent", "data"})
public class SystemIntent {
		
		@JsonProperty("intent")
		private String intent;
		public String getIntent() {
			return intent;
		}
		public void setIntent(String intent) {
			this.intent = intent;
		}
		
		
		@JsonProperty("data")
		private Data data;
		public Data getData() {
			return data;
		}
		public void setData(Data data) {
			this.data = data;
		}
		
}
