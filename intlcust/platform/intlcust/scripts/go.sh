#!/bin/bash

source /root/bamboo-vars.conf
ACTIVE_PROFILE="$(echo $bamboo_deployment_env | tr '[A-Z]' '[a-z]')";

echo 'current deployment environment is :'
echo ${ACTIVE_PROFILE}

cd `dirname $0`

. /etc/profile.d/context.sh

set -x 
exec 1>>/tmp/out 2>&1

who 
whoami 

JAVA_HOME=/usr/java/jdk1.8.0_40
JAVA_OPTS="-Xmx256M"

if [ -z "$DEBUG" ] ; then
  JAVA_OPTS+=" -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -Djava.compiler=NONE -Xnoagent"
fi

DATASOURCE_URL="jdbc:mysql://${intlcust_database_IntlcustDatabaseEndpointAddress}:${intlcust_database_IntlcustDatabaseEndpointPort}/intlcustdb?autoReconnect=true"

JAVA_OPTS+=" -Dspring.datasource.url=${DATASOURCE_URL}
-Dspring.datasource.username=${intlcust_database_IntlcustDatabaseMasterUsername}
-Dspring.datasource.password=IntlCustQF2017"

echo 'DATASOURCE_URL: '
echo ${DATASOURCE_URL}

echo 'intlcust_database_IntlcustDatabaseEndpointAddress :'
echo ${intlcust_database_IntlcustDatabaseEndpointAddress}

echo 'intlcust_database_IntlcustDatabaseMasterUsername :'
echo  ${intlcust_database_IntlcustDatabaseMasterUsername}

echo 'intlcust_database_IntlcustDatabaseMasterUserPassword :'
echo ${intlcust_database_IntlcustDatabaseMasterUserPassword}

echo 'current deployment environment is :'
echo ${ACTIVE_PReOFILE}

nohup $JAVA_HOME/bin/java $JAVA_OPTS -jar `find . -maxdepth 1 -name intlcust.jar` &

echo 'Final after :'
echo 'pwd :'
echo `pwd`

disown

exit 0 
