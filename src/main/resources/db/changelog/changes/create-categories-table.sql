--liquibase formatted sql
--changeset <mysql>:<create-categories-table>

CREATE TABLE IF NOT EXISTS categories (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `description` varchar(255) DEFAULT NULL,
                              `name` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`)
);

--rollback DROP TABLE categories
