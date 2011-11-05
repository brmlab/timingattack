#!/bin/bash
. libs.sh

if [ $# -ne 1 ] | [ $# -ne 3 ]; then
  echo 1>&2 Usage: $0 location [rangeLow rangeHigh]
  exit -1
fi

if [ $# -eq 3 ]; then
    dirs=""
    for i in `seq $2 $3`; do
        dirs="$dirs $1$i"
    done
else
    dirs="$1"
fi
for i in $dirs; do
    mkdir $i
    # This will create a new key store in the given location and generate a new
    # symmetric signing key. This expects Keyczar and GSON to be on the classpath
    if [ ! -f $1/meta ]; then 
        java -Dfile.encoding=UTF-8 -classpath $CP cz.cvut.keyczar.KeyczarTool create --location=$i --purpose=sign
    fi
    java -Dfile.encoding=UTF-8 -classpath $CP cz.cvut.keyczar.KeyczarTool addkey --location=$i --status=primary
done
