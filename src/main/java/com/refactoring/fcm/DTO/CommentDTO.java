package com.refactoring.fcm.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentDTO {
	private String center_id;
	private String writer;
	private String content;
	private Date created;
	private int idx;
}
