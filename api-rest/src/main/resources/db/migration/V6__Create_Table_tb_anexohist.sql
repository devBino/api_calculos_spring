CREATE TABLE IF NOT EXISTS `tb_anexohist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `tipo` tinyint DEFAULT NULL,
  `anexo_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKet2xt7s154e0m8dqj0vsja2r8` (`anexo_id`),
  CONSTRAINT `FKet2xt7s154e0m8dqj0vsja2r8` FOREIGN KEY (`anexo_id`) REFERENCES `tb_anexos` (`id`)
);