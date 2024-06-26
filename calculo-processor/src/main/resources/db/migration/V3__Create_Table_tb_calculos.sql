CREATE TABLE IF NOT EXISTS `tb_calculos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `calculouu` varchar(255) DEFAULT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `estado` char(1) NOT NULL,
  `numero1` double NOT NULL,
  `numero2` double NOT NULL,
  `resultado` double DEFAULT NULL,
  `sinal` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ay9hh9vr6yjcbxl1g8e6ng5sa` (`calculouu`)
);