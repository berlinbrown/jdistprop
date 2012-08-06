echo %~dp0
cd /d %~dp0/../
@java -cp "bin/jdistprop.jar;lib/slf4j-api-1.5.8.jar;lib/slf4j-log4j12-1.5.8.jar;lib/commons-logging-1.0.4.jar;lib/log4j-1.2.14.jar" org.berlin.jdistprop.application.MainStart %*
@if errorlevel 1 pause