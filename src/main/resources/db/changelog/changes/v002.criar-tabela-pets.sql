-- liquibase formatted sql
-- changeset esdras:2
-- comment: Criacao da segunda versao



CREATE TABLE IF NOT EXISTS pets(
                         `id` INT NOT NULL AUTO_INCREMENT,
                         `nome` VARCHAR(45) not null ,
                         `tipo` VARCHAR(45) not null ,
                         `idade` date not null ,
                         `sexo` enum('M', 'F'),
                         `status` enum('DISPONIVEL', 'INDISPONIVEL'),
                         PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;
-- rollback DROP TABLE usuario;