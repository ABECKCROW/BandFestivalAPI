DROP TABLE IF EXISTS bands;
DROP TABLE IF EXISTS members;

CREATE TABLE bands (
  id int unsigned AUTO_INCREMENT,
  band_name VARCHAR(204) NOT NULL,
  act_Announcement_Date DATE,
  PRIMARY KEY(id),
  UNIQUE(band_name)
);

CREATE TABLE members (
  id int unsigned AUTO_INCREMENT,
  member_name VARCHAR(1019) NOT NULL,
  part VARCHAR(10) NOT NULL,
  band_id int unsigned,
  PRIMARY KEY(id),
  FOREIGN KEY(band_id) REFERENCES bands(id)
);

INSERT INTO bands (band_name, act_Announcement_Date) VALUES ("ASIAN KUNG-FU GENERATION", '2023-08-24');
INSERT INTO bands (band_name, act_Announcement_Date) VALUES ("Rhythmic Toy World", '2023-08-23');

INSERT INTO members (member_name, part, band_id) VALUES ("Gotoh", "Gt&Vo", 1);
INSERT INTO members (member_name, part, band_id) VALUES ("Kita", "Gt", 1);
INSERT INTO members (member_name, part, band_id) VALUES ("Yamada", "Ba", 1);
INSERT INTO members (member_name, part, band_id) VALUES ("Izichi", "Drum", 1);
INSERT INTO members (member_name, part, band_id) VALUES ("Uchida", "Gt&Vo", 2);
INSERT INTO members (member_name, part, band_id) VALUES ("Kishi", "Gt", 2);
INSERT INTO members (member_name, part, band_id) VALUES ("Sudo", "Ba", 2);
INSERT INTO members (member_name, part, band_id) VALUES ("Isomura", "Drum", 2);
