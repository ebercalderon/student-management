CREATE TABLE IF NOT EXISTS student (
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    status VARCHAR(40) NOT NULL,
    age INT
);
