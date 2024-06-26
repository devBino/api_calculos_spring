CREATE TABLE IF NOT EXISTS `tb_anexos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content_type` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `data` varbinary(255) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4rmniyyyd0ex3qilv028k0b8w` (`name`)
);