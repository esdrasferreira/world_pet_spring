-- liquibase formatted sql
-- changeset esdras:1
-- comment: Criacao da primeira versao



CREATE TABLE IF NOT EXISTS usuarios(
                         `usuario_id` BIGINT NOT NULL AUTO_INCREMENT,
                         `usuario_nome` VARCHAR(45) not null ,
                         `idade` date not null ,
                         `sexo` enum('M', 'F'),
                         PRIMARY KEY (`usuario_id`)
)
    ENGINE = InnoDB;
-- rollback DROP TABLE usuario;