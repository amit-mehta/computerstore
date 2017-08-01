#!/bin/bash

source /root/bamboo-vars.conf
source /root/context
 
set -x 
#exec 1>>/tmp/out 2>&1
echo "****************************address-book INSTALL SCRIPT*********************************"
who am i
PAYLOAD=/root/payload

find $PAYLOAD

echo "-------- bamboo plan keys are --------"
echo ${bamboo_planKey}
echo ${bamboo_pipeline_name}
echo ${bamboo_shortPlanName}
echo ${bamboo_buildNumber}


DEPLOYMENT_ENVIRONMENT=${bamboo_deployment_env}

echo $DEPLOYMENT_ENVIRONMENT
#change to splunk index a245 config

case "$DEPLOYMENT_ENVIRONMENT" in

'NonProduction')    
	sed -i -e "s/%SPLUNK_INDEX%/a245_freight_nonprod/g" /root/payload/inputs.conf
    ;;
'Production')
    sed -i -e "s/%SPLUNK_INDEX%/a245_freight_prod/g" /root/payload/inputs.conf
    ;;

'*')
    echo "Could not replace splunk input config"
    exit 1;
    ;;
esac

#if [ `find /opt/paxpet -maxdepth 1 -name paxpet-app\*.jar | grep paxpet-app` ] ; then

/usr/local/bin/aws s3 cp s3://pipeline-artefact-store/common/jdk/jdk-8u40-linux-x64.rpm .
NAME=intlcust
TARGET=/opt/$NAMEƒƒƒ
rpm -i jdk-8u40-linux-x64.rpm
sudo yum -y install nc

cp -r ${PAYLOAD}/app $TARGET
cp -r ${PAYLOAD}/go.sh $TARGET

SPLUNK_DIR=/opt/splunkforwarder/etc/apps/$NAME/default/

mkdir -p $SPLUNK_DIR
cp -v ${PAYLOAD}/inputs.conf $SPLUNK_DIR

chmod u+srx $TARGET
chmod u+srx $TARGET/*.sh

#cat ${PAYLOAD}/authorized_keys >> /root/.ssh/authorized_keys

ls -lad $TARGET
ls -la $TARGET/*


crontab -l >> /tmp/cron
echo "@reboot /bin/bash -x $TARGET/go.sh" >> /tmp/cron
crontab /tmp/cron

echo "**********************************INSTALLED*****************************************"
exit 0 
