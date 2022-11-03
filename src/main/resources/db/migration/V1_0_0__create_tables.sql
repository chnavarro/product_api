USE [product_api_db];
GO

--You need to check if the table exists
IF NOT EXISTS (SELECT * FROM SYSOBJECTS WHERE name='USER' and xtype='U')
    BEGIN
        CREATE TABLE [USER]
        (
            USER_NAME nvarchar(10) NOT NULL,
            USER_FULL_NAME nvarchar(100) NOT NULL,
            USER_PASSWORD nvarchar(512) NOT NULL,
            USER_CREATE_DATE datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
            USER_LAST_LOGIN datetime,
            USER_ROLE nvarchar(5) DEFAULT 'USER',
            USER_STATUS BIT DEFAULT 1,
            CONSTRAINT PK_USER_ID PRIMARY KEY (USER_NAME)
            )
    END;
GO
