FROM tomcat:10-jdk21-openjdk
COPY /target/root.war /usr/local/tomcat/webapps/root.war