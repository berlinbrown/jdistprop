package org.berlin.jdistprop.init.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainSetupDatabase {
    
    public static void queryTable() throws Exception {
        final Connection conn = DriverManager.getConnection("jdbc:h2:./conf/db/jdistpropdb", "jdist", "jdist");
        // add application code here        
        try {
            
            final String query = "select KEY, VALUE from jdistprop;";            
            final Statement stmt = conn.createStatement();
            final ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("KEY"));
                System.out.println(rs.getString("VALUE"));                
            }
            stmt.close();
            conn.close();
            System.out.println("Done");
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    public static void insertTable() throws Exception {
        final Connection conn = DriverManager.getConnection("jdbc:h2:./conf/db/jdistpropdb", "jdist", "jdist");
        // add application code here        
        try {
            final String insert = "insert into jdistprop(KEY, VALUE) values('jdistprop.version','0.0');";            
            final Statement stmt = conn.createStatement();
            stmt.executeUpdate(insert);
            stmt.close();
            conn.close();
            System.out.println("Done");
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("Running");        
        Class.forName("org.h2.Driver");
        final Connection conn = DriverManager.getConnection("jdbc:h2:./conf/db/jdistpropdb", "jdist", "jdist");
        // add application code here        
        try {
            final String createTable = "create table jdistprop" +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT, " +                    
                    "KEY VARCHAR(60) UNIQUE," +
                    "VALUE VARCHAR(255));";
            final Statement stmt = conn.createStatement();
            stmt.executeUpdate(createTable);
            stmt.close();
            conn.close();
            // init another connection //            
            insertTable();         
            queryTable();
            System.out.println("Done");
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }                      
    } // End of the function main //    
} // End of the class //
