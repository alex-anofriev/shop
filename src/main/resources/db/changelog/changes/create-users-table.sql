--liquibase formatted sql
--changeset <mysql>:<create-users-table>

CREATE TABLE IF NOT EXISTS users (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `email` varchar(255) DEFAULT NULL,
                         `first_name` varchar(255) DEFAULT NULL,
                         `last_name` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `role` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
);

--rollback DROP TABLE users
