CREATE TABLE IF NOT EXISTS `tb_usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ativo` int NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `user` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fw6qijy1app3gi3vp4e6oc2xn` (`nome`),
  UNIQUE KEY `UK_9w7jlo55oab2mtx75sny69v5g` (`password`),
  UNIQUE KEY `UK_exwg0cd7r1jh15my8qc93gcma` (`user`)
);
