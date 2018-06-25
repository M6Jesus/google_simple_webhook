package org.norsys.pamela.webhookSimple.model.reponsePermission;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "user", "conversation", "inputs", "surface", "device", "isInSandbox" })
public class ReponsePermission {
	
	@JsonProperty("user")
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@JsonProperty("conversation")
	private Conversation conversation;
	public Conversation getConversation() {
		return conversation;
	}
	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	
	
	@JsonProperty("inputs")
	private Inputs inputs;
	public Inputs getInputs() {
		return inputs;
	}
	public void setInputs(Inputs inputs) {
		this.inputs = inputs;
	}
	
	
	@JsonProperty("surface")
	private Surface surface;
	public Surface getSurface() {
		return surface;
	}
	public void setSurface(Surface surface) {
		this.surface = surface;
	}
	
	
	@JsonProperty("device")
	private Device device;
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	
	@JsonProperty("isInSandbox")
	private boolean isInSandbox;
	public boolean isInSandbox() {
		return isInSandbox;
	}
	public void setInSandbox(boolean isInSandbox) {
		this.isInSandbox = isInSandbox;
	}
	
}
