FROM jetty
ADD build/libs/tema-06.war /var/lib/jetty/webapps/tema-06.war
EXPOSE 8089