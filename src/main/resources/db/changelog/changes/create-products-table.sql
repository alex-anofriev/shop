--liquibase formatted sql
--changeset <mysql>:<create-products-table>

CREATE TABLE IF NOT EXISTS products (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `description` varchar(500) DEFAULT NULL,
                            `name` varchar(255) DEFAULT NULL,
                            `price` decimal(38,2) DEFAULT NULL,
                            `product_image` varchar(255) DEFAULT NULL,
                            `rating` double DEFAULT NULL,
                            PRIMARY KEY (`id`)
);

--rollback DROP TABLE products
