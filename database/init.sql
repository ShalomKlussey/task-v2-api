CREATE TABLE IF NOT EXISTS task (
   id BIGINT NOT NULL,
   title VARCHAR(255),
   note VARCHAR(255),
   done BOOLEAN NOT NULL,
   CONSTRAINT pk_task PRIMARY KEY (id)
);

INSERT INTO task (title, note, done) VALUES ('Report', 'Write the report on the Rail-Poc project', TRUE);
INSERT INTO task (name, salary) VALUES ('Maintenance', 'Installing Windows Server on the desktop server' FALSE);
