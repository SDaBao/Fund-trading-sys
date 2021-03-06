#!/bin/bash
alias cp='cp'
##---------备份日志----------##
now=`date "+%Y%m%d%H%M%S"`
finalname="logs-"$now
backupdir="./tmp/${app_name}/${app_name}/backup"
mkdir "$backupdir" &>/dev/null

##------删除未调整新规范之前版本的遗留内容-----##
rm -rf logs backup

if [ -d ./${app_name}/logs ];then
	cp -R ./${app_name}/logs "${backupdir}"/$finalname &>/dev/null
fi

# open port
path=`dirname $0`
#[firewall]
sh ${path}/openPort.sh ${app_server_port},${rpc_protocol_port}

rm -rf ${app_name}
cp -rf ./tmp/${app_name}/${app_name} ./
chmod -R 755 ./${app_name}