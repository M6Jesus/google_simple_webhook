package org.norsys.pamela.webhookSimple.model.requettePermission;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "initialPrompts", "noInputPrompts" })
public class InputPrompt {
	
	@JsonProperty("initialPrompts")
	private List<InitialPrompts> initialPrompts;
	public List<InitialPrompts> getInitialPrompts() {
		return initialPrompts;
	}
	public void setInitialPrompts(List<InitialPrompts> initialPrompts) {
		this.initialPrompts = initialPrompts;
	}
	
	
	@JsonProperty("noInputPrompts")
	private List noInputPrompts;
	public List getNoInputPrompts() {
		return noInputPrompts;
	}
	public void setNoInputPrompts(List noInputPrompts) {
		this.noInputPrompts = noInputPrompts;
	}
	
//	public List<NoInputPrompts> getNoInputPrompts() {
//		return noInputPrompts;
//	}
//	public void setNoInputPrompts(List<NoInputPrompts> noInputPrompts) {
//		this.noInputPrompts = noInputPrompts;
//	}
	
}
