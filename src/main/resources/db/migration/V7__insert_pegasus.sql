INSERT INTO ARMOR (name, fk_category) VALUES ('Pegasus', 1);
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v1', 'https://res.cloudinary.com/ksams/image/upload/v1633541840/bronze/pegasus_v1_dei0zp.png', (select id from armor where name like 'Pegasus'));
INSERT INTO ARMOR_VERSION_ATTACK (name, fk_armor_version) VALUES ('Pegasus Ryu Sei Ken', (select max(id) from armor_version));
INSERT INTO ARMOR_STRENGTH (amount, fk_armor, fk_strength) VALUES (5, 1, 1);
INSERT INTO ARMOR_STRENGTH (amount, fk_armor, fk_strength) VALUES (2, 1, 2);
INSERT INTO ARMOR_STRENGTH (amount, fk_armor, fk_strength) VALUES (2, 1, 3);
INSERT INTO ARMOR_STRENGTH (amount, fk_armor, fk_strength) VALUES (3, 1, 4);
INSERT INTO ARMOR_STRENGTH (amount, fk_armor, fk_strength) VALUES (4, 1, 5);
