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
    court_number INT,
    reservation_date DATE,
    time_start TIME,
    time_end TIME,
    duration DOUBLE,
    price_schedule INT,
    total_price DOUBLE,
    is_doubles_match TINYINT(1),
    is_paid TINYINT(1),
    is_cash TINYINT(1),
    comments TEXT,
    customer_id INT,
    accepted_by VARCHAR(50),
    accepted_at VARCHAR(50),
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
    discount_name VARCHAR(100),
    workday_morning_singles double,
    workday_afternoon_singles double,
    offday_morning_singles double,
    offday_afternoon_singles double,
    workday_morning_doubles double,
    workday_afternoon_doubles double,
    offday_morning_doubles double,
    offday_afternoon_doubles double
);
INSERT INTO price_schedule (discount_name, workday_morning_singles, workday_afternoon_singles, offday_morning_singles, offday_afternoon_singles, workday_morning_doubles, workday_afternoon_doubles, offday_morning_doubles, offday_afternoon_doubles)
VALUES 
('Cena regularna', 20, 33, 33, 33, 30, 40, 40, 40),
('Dzieci, młodzież szkolna do lat 16', 13, 22, 22, 22, 20, 30, 30, 30),
('Karta Rodzina 3+ (osoby dorosłe)', 10, 16.5, 16.5, 16.5, 15, 20, 20, 20),
('Karta Rodzina 3+ (dzieci, młodzież szkolna do lat 16)', 6.5, 11, 11, 11, 10, 15, 15, 15),
('Karta Gliwicki Senior 60+', 14.5, 23, 23, 23, 20, 30, 30, 30),
('Karta Gliwicki Senior 75+', 10.5, 17, 17, 17, 15, 20, 20, 20),
('Gliwicka Karta Mieszkańca (osoby dorosłe)', 16, 24, 26, 26, 24, 32, 32, 32),
('Gliwicka Karta Mieszkańca (dzieci, młodzież szkolna do lat 16)', 10.5, 17.5, 17.5, 17.5, 16, 24, 24, 24),
('Gliwickie stowarzyszenia i kluby sportowe', 10, 16, 10, 16, 15, 22, 15, 22),
('Inne kluby sportowe i podmioty gospodarcze', 15, 20, 15, 20, 20, 26, 20, 26);


CREATE TABLE IF NOT EXISTS price_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description_info VARCHAR(50),
    price double
);

USE court_management;
CREATE TABLE IF NOT EXISTS court (
    id INT AUTO_INCREMENT PRIMARY KEY,
    court_number INT,
    is_active TINYINT(1),
    is_taken TINYINT(1),
    is_flooded TINYINT(1)
) ENGINE=InnoDB;
INSERT INTO court (court_number,is_active,is_taken,is_flooded) VALUES
(1,1,0,0),
(2,1,0,0),
(3,1,0,0),
(4,1,0,0),
(5,1,0,0);

USE court_management;
CREATE TABLE IF NOT EXISTS settlement_day (
    id INT AUTO_INCREMENT PRIMARY KEY,
    summary_date DATE,
    cash_total DOUBLE,
    card_total DOUBLE,
    cash_box DOUBLE,
    card_terminal DOUBLE,
    number_of_reservations INT,
    is_correct TINYINT(1),
    accepted_by VARCHAR(50),
    accepted_at VARCHAR(50)
) ENGINE=InnoDB;