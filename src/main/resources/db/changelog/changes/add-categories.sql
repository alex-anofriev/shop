--liquibase formatted sql
--changeset <mysql>:<add-categories>

INSERT INTO categories (name, description) VALUES ('laptops', 'expensive Laptops'),
                                                  ('smartphones', 'expensive phones'),
                                                  ('appliances', 'something for home'),
                                                  ('pet products', 'i hate cats'),
                                                  ('animals', 'we love them'),
                                                  ('furniture', 'some nice wood tables'),
                                                  ('tablets', 'not popular anymore');

--rollback DELETE FROM categories;

