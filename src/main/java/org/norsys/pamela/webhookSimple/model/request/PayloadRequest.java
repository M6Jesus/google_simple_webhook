package org.norsys.pamela.webhookSimple.model.request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Cette classe represente l'objet Payload d'une requête
 * @author panou
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "isInSandbox", "isInSandbox", "surface", "imputs", "user", "conversation", "availableSurfaces"  })
public class PayloadRequest {
	@JsonProperty("isInSandbox")
	private String isInSandbox;
	public String getIsInSandbox() {
		return isInSandbox;
	}
	public void setIsInSandbox(String isInSandbox) {
		this.isInSandbox = isInSandbox;
	}
	
	
	@JsonProperty("surface")
	private Surface surface;
	public Surface getSurface() {
		return surface;
	}
	public void setSurface(Surface surface) {
		this.surface = surface;
	}

	
	@JsonProperty("inputs")
	private List<Inputs> inputs;
	public List<Inputs> getInputs() {
		return inputs;
	}
	public void setInputs(List<Inputs> inputs) {
		this.inputs = inputs;
	}
	
	
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
	
	
	@JsonProperty("availableSurfaces")
	private List<AvailableSurfaces> availableSurfaces;
	public List<AvailableSurfaces> getAvailableSurfaces() {
		return availableSurfaces;
	}
	public void setAvailableSurfaces(List<AvailableSurfaces> availableSurfaces) {
		this.availableSurfaces = availableSurfaces;
	}
	
	@JsonProperty("device")
	private Device device;
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	

//	@JsonProperty("url")
//	public String url;
//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
	
	
}
