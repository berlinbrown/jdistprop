package org.berlin.test.jdistprop.func;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestH2Database {

    public static void main(String[] args) throws Exception {
        System.out.println("Running");
        
        // start the TCP Server (9092)
        //Server server = Server.createTcpServer(args).start();
        
        Class.forName("org.h2.Driver");
        final Connection conn = DriverManager.getConnection("jdbc:h2:./conf/db/jdistpropdb", "sa", "");
        // add application code here        
        try {
            final String createTable = "create table jdistproperties" +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "KEY VARCHAR(60) UNIQUE," +
                    "VALUE VARCHAR(255));";
            final Statement stmt = conn.createStatement();
            stmt.executeUpdate(createTable);
            stmt.close();
            conn.close();
            System.out.println("Done");

        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }              
        // stop the TCP Server
        //server.stop();
    }

} // End of the class //
