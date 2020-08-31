package com.stratio.qa.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class JDBCConnection {
    private static final Logger logger = LoggerFactory.getLogger(JDBCConnection.class);

    private static Connection myConnection = null;

    public static Connection getConnection() {
        return myConnection;
    }

    public static void setConnection(Connection c) {
        myConnection = c;
    }

    public static void closeConnection() {
        if (myConnection != null) {
            try {
                myConnection.close();
            } catch (Exception e) {
                logger.warn("Error closing JDBC connection", e);
            }
        }
        myConnection = null;
    }
}
