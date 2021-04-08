# 创建获取监控数据表，并初始化一条数据
CREATE TABLE `monitor_data` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` varchar(5120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT into monitor_data (data) values ('111');


# 创建评论表
CREATE TABLE `comment` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `content` varchar(5120) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;