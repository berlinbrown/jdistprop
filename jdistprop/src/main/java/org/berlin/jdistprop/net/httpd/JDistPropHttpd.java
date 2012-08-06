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
 * 
 * http://code.google.com/p/jdistprop/
 * https://github.com/berlinbrown   
 * **********************************************
 */
package org.berlin.jdistprop.net.httpd;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.berlin.jdistprop.servlet.DefaultQueryServlet;
import org.berlin.jdistprop.servlet.DefaultServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a standard Java class. 
 */
public class JDistPropHttpd extends NanoHTTPD {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDistPropHttpd.class);    
    public static final String MEM = "freeMemory=%.2fM\ntotal=%.2fM\nmaxMemory=%.2fM";
    
    private final String objectServerInstance;        
    private final ApplicationWebData data = new ApplicationWebData();
    
    /**
     * Initialize servlets.
     */
    private final WebServlet defaultServlet = new DefaultServlet(this, this.data);
    private final WebServlet queryServlet = new DefaultQueryServlet(this, this.data);
    
    /**
     * Constructor for httpd server.
     */
    public JDistPropHttpd(final int port, final File wwwroot) throws IOException {
        super(port, wwwroot);
        this.objectServerInstance = String.valueOf(super.hashCode());       
    }    
    
    @Override
    public Response serve(final String uri, final String method, final Properties header, final Properties parms, final Properties files) {        
        if (uri != null && uri.toLowerCase().contains("servlet")) {
            LOGGER.trace("Custom HTTPD request : " + method + " '" + uri + "' ");                        
            // Example: if (parms.getProperty("username") == null)            
            if (uri.contains("/file/")) {
                if ("GET".equalsIgnoreCase(method )) {
                    return this.defaultServlet.doGet(uri, method, header, parms);
                } else {
                    return this.defaultServlet.doPost(uri, method, header, parms);
                } // End of the if - else //
            } else if (uri.contains("/db/")) {
                if ("GET".equalsIgnoreCase(method )) {
                    return this.queryServlet.doGet(uri, method, header, parms);
                } else {
                    return this.queryServlet.doPost(uri, method, header, parms);
                } // End of the if - else //
            } else {
                return mem();
            } // End of if check URI for action //
        } else {
            return mem();            
        } // End of the if - else //
    } // End of method //
           
    protected NanoHTTPD.Response mem() {
        final double mb = 1024.0 * 1024;
        final double free = Runtime.getRuntime().freeMemory() / mb;
        final double total = Runtime.getRuntime().totalMemory() / mb;
        final double max = Runtime.getRuntime().maxMemory() / mb;
        final String fmt = String.format(MEM, free, total, max);            
        return new NanoHTTPD.Response(HTTP_OK, MIME_PLAINTEXT, fmt);
    }
    
    public ApplicationWebData getData() {
        synchronized (data) {
            return data;
        }
    } // End of the method //
    
} // End of the class //I
