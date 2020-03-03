package com.refactoring.fcm.DTO;


import lombok.Data;

import java.sql.Date;

@Data
public class ScheduleDTO {
	private int hour;//9~21
	private Date date;
	private String member_id;
	private String trainer_id;
	private String member_name;
	private String trainer_name;
}
