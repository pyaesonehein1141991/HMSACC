CREATE TABLE BRANCH(
ID CHAR(36) NOT NULL PRIMARY KEY,
NAME VARCHAR(225) NOT NULL,
CODE VARCHAR(225) NOT NULL,
DESCRIPTION VARCHAR(225) NOT NULL,
VERSION INT,
CREATEDDATE DATETIME,
CREATEDUSERID CHAR(36),
UPDATEDDATE DATETIME, 
UPDATEDUSERID CHAR(36)
)

SELECT * FROM BRANCH