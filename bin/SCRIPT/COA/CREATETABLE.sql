CREATE TABLE COA(
ID CHAR(36) NOT NULL PRIMARY KEY,
ACNAME VARCHAR(225) NOT NULL,
ACCODE VARCHAR(225) NOT NULL,
ACTYPE VARCHAR(225) NOT NULL,
PDATE DATETIME,
IBSBACODE VARCHAR(225),
PARENTID CHAR(36),
VERSION INT,
CREATEDDATE DATETIME,
CREATEDUSERID CHAR(36),
UPDATEDDATE DATETIME, 
UPDATEDUSERID CHAR(36)
FOREIGN KEY (PARENTID) REFERENCES COA(ID)
)
