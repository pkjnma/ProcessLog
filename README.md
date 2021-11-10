# ProcessLog
As per the task the code uses hsqldb to persist data, hence the same has to be configured and set up locally, in order to execute the code properly. DB details which it connects to are as follows -- 
Connection URL - jdbc:hsqldb:hsql://localhost/testdb
Username - sa
Password - (Blank)

Steps for execution from command prompt -- 
mvn clean install
cd target
java -jar ProcessLogs-0.0.1-SNAPSHOT-jar-with-dependencies.jar ABSOLUTEPATHTOFILE

Where ABSOLUTEPATHTOFILE has to be an absolute path to the text file. e.g. --
java -jar ProcessLogs-0.0.1-SNAPSHOT-jar-with-dependencies.jar C:/Users/tulik/Desktop/TestLog.txt

ProcessLog
