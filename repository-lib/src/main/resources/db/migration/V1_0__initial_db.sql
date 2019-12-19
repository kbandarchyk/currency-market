CREATE TABLE currency (
  id BIGSERIAL NOT NULL,
  name VARCHAR(255) NOT NULL,
  code varchar(127) NOT NULL,
  description varchar(127) DEFAULT NULL,
  PRIMARY KEY (id)
);