CREATE TABLE `monitor_data` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` varchar(5120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT into monitor_data (data) values ('111');