INSERT INTO user (id, user_id, password, name, create_date) values (1, 'javajigi', 'test', '자바지기', current_timestamp);
INSERT INTO user (id, user_id, password, name, create_date) values (2, 'sanjigi', 'test', '산지기', current_timestamp);
INSERT INTO user (id, user_id, password, name, create_date) values (3, 'kkssry', 'kkssry', 'Skull', current_timestamp);

INSERT INTO issue (id, subject, comment, writer_id,create_date, deleted) values (1, 'SUBJECT ~~~', 'Comment ~~~~~~', 1,current_timestamp, 0);
INSERT INTO issue (id, subject, comment, writer_id,create_date, deleted) values (2, 'SUBJECT ~~~', 'Comment ~~~~~~', 2,current_timestamp, 0);
INSERT INTO issue (id, subject, comment, writer_id,create_date, deleted) values (3, 'SUBJECT ~~~', 'Comment ~~~~~~', 3,current_timestamp, 0);

INSERT INTO milestone(id, subject, start_date, end_date,create_date,writer_id) values (1, '마일스톤!!', '2019-01-01','2019-12-31',current_timestamp,3);