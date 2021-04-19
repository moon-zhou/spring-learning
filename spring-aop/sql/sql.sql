DROP TABLE IF EXISTS `aop_user`;

CREATE TABLE `aop_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
                           `money` int(11) DEFAULT NULL,
                           PRIMARY   KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `account` */
insert  into `account`(`id`,`username`,`money`) values (1,'zhangsan',1000),(2,'lisi',1000);

