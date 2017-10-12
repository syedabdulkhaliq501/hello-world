package com.woobloo.response.model;

public class SuccessMessage {
	private String ticketId;
	private boolean agentAvailable;

	private String message;
	private boolean status;

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public boolean isAgentAvailable() {
		return agentAvailable;
	}

	public void setAgentAvailable(boolean agentAvailable) {
		this.agentAvailable = agentAvailable;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
