package org.norsys.pamela.webhookSimple.model.reponsePermission;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "userId", "profile" })
public class User {
		
		@JsonProperty("userId")
		private String userId;
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		
		@JsonProperty("profile")
		private Profile profile;
		public Profile getProfile() {
			return profile;
		}
		public void setProfile(Profile profile) {
			this.profile = profile;
		}
		
}
