USE court_management;
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
