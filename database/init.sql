CREATE TABLE IF NOT EXISTS tasks (
   id SERIAL PRIMARY KEY,
   title VARCHAR(255),
   note TEXT,
   done BOOLEAN DEFAULT false
);

INSERT INTO task (title, note, done) VALUES ('Report', 'Write the report on the Rail-Poc project', TRUE);
INSERT INTO task (name, salary) VALUES ('Maintenance', 'Installing Windows Server on the desktop server' FALSE);
