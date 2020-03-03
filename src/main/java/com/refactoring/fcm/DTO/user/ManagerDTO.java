package com.refactoring.fcm.DTO.user;

import lombok.Data;


@Data
public class ManagerDTO extends Account {
	private String name;
	private String phoneNumber;

	public ManagerDTO() {
		this.setType("MANAGER");
	}

	@Override
	public String toString() {
		return this.getId() + "," + this.getPassword() + "," + this.getType() + "," + this.getName() + ", " + this.getCenter_id() + ", " + this.getPhoneNumber() + ".";
	}


}


