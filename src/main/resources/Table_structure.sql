CREATE TABLE office
(
    id          BIGINT PRIMARY KEY,
    office_name VARCHAR(255),
    address     VARCHAR(255)
);

CREATE TABLE employee
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(255),
    salary    INTEGER,
    office_id INTEGER REFERENCES office (id)
);

