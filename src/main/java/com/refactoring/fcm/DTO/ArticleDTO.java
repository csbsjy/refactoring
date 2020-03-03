package com.refactoring.fcm.DTO;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class ArticleDTO {
	@NotNull
	private int idx;
	@NotNull
	private String writer;
	@NotNull
	@Size(min = 1, max = 30, message = "제목이 너무 길거나 짧습니다!!")
	private String subject;
	@NotNull
	@Size(min = 1, max = 10000000, message = "내용이 너무 길거나 짧습니다!!")
	private String content;
	private Date created;
	private int view;
}
