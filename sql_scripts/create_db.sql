CREATE DATABASE IF NOT EXISTS court_management;

USE court_management;

CREATE TABLE customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    phone VARCHAR(20)
);

CREATE TABLE court_reservation (
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
    comments TEXT,
    customer_id INT,
    reservation_made_timestamp VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES Customer(id)
) ENGINE=InnoDB;

