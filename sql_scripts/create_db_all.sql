CREATE DATABASE IF NOT EXISTS court_management;

USE court_management;

CREATE TABLE IF NOT EXISTS customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    phone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS court_reservation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    reservation_date DATE,
    time_start TIME,
    time_end TIME,
    duration DOUBLE,
    price_schedule INT,
    total_price DOUBLE,
    is_doubles_match TINYINT(1),
    is_paid TINYINT(1),
    comments TEXT,
    customer_id INT,
    reservation_made_timestamp VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES customer(id)
) ENGINE=InnoDB;

USE court_management;

CREATE TABLE IF NOT EXISTS users (
  username varchar(50) NOT NULL,
  password char(68) NOT NULL,
  enabled tinyint NOT NULL,
  PRIMARY KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO users 
VALUES 
('admin','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1);

CREATE TABLE IF NOT EXISTS authorities (
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  UNIQUE KEY authorities4_idx_1 (username, authority),
  CONSTRAINT authorities4_ibfk_1 FOREIGN KEY (username) REFERENCES users (username)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO authorities 
VALUES 
('admin','ROLE_EMPLOYEE'),
('admin','ROLE_MANAGER'),
('admin','ROLE_ADMIN');

CREATE TABLE IF NOT EXISTS price_schedule (
    id INT AUTO_INCREMENT PRIMARY KEY,
    discount_name VARCHAR(50),
    workday_morning_singles double,
    workday_afternoon_singles double,
    offday_morning_singles double,
    offday_afternoon_singles double,
    workday_morning_doubles double,
    workday_afternoon_doubles double,
    offday_morning_doubles double,
    offday_afternoon_doubles double
);

CREATE TABLE IF NOT EXISTS price_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description_info VARCHAR(50),
    price double
);