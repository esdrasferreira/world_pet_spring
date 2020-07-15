-- liquibase formatted sql
-- changeset esdras:4
-- comment: Criacao da quarta versao



CREATE TABLE IF NOT EXISTS pet_usuario(
                         `pet_id` BIGINT NOT NULL,
                         `usuario_id` BIGINT NOT NULL

)
    ENGINE = InnoDB;
