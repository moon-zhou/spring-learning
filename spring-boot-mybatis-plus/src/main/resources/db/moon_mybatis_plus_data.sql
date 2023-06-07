DELETE FROM user;

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');


DELETE FROM user_code;

INSERT INTO user_code (id, name, age, email) VALUES
                                            (1, 'Jone', 18, 'test1@baomidou.com'),
                                            (2, 'Jack', 20, 'test2@baomidou.com'),
                                            (3, 'Tom', 28, 'test3@baomidou.com'),
                                            (4, 'Sandy', 21, 'test4@baomidou.com'),
                                            (5, 'Billie', 24, 'test5@baomidou.com');


DELETE FROM user_info;

INSERT INTO user_info (id, name, age, email, dept, create_time) VALUES
(1, 'Jone', 18, 'test1@baomidou.com', '一部', CURRENT_TIMESTAMP),
(2, 'Jack', 20, 'test2@baomidou.com', '二部', CURRENT_TIMESTAMP),
(3, 'Tom', 28, 'test3@baomidou.com', '一部', CURRENT_TIMESTAMP),
(4, 'Sandy', 21, 'test4@baomidou.com', '二部', CURRENT_TIMESTAMP),
(5, 'Billie', 24, 'test5@baomidou.com', '二部', CURRENT_TIMESTAMP);



DELETE FROM hardship_aid_apply;

INSERT INTO `hardship_aid_apply` (`id`, `name`, `id_no`, `age`, `difficult_description`, `user_no`, `dept`, `create_time`, `create_user`, `update_time`, `update_user`, `deleted`) VALUES (1, 'moon1', '111', 18, '困难', '001', 'member', '2022-06-23 21:04:12', 'admin', '2022-06-23 21:04:20', 'admin', 0);
INSERT INTO `hardship_aid_apply` (`id`, `name`, `id_no`, `age`, `difficult_description`, `user_no`, `dept`, `create_time`, `create_user`, `update_time`, `update_user`, `deleted`) VALUES (2, 'moon2', '222', 19, 'difficult', '002', 'member', '2022-06-23 21:05:18', 'admin', '2022-06-23 21:05:25', 'admin', 0);
