#####################################################################
 README - jdistprop
 http://code.google.com/p/jdistprop/
#####################################################################

#####################################################################
# Overview
#####################################################################

 This project supports distributed property files for easy loading with Java projects.
 
 This project is a solution to this problem:
 
 In a J2EE environment, we are normally used to storing text in a property/resource file. And that property file is associated with some view HTML markup file. 
 E.g. if your label 'First Name' changes to 'Full Name' on a HTML page, you could use the property to make that update.

 firstName=First Name
 someOtherData=This is the data to display on screen, from property file
 
 If you are in an environment, where it is difficult to update those property files on a regular basis, what architecture are developers using to change text/label content 
 that would normally reside in a property file? Or let's say you need to change that content before re-deploying a property file change. 
 One solution is to store that in a database? Are developers using memcache-db? Is that usually used for caching solutions?

 Would you use a solution outside of the java framework? Like a key/value datastore? memcachedb?
  
 Author: Berlin Brown (berlin dot brown at gmail.com)
 
 http://code.google.com/p/jdistprop/
 
#####################################################################
# Operations and Behaviors
#####################################################################

 http://localhost:7191/servlet/file/default.properties ==> http://localhost:7191/servlet/<action-load-file>/<filename from ./conf>.properties 

#####################################################################
# Backing Technology:
#####################################################################
 
 * NanoHTTPD
 * H2 databse engine : http://www.h2database.com/html/main.html (h2-1.3.167.jar/h2-2011-12-30)
      http://www.h2database.com/html/cheatSheet.html
#####################################################################
# End of File 
##################################################################### 