package com.cowards.onlyarts.utils;

import com.cowards.onlyarts.core.DBContext;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DBContextTest {
    
    public DBContextTest() {
    }

    @org.junit.jupiter.api.Test
    public void testGetConnectionSuccessfully() throws Exception {
        System.out.println("getConnection");
        Connection result = DBContext.getInstance();
        String driverName = "Microsoft JDBC Driver 11.2 for SQL Server" ;
        String driverVersion = "11.2.3.0";
        DatabaseMetaData dm = (DatabaseMetaData) result.getMetaData();
        System.out.println(dm.getDriverName() + " | " + dm.getDriverVersion());
        assertEquals(driverName, dm.getDriverName());
        assertEquals(driverVersion, dm.getDriverVersion());
    }
}
