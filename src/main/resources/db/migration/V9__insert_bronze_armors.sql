INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v2', 'https://res.cloudinary.com/ksams/image/upload/v1633541841/bronze/pegasus_v2_mgielg.jpg', 1);
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v3', 'https://res.cloudinary.com/ksams/image/upload/v1633541840/bronze/pegasus_v3_lbbfpo.jpg', 1);

INSERT INTO ARMOR (name, fk_category) VALUES ('Draco', (select id from ARMOR_CATEGORY where name = 'Bronze'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v1', 'https://res.cloudinary.com/ksams/image/upload/v1633541829/bronze/draco_v1_v1tk9v.jpg', (select id from armor where name like 'Draco'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('dark', 'https://res.cloudinary.com/ksams/image/upload/v1633541829/bronze/draco_dark_zakuqz.jpg', (select id from armor where name like 'Draco'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v2', 'https://res.cloudinary.com/ksams/image/upload/v1633541829/bronze/draco_v2_ujnbf7.jpg', (select id from armor where name like 'Draco'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v3', 'https://res.cloudinary.com/ksams/image/upload/v1633541829/bronze/draco_v3_dlvigu.jpg', (select id from armor where name like 'Draco'));
INSERT INTO ARMOR (name, fk_category) VALUES ('Cygnus', (select id from ARMOR_CATEGORY where name = 'Bronze'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v1', 'https://res.cloudinary.com/ksams/image/upload/v1633541820/bronze/cygnus_v1_cohtjd.png', (select id from armor where name like 'Cygnus'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('dark', 'https://res.cloudinary.com/ksams/image/upload/v1633544831/bronze/cygnus_dark_pniuwp.jpg', (select id from armor where name like 'Cygnus'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v2', 'https://res.cloudinary.com/ksams/image/upload/v1633541819/bronze/cygnus_v2_gsosf0.jpg', (select id from armor where name like 'Cygnus'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v3', 'https://res.cloudinary.com/ksams/image/upload/v1633541819/bronze/cygnus_v3_lc04um.jpg', (select id from armor where name like 'Cygnus'));
INSERT INTO ARMOR (name, fk_category) VALUES ('Andromeda', (select id from ARMOR_CATEGORY where name = 'Bronze'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v1', 'https://res.cloudinary.com/ksams/image/upload/v1633541857/bronze/andromeda_v1_yp5vap.png', (select id from armor where name like 'Andromeda'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('dark', 'https://res.cloudinary.com/ksams/image/upload/v1633541857/bronze/andromeda_v1_yp5vap.png', (select id from armor where name like 'Andromeda'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v2', 'https://res.cloudinary.com/ksams/image/upload/v1633541856/bronze/andromeda_v2_oaxh00.jpg', (select id from armor where name like 'Andromeda'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v3', 'https://res.cloudinary.com/ksams/image/upload/v1633541856/bronze/andromeda_v3_qvuivm.jpg', (select id from armor where name like 'Andromeda'));
INSERT INTO ARMOR (name, fk_category) VALUES ('Phoenix', (select id from ARMOR_CATEGORY where name = 'Bronze'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v1', 'https://res.cloudinary.com/ksams/image/upload/v1633541851/bronze/phoenix_v1_rm1cel.png', (select id from armor where name like 'Phoenix'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('dark', 'https://res.cloudinary.com/ksams/image/upload/v1633541849/bronze/phoenix_dark_o81tor.jpg', (select id from armor where name like 'Phoenix'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v2', 'https://res.cloudinary.com/ksams/image/upload/v1633541850/bronze/phoenix_v2_ohlv75.jpg', (select id from armor where name like 'Phoenix'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v3', 'https://res.cloudinary.com/ksams/image/upload/v1633541850/bronze/phoenix_v3_ig8edd.jpg', (select id from armor where name like 'Phoenix'));
INSERT INTO ARMOR (name, fk_category) VALUES ('Monoceros', (select id from ARMOR_CATEGORY where name = 'Bronze'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v1', 'https://res.cloudinary.com/ksams/image/upload/v1633541810/bronze/monoceros_n4be05.jpg', (select id from armor where name like 'Monoceros'));
INSERT INTO ARMOR (name, fk_category) VALUES ('Leo Minor', (select id from ARMOR_CATEGORY where name = 'Bronze'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v1', 'https://res.cloudinary.com/ksams/image/upload/v1633541810/bronze/leo_minor_x5pr6a.jpg', (select id from armor where name like 'Leo Minor'));
INSERT INTO ARMOR (name, fk_category) VALUES ('Ursa Maior', (select id from ARMOR_CATEGORY where name = 'Bronze'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v1', 'https://res.cloudinary.com/ksams/image/upload/v1633541809/bronze/ursa_maior_aliyki.jpg', (select id from armor where name like 'Ursa Maior'));
INSERT INTO ARMOR (name, fk_category) VALUES ('Hydra', (select id from ARMOR_CATEGORY where name = 'Bronze'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v1', 'https://res.cloudinary.com/ksams/image/upload/v1633541811/bronze/hydra_mqkzka.jpg', (select id from armor where name like 'Hydra'));
INSERT INTO ARMOR (name, fk_category) VALUES ('Lupus', (select id from ARMOR_CATEGORY where name = 'Bronze'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v1', 'https://res.cloudinary.com/ksams/image/upload/v1633541811/bronze/lupus_ctvyoo.jpg', (select id from armor where name like 'Lupus'));
INSERT INTO ARMOR (name, fk_category) VALUES ('Chamaeleon', (select id from ARMOR_CATEGORY where name = 'Bronze'));
INSERT INTO ARMOR_VERSION (name, image, fk_armor) VALUES ('v1', 'https://res.cloudinary.com/ksams/image/upload/v1633541809/bronze/chamaeleon_ifjrle.jpg', (select id from armor where name like 'Chamaeleon'));