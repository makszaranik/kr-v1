DROP TABLE IF EXISTS queueItems;
DROP TABLE IF EXISTS queues;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(255) NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     roleType VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS queues (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
                                     creatorId INT NOT NULL,
                                     FOREIGN KEY (creatorId) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS queueItems (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     queueId INT NOT NULL,
                                     item VARCHAR(255) NOT NULL,
                                     FOREIGN KEY (queueId) REFERENCES queues(id)
);

INSERT INTO users (username, password, roleType) VALUES ('Max', 'max123', 'USER');
INSERT INTO users (username, password, roleType) VALUES ('Yana', 'yana123', 'USER');
INSERT INTO users (username, password, roleType) VALUES ('admin', 'admin', 'USER');

INSERT INTO queues (name, creatorId) VALUES ('IT Support', 1);
INSERT INTO queues (name, creatorId) VALUES ('HR Services', 3);
INSERT INTO queues (name, creatorId) VALUES ('Facilities', 2);

INSERT INTO queueItems (queueId, item) VALUES (1, 'Install new software');
INSERT INTO queueItems (queueId, item) VALUES (1, 'Update server');
INSERT INTO queueItems (queueId, item) VALUES (1, 'Check email issues');
INSERT INTO queueItems (queueId, item) VALUES (2, 'Recruit new developer');
INSERT INTO queueItems (queueId, item) VALUES (2, 'Organize team building');
INSERT INTO queueItems (queueId, item) VALUES (3, 'Repair office AC');
INSERT INTO queueItems (queueId, item) VALUES (3, 'Replace light bulbs');
