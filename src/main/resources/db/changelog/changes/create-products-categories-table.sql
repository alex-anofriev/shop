--liquibase formatted sql
--changeset <mysql>:<create-products_categories-table>

CREATE TABLE IF NOT EXISTS products_categories (
                                       `product_id` bigint NOT NULL,
                                       `category_id` bigint NOT NULL,

                                       CONSTRAINT FKqt6m2o5dly3luqcm00f5t4h2p FOREIGN KEY (`category_id`) REFERENCES categories (`id`) ON DELETE CASCADE,
                                       CONSTRAINT FKtj1vdea8qwerbjqie4xldl1el FOREIGN KEY (`product_id`) REFERENCES products (`id`)
);

--rollback DROP TABLE products_categories
