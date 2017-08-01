#!/bin/bash

echo "Staring application build for intlcust"

set -x

echo $SKIP_BINARIES

cp -r ${COMPONENT_DIR}/scripts/* ${PAYLOAD_DIR}

PAYLOAD_APP_DIR=${PAYLOAD_DIR}/app
mkdir -p $PAYLOAD_APP_DIR

TARGET_DIR=${APP_DIR}/target/

cp -r ${TARGET_DIR}/*.jar ${PAYLOAD_APP_DIR}

find ${PAYLOAD_DIR}

