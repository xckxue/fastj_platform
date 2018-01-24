#!/bin/bash
cd `dirname $0`
BIN_DIR=`pwd`
STDOUT_FILE=$BIN_DIR/stdout.log
cd ..
DEPLOY_DIR=`pwd`
DEPLOY_CONF_DIR=$DEPLOY_DIR/conf
DEPLOY_LIBS_DIR=$DEPLOY_DIR/lib/*
DEPLOY_Module_DIR=$DEPLOY_DIR/lib_module/*

SERVER_NAME=`sed '/application.name/!d;s/.*=//' conf/application.properties | tr -d '\r'`
SERVER_PORT=`sed '/server.port/!d;s/.*=//' conf/application.properties | tr -d '\r'`
SERVER_PIDS=`ps -ef | grep java | grep "$DEPLOY_CONF_DIR" | awk '{print $2}'`

if [ -z "$SERVER_NAME" ]; then
    SERVER_NAME=`hostname`
fi

if [ -n "$SERVER_PIDS" ]; then
    echo "ERROR: The $SERVER_NAME already started!"
    echo "PID: $SERVER_PIDS"
    exit 1
fi

if [ -n "$SERVER_PORT" ]; then
    SERVER_PORT_COUNT=`netstat -tln | grep "$SERVER_PORT" | wc -l`
    if [ "$SERVER_PORT_COUNT" -gt 0 ]; then
        echo "ERROR: The $SERVER_NAME port $SERVER_PORT already used!"
        exit 1
    fi
fi

LOGS_DIR=${env.logback.path}
if [ ! -d "$LOGS_DIR" ]; then
    mkdir "$LOGS_DIR"
fi

Module_DIR=$DEPLOY_DIR/lib_module
if [ ! -d "$Module_DIR" ]; then
    mkdir "$Module_DIR"
fi

#STDOUT_FILE=$LOGS_DIR/stdout.log

#ready opts...
#JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "
#JAVA_DEBUG_OPTS=""
#if [ "$1" = "debug" ]; then
#    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
#fi
#JAVA_JMX_OPTS=""
#if [ "$1" = "jmx" ]; then
#    JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
#fi
JAVA_MEM_OPTS=""
BITS=`java -version 2>&1 | grep -i 64-bit`
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS=" -server -Xmx${env.xmx} -Xms${env.xms} -XX:PermSize=128m -Xmn256m -Xss256k
    -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection
    -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
else
    JAVA_MEM_OPTS=" -server -Xms${env.xmx} -Xmx${env.xms} -XX:PermSize=128m -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi

#start service...
BOOT_CLASS="com.upotv.Application"
echo -e "Starting the $SERVER_NAME ...\c"
nohup java $JAVA_MEM_OPTS -cp $DEPLOY_CONF_DIR:$DEPLOY_LIBS_DIR:$DEPLOY_Module_DIR $BOOT_CLASS > $STDOUT_FILE 2>&1 &
echo "OK!"
SERVER_PIDS=`ps -ef | grep java | grep "$DEPLOY_DIR"  | awk '{print $2}'`
echo "PID: $SERVER_PIDS"
echo "STDOUT: $STDOUT_FILE"