CREATE SCHEMA IF NOT EXISTS test;
USE
test;

CREATE TABLE office
(
    id          BIGINT PRIMARY KEY,
    office_name VARCHAR(10),
    address     VARCHAR(50)
);

CREATE TABLE employee
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(50),
    position  VARCHAR(30),
    salary    INTEGER,
    office_id BIGINT REFERENCES office (id)
);

CREATE TABLE rates
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    position VARCHAR(30),
    salary   INTEGER
);

INSERT INTO office VALUES ('1', 'OP1', 'Krasnaya st. 1');
INSERT INTO office VALUES ('2', 'OP2', 'Severnaya st. 200');
INSERT INTO office VALUES ('3', 'OP3', 'Prostornaya st. 7');
INSERT INTO office VALUES ('4', 'OP4', 'Begovaya st. 5');

INSERT INTO employee (name, position, salary, office_id) VALUES ('Ivan Ivanov', 'engineer 1', 1000, 2);
INSERT INTO employee (name, position, salary, office_id) VALUES ('Petr Petrov', 'engineer 2', 1200, 1);
INSERT INTO employee (name, position, salary, office_id) VALUES ('Sidr Sidorov', 'engineer 3', 1500, 3);
INSERT INTO employee (name, position, salary, office_id) VALUES ('Sergey Sergeev', 'boss', 1800, 4);

INSERT INTO rates (position, salary) VALUES ('boss', 1800);
INSERT INTO rates (position, salary) VALUES ('engineer 3', 1500);
INSERT INTO rates (position, salary) VALUES ('engineer 2', 1200);
INSERT INTO rates (position, salary) VALUES ('engineer 1', 1000);

