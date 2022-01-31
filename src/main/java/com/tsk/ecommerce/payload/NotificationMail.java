package com.tsk.ecommerce.payload;

import java.util.List;

import com.tsk.ecommerce.entities.Address;
import com.tsk.ecommerce.entities.OrderLine;

public class NotificationMail {

	private String emailRecipient;
	private String clientName;
	private String numCommand;
	private List<OrderLine> orderLines;
	private Address address;
	
	
	public NotificationMail(String emailRecipient, String clientName, String numCommand, List<OrderLine> orderLines,
			Address address) {
		super();
		this.emailRecipient = emailRecipient;
		this.clientName = clientName;
		this.numCommand = numCommand;
		this.orderLines = orderLines;
		this.address = address;
	}


	public NotificationMail() {
		super();
	}


	public String getEmailRecipient() {
		return emailRecipient;
	}


	public void setEmailRecipient(String emailRecipient) {
		this.emailRecipient = emailRecipient;
	}


	public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public String getNumCommand() {
		return numCommand;
	}


	public void setNumCommand(String numCommand) {
		this.numCommand = numCommand;
	}


	public List<OrderLine> getOrderLines() {
		return orderLines;
	}


	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	
}
