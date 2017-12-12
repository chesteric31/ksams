CREATE TABLE ARMOR_CATEGORY (
	id SERIAL,
	name varchar(255) not null
);

CREATE SEQUENCE armor_category_sequence start WITH 1 INCREMENT BY 1;

INSERT INTO ARMOR_CATEGORY (name) VALUES ('Bronze');