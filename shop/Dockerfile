FROM jboss/wildfly:11.0.0.Final

ENV MARIADB_CONNECTOR_VERSION 1.5.4

USER root

# Install DB connector
COPY docker/mariadb-module.xml $JBOSS_HOME/modules/system/layers/base/org/mariadb/jdbc/main/module.xml
COPY docker/mariadb-xa-module.xml $JBOSS_HOME/modules/system/layers/base/org/mariadb/jdbc/xaMaria/module.xml
RUN cd $JBOSS_HOME/modules/system/layers/base/org/mariadb/jdbc/main/ \
	&& curl -o mariadb-java-client.jar http://central.maven.org/maven2/org/mariadb/jdbc/mariadb-java-client/$MARIADB_CONNECTOR_VERSION/mariadb-java-client-$MARIADB_CONNECTOR_VERSION.jar
RUN cp $JBOSS_HOME/modules/system/layers/base/org/mariadb/jdbc/main/mariadb-java-client.jar $JBOSS_HOME/modules/system/layers/base/org/mariadb/jdbc/xaMaria/

COPY docker/standalone.xml $JBOSS_HOME/standalone/configuration/