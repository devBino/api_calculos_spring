CREATE TABLE IF NOT EXISTS `tb_calculohist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `tipo` tinyint DEFAULT NULL,
  `calculo_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKomh3jouuipspwyxwa6t5a0sr6` (`calculo_id`),
  CONSTRAINT `FKomh3jouuipspwyxwa6t5a0sr6` FOREIGN KEY (`calculo_id`) REFERENCES `tb_calculos` (`id`)
);