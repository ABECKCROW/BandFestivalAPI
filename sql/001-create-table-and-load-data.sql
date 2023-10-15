DROP TABLE IF EXISTS rij;
DROP TABLE IF EXISTS bands;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS stages;
DROP TABLE IF EXISTS roads;

CREATE TABLE Festival (
  id int unsigned AUTO_INCREMENT,
  event_year VARCHAR(204) NOT NULL,
  event_date DATE,
  start_time DATE,
  end_time DATE,
  PRIMARY KEY(id),
)AUTO_INCREMENT = 2023;

CREATE TABLE bands (
  id int unsigned AUTO_INCREMENT,
  band_name VARCHAR(204) NOT NULL,
  act_announcement_date DATE,
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

INSERT INTO bands (band_name, act_announcement_date) VALUES ("ASIAN KUNG-FU GENERATION", '2023-08-24');
INSERT INTO bands (band_name, act_announcement_date) VALUES ("Rhythmic Toy World", '2023-08-23');

INSERT INTO members (member_name, part, band_id) VALUES ("Gotoh", "Gt&Vo", 1);
INSERT INTO members (member_name, part, band_id) VALUES ("Kita", "Gt", 1);
INSERT INTO members (member_name, part, band_id) VALUES ("Yamada", "Ba", 1);
INSERT INTO members (member_name, part, band_id) VALUES ("Izichi", "Drum", 1);
INSERT INTO members (member_name, part, band_id) VALUES ("Uchida", "Gt&Vo", 2);
INSERT INTO members (member_name, part, band_id) VALUES ("Kishi", "Gt", 2);
INSERT INTO members (member_name, part, band_id) VALUES ("Sudo", "Ba", 2);
INSERT INTO members (member_name, part, band_id) VALUES ("Isomura", "Drum", 2);
INSERT INTO members (member_name, part, band_id) VALUES ("Saitoh", "Gt&Vo", 3);
