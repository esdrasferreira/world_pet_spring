-- liquibase formatted sql
-- changeset esdras:3
-- comment: Criacao da terceira versao



CREATE TABLE IF NOT EXISTS adocoes(
                         `id` BIGINT NOT NULL AUTO_INCREMENT,
                         `pet_id` BIGINT NOT NULL,
                         `usuario_id` BIGINT NOT NULL,
                         `antigo_usuario_id` BIGINT NOT NULL,
                         `data_adocao` DATE NOT NULL ,
                         `informacoes` VARCHAR(255) NULL,
                         PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;
-- rollback DROP TABLE usuario;