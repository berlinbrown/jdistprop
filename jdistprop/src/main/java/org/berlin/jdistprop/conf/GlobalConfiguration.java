/**
 * Copyright (c) 2006-2012 Berlin Brown (berlin dot brown @gmail.com)  All Rights Reserved
 *
 * http://www.opensource.org/licenses/bsd-license.php

 * All rights reserved.

 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:

 * * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * * Neither the name of the Botnode.com (Berlin Brown) nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written permission.

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * Log Parser - analyze log files
 * 1/22, 2012
 * bbrown
 * Contact: Berlin Brown <berlin dot brown at gmail.com>
 */
package org.berlin.jdistprop.conf;

/**********************************************************
 * Example Design Pattern Proof of Concept Swing Application 
 * (Input Text Area, Output Area, Scroll bars, menu options) 
 **********************************************************/

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Global configuration store for DSL scripts.
 * 
 * @author berlin
 * 
 */
public class GlobalConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalConfiguration.class);
        
    public static final String PATH = "org/berlin/jdistprop/resource/jdistprop_system.properties";
    public static final String PATH2 = "src/main/java/org/berlin/jdistprop/resource/jdistprop_system.properties";

    Properties systemPropertiesFromClasspath = new Properties();
    Properties userDynamicScriptPropertiesFromString = new Properties();
    
    String systemApplicationName = "X-LogScan";
    String systemVersion = "X-1.0.0";
    String sessionOutputName = "";
    
    /**
     * Load the system global configuration.
     * 
     * @param propFilename
     * @return
     */
    public GlobalConfiguration load(final String propFilename) {
        final Properties props = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(propFilename);
            if (inputStream == null) {
                LOGGER.info("! Global Conf Invalid at [1] : " + propFilename);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        if (inputStream == null) {
            try {
                inputStream = this.getClass().getClassLoader().getResourceAsStream(PATH2);
                if (inputStream == null) {
                    LOGGER.info("! Global Conf Invalid at [2] : " + PATH2);
                }
            } catch (final Exception e) {
                e.printStackTrace();
            } // End of try catch //
        } // End of if //
        if (inputStream == null) {
            LOGGER.info("Invalid inputStream at Property File");
            throw new IllegalStateException("GlobalConfiguration<1>: property file '" + propFilename
                    + "' not found in the classpath");
        }
        try {
            props.load(inputStream);
            this.systemPropertiesFromClasspath = props;
            this.load(props);
        } catch (IOException e) {
            
            e.printStackTrace();
            LOGGER.error("Error at load properties",  e);
            throw new IllegalStateException("GlobalConfiguration<2>: property file '" + propFilename
                    + "' not found in the classpath, msg=" + e.getMessage() + " stream=" + inputStream);
            
        }
        return this;
    }

    /*
     * --------------------------------------------------------------
     * --------------------------------------------------------------
     * 
     * LOAD FIRST RUN SYSTEM PROPERTIES
     * 
     * --------------------------------------------------------------
     * --------------------------------------------------------------
     */
    
    /**
     * Load the property data.
     * Default load, from system properties.
     * 
     * @param props
     */
    public void load(final Properties props) {

        this.systemApplicationName = props.getProperty("system.applicationName", "None").trim();
        this.systemVersion = props.getProperty("system.version", "None").trim();
        
    } // End of the method //

    /*
     * --------------------------------------------------------------
     * --------------------------------------------------------------
     * 
     * LOAD SECOND RUN DYNAMIC PROPERTIES [2]
     * 
     * --------------------------------------------------------------
     * --------------------------------------------------------------
     */
    
    /**
     * Load property file from dynamic script/string data.
     * 
     * @param props
     * @return
     */
    public Properties loadFromDynamicScript(final Properties props) {        
        if (props == null) {
            return new Properties();
        }
        this.userDynamicScriptPropertiesFromString = props;
        final String tmpSearch = props.getProperty("user.searchTerm");
        return props;
    } // End of method, second load, dynamic

    public String toString() {
        return "[GlobalConfiguration : appName=" + this.systemApplicationName + ", vers=" + systemVersion + " ]";
    }

    /**
     * @return the systemPropertiesFromClasspath
     */
    public Properties getSystemPropertiesFromClasspath() {
        return systemPropertiesFromClasspath;
    }

    /**
     * @return the systemApplicationName
     */
    public String getSystemApplicationName() {
        return systemApplicationName;
    }

    /**
     * @return the systemVersion
     */
    public String getSystemVersion() {
        return systemVersion;
    }

    /**
     * @return the userDynamicScriptPropertiesFromString
     */
    public Properties getUserDynamicScriptPropertiesFromString() {
      return userDynamicScriptPropertiesFromString;
    }

    
} // End of the class //