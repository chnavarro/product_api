--Creates tables
CREATE TABLE API_USER
(
    USER_NAME        VARCHAR(10)  NOT NULL,
    USER_FULL_NAME   VARCHAR(100) NOT NULL,
    USER_PASSWORD    VARCHAR(512) NOT NULL,
    USER_CREATE_DATE TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    USER_LAST_LOGIN  TIMESTAMP(3),
    USER_ROLE        VARCHAR(5)            DEFAULT 'USER',
    USER_STATUS      BOOLEAN               DEFAULT TRUE,
    CONSTRAINT PK_USER_ID PRIMARY KEY (USER_NAME)
);


CREATE TABLE CATEGORY
(
    CAT_ID          BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,
    CAT_NAME        VARCHAR(50)                                                       NOT NULL,
    CAT_CREATE_DATE TIMESTAMP(3)                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CAT_LAST_UPDATE TIMESTAMP(3),
    CAT_STATUS      BOOLEAN                                                                    DEFAULT TRUE,
    CONSTRAINT PK_CATEGORY_ID PRIMARY KEY (CAT_ID)
);


CREATE TABLE PRODUCT
(
    PROD_ID          BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,
    PROD_NAME        VARCHAR(50)                                                       NOT NULL,
    PROD_COST        NUMERIC(10, 2),
    PROD_PRICE       NUMERIC(10, 2),
    PROD_TAGS        VARCHAR(100),
    PROD_CREATE_DATE TIMESTAMP(3)                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PROD_LAST_UPDATE TIMESTAMP(3),
    PROD_STATUS      BOOLEAN                                                                    DEFAULT TRUE,
    CAT_ID           BIGINT                                                            NULL,
    CONSTRAINT PK_PRODUCT_ID PRIMARY KEY (PROD_ID),
    CONSTRAINT FK_PRODUCT_CATEGORY FOREIGN KEY (CAT_ID)
        REFERENCES CATEGORY (CAT_ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);