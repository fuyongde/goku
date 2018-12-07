#!/bin/bash
APP=dubbo-provider
JAR=$APP.jar

# 绝对路径
FULL_PATH=$(cd "$(dirname "$0")/../"; pwd)
echo "The application absolute path is $FULL_PATH"

PID=`ps -ef | grep $JAR | grep -v grep | awk '{print $2}'`

function banner(){
    echo ""
    echo "--------------------------------------------------------------------------------------------------"
    echo "$1"
    echo "--------------------------------------------------------------------------------------------------"
    echo ""
}

function isRunning(){
    if [ -n "$PID" ]; then
        return 1
    else
        return 0
    fi
}

function doStop(){
    echo "Stopping $APP..";
    kill $PID
    for i in $(seq 15)
    do
        if [ -f $FULL_PATH/application.pid ];then
            echo -e ".\c"
        else
            banner "$APP stopped."
            exit 0
        fi
        sleep 1
    done
    banner "Stop $APP failed ! please check. PID is : $PID"
    kill -9 $PID
    banner "Force Stop $APP . PID is : $PID"
}

isRunning

if test $? -eq 0; then
    banner "$APP already stopped. "
else
    doStop
fi