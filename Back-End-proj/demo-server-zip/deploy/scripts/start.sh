#!/bin/sh

#set($pidId = "$"+"!")
cd ${app_name}
pidFile="${app_name}.pid"
pidTmpFile="${app_name}.tmp.pid"

if [ -f "$pidFile" ]
then
    rm -f $pidFile
fi

jarName=$(ls *.jar)
nohup java ${jvm_args} ${log4j2_args} -jar -DAppPID $jarName jfile=config/application.properties >/dev/null  &

echo $pidId > ${pidTmpFile}