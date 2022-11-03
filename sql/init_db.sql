USE [master];
GO


IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = 'product_api_db')
    BEGIN
        CREATE DATABASE [product_api_db]
    END;
GO


Use product_api_db;
GO

IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = N'apiusr')
    BEGIN
        CREATE LOGIN apiusr
            WITH PASSWORD    = N'strongPassword',
            DEFAULT_DATABASE = product_api_db,
            CHECK_POLICY     = OFF,
            CHECK_EXPIRATION = OFF;
        EXEC sp_addsrvrolemember
             @loginame = N'apiusr',
             @rolename = N'sysadmin';
    END;
GO



