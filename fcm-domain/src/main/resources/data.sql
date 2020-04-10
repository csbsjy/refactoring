INSERT INTO USER(user_email, user_name, age, gender, user_type, enable, create_date_time) VALUES('a1010100z@naver.com', '서재연', 26, 'WOMAN' , 'TRAINER', true, now() );
INSERT INTO USER(user_email, user_name, age, gender, user_type, enable, create_date_time) VALUES('minjung@gmail.com', '김민정', 23, 'WOMAN' , 'MEMBER', true, now()) ;
INSERT INTO USER(user_email, user_name, age, gender, user_type, enable, create_date_time) VALUES('mun@naver.com', '문상일', 26, 'MAN' , 'MANAGER', true, now());

INSERT INTO ARTICLE(contents, create_date_time, modified_date_time, subject, user_id, display)  VALUES ('글 내용입니다', now(), now(), '글 제목입니다', 1, true);
INSERT INTO ARTICLE(contents, create_date_time, modified_date_time, subject, user_id, display)  VALUES ('김민정 게시글입니다', now(), now(), '두번쨰 제목입니다', 2, true);
INSERT INTO ARTICLE(contents, create_date_time, modified_date_time, subject, user_id, display)  VALUES ('문상일 게시글입니다', now(), now(), '세번째 제목입니다', 3, true);
INSERT INTO ARTICLE(contents, create_date_time, modified_date_time, subject, user_id, display)  VALUES ('보이면 안되는 게시글입니다', now(), now(), '보이면 안되는 제목입니다', 3, false);


INSERT INTO COMMENT(content, article_id, user_id) VALUES ('댓글댓글', 1, 1);
INSERT INTO COMMENT(content, article_id, user_id) VALUES ('댓글댓글22', 1, 2);
INSERT INTO COMMENT(content, article_id, user_id) VALUES ('댓글댓글22', 1, 3);
