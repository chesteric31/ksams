UPDATE ARMOR_VERSION SET image = 'https://res.cloudinary.com/ksams/image/upload/v1633541867/silver/canis_maior_j0tiba.jpg' WHERE fk_armor = (select id from armor where name like 'Canis Major');
