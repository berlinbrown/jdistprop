package org.berlin.jdistprop.init.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainVerifyDatabase {
    
    public static void queryTable() throws Exception {
        final Connection conn = DriverManager.getConnection("jdbc:h2:./conf/db/jdistpropdb", "jdist", "jdist");
        // add application code here        
        try {            
            final String query = "select KEY, VALUE from jdistprop;";            
            final Statement stmt = conn.createStatement();
            final ResultSet rs = stmt.executeQuery(query);
            System.out.println("---- PROPERTY DB LISTING ----");
            while (rs.next()) {
                System.out.print(rs.getString("KEY"));
                System.out.print("=");
                System.out.print(rs.getString("VALUE"));
                System.out.println();
            }
            System.out.println("---- END/PROPERTY DB LISTING ----");
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
        // add application code here        
        try {                     
            queryTable();
            System.out.println("Done");
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }                      
    } // End of the function main //    
} // End of the class //
