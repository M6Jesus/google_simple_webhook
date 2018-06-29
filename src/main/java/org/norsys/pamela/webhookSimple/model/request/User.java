package org.norsys.pamela.webhookSimple.model.request;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "lastSeen", "locale", "userId"})
public class User {
	
	@JsonProperty("lastSeen")
	private String lastSeen;
	public String getLastSeen() {
		return lastSeen;
	}
	public void setLastSeen(String lastSeen) {
		this.lastSeen = lastSeen;
	}
	
	
	@JsonProperty("locale")
	private String locale;
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	
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
	
	@JsonProperty("permissions")
	private String permissions[];
	public String[] getPermissions() {
		return permissions;
	}
	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}
	
	
	

}
