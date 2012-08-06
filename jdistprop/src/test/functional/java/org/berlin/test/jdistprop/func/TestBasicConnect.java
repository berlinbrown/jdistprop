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
package org.berlin.test.jdistprop.func;

import java.net.URL;
import java.util.Properties;

public class TestBasicConnect {

    public static void main(final String [] args) throws Exception {
        System.out.println("Running - Test - Basic Connect");        
        // Iterate and time :
        final int requests = 2000;
        final long tstart = System.currentTimeMillis();
        for (int i = 0; i < requests; i++) {
            final Properties prop = new Properties() ;
            final URL url = new URL("http://localhost:7191/servlet/file/default");        
            prop.load(url.openStream());                   
            final StringBuffer buf = new StringBuffer();
            for (final Object key : prop.keySet()) {
                buf.append(key).append("=").append(prop.getProperty(String.valueOf(key)));
                buf.append('\n');
            } // End of the for //
            buf.toString();
        } // End of the for //
        final long tdiff = System.currentTimeMillis() - tstart;
        System.out.println("Number of requests=" + requests + " diff=" + tdiff + " ms");
        System.out.println("Running - Test - Basic Connect - End");
    } // End of the method //
    
} // End of the class //
