CREATE TABLE TASKLIST (
    TASKID INT PRIMARY KEY,
    TASKNAME NVARCHAR(100),
    TASKDESC NVARCHAR(255),
    CREATEDATE NVARCHAR(50),
	DEADLINE NVARCHAR(50)
);

CREATE TABLE ACCOUNT (
    USERNAME VARCHAR(30) PRIMARY KEY,
	PASS CHAR(30),
	CONFIRM CHAR(30)
);

SELECT * FROM TASKLIST