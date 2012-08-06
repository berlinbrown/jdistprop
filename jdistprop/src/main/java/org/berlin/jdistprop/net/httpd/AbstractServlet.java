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
package org.berlin.jdistprop.net.httpd;

import java.util.Properties;

import org.berlin.jdistprop.net.httpd.NanoHTTPD.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a standard Java class.
 */
public abstract class AbstractServlet implements WebServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractServlet.class);  
    private static final String NL = "\n";
    
    private final JDistPropHttpd parent;
    private final ApplicationWebData data;
    
    public AbstractServlet(final JDistPropHttpd parent, final ApplicationWebData data) {
        this.parent = parent;
        this.data = data;
    }
    
    @Override
    public Response doGet(String uri, String method, Properties header, Properties parms) {     
        return this.doPost(uri, method, header, parms);
    }
        
    @Override
    public JDistPropHttpd parent() {
        return this.parent;
    }

    @Override
    public ApplicationWebData data() {        
        return this.data;
    }
    
} // End of the class //