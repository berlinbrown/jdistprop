package org.berlin.jdistprop.init.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MainBasicInsertProp {
    
    public static void queryTable() throws Exception {
        final Connection conn = DriverManager.getConnection("jdbc:h2:./conf/db/jdistpropdb", "jdist", "jdist");
        // add application code here        
        try {
            
            final String query = "select KEY, VALUE from jdistprop;";            
            final Statement stmt = conn.createStatement();
            final ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getString("KEY"));
                System.out.print("=");
                System.out.print(rs.getString("VALUE"));
                System.out.println();
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
            final Properties prop = new Properties();        
            try {                           
                // load a properties file
                prop.load(new FileInputStream("./conf/file/tmp/staging_load.properties"));
                for (final Object key : prop.keySet()) {                    
                    final String val = prop.getProperty(String.valueOf(key));                    
                    final String insert = "insert into jdistprop(KEY, VALUE) values('"+key+"','"+val+"');";
                    final Statement stmt = conn.createStatement();
                    stmt.executeUpdate(insert);
                    stmt.close();
                } // End of the for //
            } catch (IOException ex) {
                ex.printStackTrace();
            } // End of the try - catch //s                                              
            conn.close();
            System.out.println("Done");
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    } // End of the method //
    
    public static void main(String[] args) throws Exception {
        System.out.println("Running");        
        Class.forName("org.h2.Driver");
        final Connection conn = DriverManager.getConnection("jdbc:h2:./conf/db/jdistpropdb", "jdist", "jdist");
        // add application code here        
        try {                     
            insertTable();         
            queryTable();
            System.out.println("Done");
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }                      
    } // End of the function main //    
} // End of the class //
