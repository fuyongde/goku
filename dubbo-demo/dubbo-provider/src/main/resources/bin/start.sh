#!/bin/bash
APP=dubbo-provider
JAR=$APP.jar

# 绝对路径
FULL_PATH=$(cd "$(dirname "$0")/../"; pwd)
echo "The application absolute path is $FULL_PATH"

# 获取服务器总内存
MEM_TOTAL=`cat /proc/meminfo |grep MemTotal|awk '{printf "%d", $2/1024 }'`
echo "Total memory is $MEM_TOTAL"

# 远程调试配置
DEBUG_PORT=9601
JAVA_DEBUG_OPTS=""
if [ "$1" = "dev" ]; then
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=$DEBUG_PORT,server=y,suspend=n"
fi

# JMX配置
IP=`/sbin/ifconfig -a|grep inet|grep -v 127.0.0.1|grep -v inet6 | awk '{print $2}' | tr -d "addr:"`
JMX_PORT=9602
JAVA_JMX_OPTS=""
if [ "$1" = "dev" ]; then
    JAVA_JMX_OPTS=" -Djava.rmi.server.hostname=$IP -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=$JMX_PORT -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false"
fi

# JVM配置
JAVA_MEM_OPTS=" -server -Xmx128m -Xms128m -XX:+UseG1GC"

# ENV配置
ALL_ENV=("local" "dev" "test" "pre" "uat" "product")
if [ ! -n "$1" ] ;then
    echo "you have not input a env!"
    echo ${ALL_ENV[@]}
    exit 1
fi

ENV=$1
if echo "${ALL_ENV[@]}" | grep -v "$ENV" &>/dev/null; then
    echo "$1 is not in env"
    echo ${ALL_ENV[@]}
    exit 1
fi

# out put banner
function banner(){
    echo ""
    echo "--------------------------------------------------------------------------------------------------"
    echo "$1"
    echo "--------------------------------------------------------------------------------------------------"
    echo ""
}

function isRunning(){
    if [ -f $FULL_PATH/application.pid ];then
        num=`xargs -a $FULL_PATH/application.pid ps -p | wc -l`
           if [ $num -eq 2 ]; then
            return 1;
        else
            return 0;
        fi
    else
        return 0
    fi
}

function doStart(){
    rm -f $FULL_PATH/application.pid
    nohup java -jar -Denv=$ENV $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS $FULL_PATH/$JAR --spring.profiles.active=$ENV 1>/dev/null 2>$FULL_PATH/error.log &
    for i in $(seq 10)
    do
        if [ -f $FULL_PATH/application.pid ];then
            banner "$APP started, PID is : "`cat $FULL_PATH/application.pid`
            exit 0;
        else
            echo -e ".\c"
        fi
        sleep 1
    done
    banner "Start $APP fail"
}

isRunning

if test $? -eq 0;then
    banner "Starting $APP"
    doStart
else
    banner "$APP already started..pid: "`cat $FULL_PATH/application.pid`
fi