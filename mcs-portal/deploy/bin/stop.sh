#!/bin/bash
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
DEPLOY_CONF_DIR=$DEPLOY_DIR/conf

SERVER_NAME=`sed '/application.name/!d;s/.*=//' conf/application.properties | tr -d '\r'`
SERVER_PIDS=`ps -ef | grep java | grep "$DEPLOY_CONF_DIR" | awk '{print $2}'`

if [ -z "$SERVER_PIDS" ]; then
    echo "ERROR: The $SERVER_NAME does not started!"
    exit 1
fi

if [ -z "$SERVER_NAME" ]; then
    SERVER_NAME=`hostname`
fi

if [ "$1" != "skip" ]; then
    echo 'skip dump...'
fi

echo -e "Stopping the $SERVER_NAME-$SERVER_PIDS ...\c"
for PID in $SERVER_PIDS ; do
    kill $PID > /dev/null 2>&1
done

COUNT=0
while [ $COUNT -lt 1 ]; do    
    echo -e ".\c"
    sleep 1
    COUNT=1
    for PID in $SERVER_PIDS ; do
        PID_EXIST=`ps -f -p $PID | grep java`
        if [ -n "$PID_EXIST" ]; then
            COUNT=0
            break
        fi
    done
done

echo "OK!"
echo "PID: $SERVER_PIDS"
