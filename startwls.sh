pid=`ps -ef | grep -i weblogic | awk '{print $2}'`
if [[ ! -z "$pid" ]]
then
        kill -9 $pid > /dev/null 2>&1
fi

out_file=`pwd`/wls.out

cd /u01/app/demo_home/domains/demo_domain > /dev/null

export USER_MEM_ARGS="-Xms1024m -Xmx1024m"
#export JAVA_OPTIONS="${JAVA_OPTIONS} -Dconfig.url=/u01/app/apex/ords/config"

nohup ./startWebLogic.sh > $out_file 2>&1 &

cd - > /dev/null