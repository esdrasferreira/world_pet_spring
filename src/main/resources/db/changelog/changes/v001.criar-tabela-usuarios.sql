-- liquibase formatted sql
-- changeset esdras:1
-- comment: Criacao da primeira versao



CREATE TABLE IF NOT EXISTS usuarios(
                         `id` BIGINT NOT NULL AUTO_INCREMENT,
                         `nome` VARCHAR(45) not null ,
                         `idade` date not null ,
                         `sexo` enum('M', 'F'),
                         `endereco` VARCHAR(45) not null ,
                         PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;
-- rollback DROP TABLE usuario;