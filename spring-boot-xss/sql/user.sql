CREATE TABLE `moon`.`user` (
  `id` INT NOT NULL,
  `name` VARCHAR(128) NULL,
  `gender` CHAR(1) NULL,
  PRIMARY KEY (`id`));


insert into user values (1, 'moon1', 1);
insert into user values (2, 'moon2', 0);
insert into user values (3, 'moon3', 1);
