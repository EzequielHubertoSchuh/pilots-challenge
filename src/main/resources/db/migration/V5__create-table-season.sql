CREATE SEQUENCE SEQ_SEASON;

CREATE TABLE TB_SEASON(
    SEASONID NUMERIC(20),
    YEAR NUMERIC(20) NOT NULL,
    URL VARCHAR(255)  NOT NULL,
    CONSTRAINT PK_SEASON PRIMARY KEY (SEASONID)
);