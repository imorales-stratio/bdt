/*
 * Copyright (C) 2014 Stratio (http://stratio.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
