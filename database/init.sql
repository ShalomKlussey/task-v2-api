CREATE TABLE IF NOT EXISTS task (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    salary DOUBLE PRECISION
);

INSERT INTO employee (name, salary) VALUES ('John Doe', 50000.0);
INSERT INTO employee (name, salary) VALUES ('Jane Smith', 60000.0);
