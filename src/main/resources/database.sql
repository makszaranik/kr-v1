DROP TABLE IF EXISTS queueItems;
DROP TABLE IF EXISTS queues;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     username VARCHAR(255) NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     roleType VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS queues (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
                                     creatorId INT NOT NULL,
                                     isBlocked BOOLEAN NOT NULL DEFAULT FALSE,
                                     FOREIGN KEY (creatorId) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS queueItems (
                                          id SERIAL PRIMARY KEY,
                                          queueId INT NOT NULL,
                                          item VARCHAR(255) NOT NULL,
                                          FOREIGN KEY (queueId) REFERENCES queues(id) ON DELETE CASCADE
);


INSERT INTO users (username, password, roleType) VALUES ('Max', 'd1696816bc1a7afe92f1c8787ac222c3', 'USER');
INSERT INTO users (username, password, roleType) VALUES ('Yana', 'f120b1fbce5e71f228b8764c574455da', 'USER');
INSERT INTO users (username, password, roleType) VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', 'USER');

INSERT INTO queues (name, creatorId, isBlocked) VALUES ('IT Support', 1, FALSE);
INSERT INTO queues (name, creatorId, isBlocked) VALUES ('HR Services', 3, FALSE);
INSERT INTO queues (name, creatorId, isBlocked) VALUES ('Facilities', 2, FALSE);

INSERT INTO queueItems (queueId, item) VALUES (1, 'Install new software');
INSERT INTO queueItems (queueId, item) VALUES (1, 'Update server');
INSERT INTO queueItems (queueId, item) VALUES (1, 'Check email issues');
INSERT INTO queueItems (queueId, item) VALUES (2, 'Recruit new developer');
INSERT INTO queueItems (queueId, item) VALUES (2, 'Organize team building');
INSERT INTO queueItems (queueId, item) VALUES (3, 'Repair office AC');
INSERT INTO queueItems (queueId, item) VALUES (3, 'Replace light bulbs');
