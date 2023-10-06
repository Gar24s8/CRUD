CREATE TABLE IF NOT EXISTS public."servletTask"
(
    id       SERIAL PRIMARY KEY NOT NULL,
    name     VARCHAR(100)       NOT NULL,
    position VARCHAR(100),
    salary   VARCHAR(20)
);