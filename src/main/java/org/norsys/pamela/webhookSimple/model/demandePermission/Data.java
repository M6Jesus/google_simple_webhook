package org.norsys.pamela.webhookSimple.model.demandePermission;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"@type", "optContext", "permissions"})
public class Data {
	@JsonTypeInfo(
			use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type",
            visible = true
			) 
	@JsonProperty("@type")
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}




	@JsonProperty("optContext")
	private String optContext;
	public String getOptContext() {
		return optContext;
	}
	public void setOptContext(String optContext) {
		this.optContext = optContext;
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
