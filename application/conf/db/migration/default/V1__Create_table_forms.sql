DROP TABLE IF EXISTS `forms`;

CREATE TABLE `forms` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `number_code` varchar(45) NOT NULL,
  `version` int(11) NOT NULL,
  `content` mediumtext NOT NULL,
  `level_approve_1` varchar(45) DEFAULT NULL,
  `level_approve_2` varchar(45) DEFAULT NULL,
  `level_approve_3` varchar(45) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `forms_idx` (`title`, `number_code`, `start_date`, `end_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;