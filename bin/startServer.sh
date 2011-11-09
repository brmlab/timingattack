#!/bin/bash
. libs.sh

if [ $# -lt 4 ]; then 
    echo "Usage:"
    echo "$0 <level> <port> <kudofileprefix> <keydirprefix>"
    echo "<kudofile> = <kudofileprefix><level>"
    echo "<keydir> = <keydirprefix><level>"

    exit 1
fi

milis=0
if [ $1 -eq 0 ]; then
    nanos=0
    milis=1
elif [ $1 -eq 1 ]; then
    nanos=10000
elif [ $1 -eq 2 ]; then
    nanos=100
elif [ $1 -eq 3 ]; then
    nanos=0
else
    echo "U TROLL? IDK LEVEL > 4!!"
    echo "Usage:"
    echo "$0 <level> <port> <kudofileprefix> <keydirprefix>"
    echo "<kudofile> = <kudofileprefix><level>"
    echo "<keydir> = <keydirprefix><level>"
    exit 1
fi

java -Dmilis=$milis -Dnanos=$nanos -Dfile.encoding=UTF-8 -jar $JAR $2 $3$1 $4$1
