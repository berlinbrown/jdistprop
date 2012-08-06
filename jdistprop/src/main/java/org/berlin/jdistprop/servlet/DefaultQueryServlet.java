/**
 * Copyright (c) 2006-2012 Berlin Brown. All Rights Reserved
 * 
 * http://www.opensource.org/licenses/bsd-license.php
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. * Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. * Neither the name of the Botnode.com (Berlin Brown)
 * nor the names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * **********************************************
 * File : 
 * 
 * Date: 7/20/2012 
 * 
 * bbrown Contact: Berlin Brown
 * <berlin dot brown at gmail.com>
 * 
 * description: Distributed Property Files With Java (jdistprop)
 * keywords: design patterns, java, distributed, property files
 *  
 * URLs:
 * http://code.google.com/p/jdistprop/
 * https://github.com/berlinbrown   
 * **********************************************
 */
package org.berlin.jdistprop.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

import org.berlin.jdistprop.net.httpd.AbstractServlet;
import org.berlin.jdistprop.net.httpd.ApplicationWebData;
import org.berlin.jdistprop.net.httpd.JDistPropHttpd;
import org.berlin.jdistprop.net.httpd.NanoHTTPD;
import org.berlin.jdistprop.net.httpd.NanoHTTPD.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a standard Java class. 
 */
public class DefaultQueryServlet extends AbstractServlet {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultQueryServlet.class);  
    
    private Connection conn;
    
    public DefaultQueryServlet(final JDistPropHttpd a, final ApplicationWebData b) {
        super(a, b);
        try {
            conn = DriverManager.getConnection("jdbc:h2:./conf/db/jdistpropdb", "jdist", "jdist");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    @Override
    public Response doPost(final String uri, final String method, final Properties header, final Properties parms) { 
        final NanoHTTPD web = this.parent();        
        final Date now = new Date();                                                            
        final StringBuffer buf = new StringBuffer();                       
        try {                        
            // add application code here        
            try {            
                final String query = "select KEY, VALUE from jdistprop;";            
                final Statement stmt = conn.createStatement();
                final ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    final String key = rs.getString("KEY");
                    final String val = rs.getString("VALUE");
                    buf.append(key).append("=").append(val);
                    buf.append('\n');                    
                }
                stmt.close();                
            } catch(SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            } // End of the try - catch //            
        } catch (Exception ex) {
            ex.printStackTrace();
        } // End of the try - catch //s               
        return web.new Response(NanoHTTPD.HTTP_OK, NanoHTTPD.MIME_PLAINTEXT, buf.toString());
    } // End of the method //
    
} 