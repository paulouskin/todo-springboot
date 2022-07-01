DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS lists;
CREATE TABLE lists
(
    id VARCHAR(50) NOT NULL,
    title VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE items
(
    id VARCHAR(50) NOT NULL,
    title VARCHAR(200) NOT NULL,
    description VARCHAR(300) NOT NULL,
    status VARCHAR(50) NOT NULL,
    listId VARCHAR(50),
    PRIMARY KEY (id),
    FOREIGN KEY (listId) REFERENCES lists(id)
);