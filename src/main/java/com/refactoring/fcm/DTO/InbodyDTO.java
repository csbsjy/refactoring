package com.refactoring.fcm.DTO;


import lombok.Data;

@Data
public class InbodyDTO {
	private String member_id;
	private float weight;
	private float fat;
	private float muscle;
	private String record;
}
