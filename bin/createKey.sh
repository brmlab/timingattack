#!/bin/bash
. libs.sh

if [ $# -ne 1 ]; then
  echo 1>&2 Usage: $0 location
  exit -1
fi

# This will create a new key store in the given location and generate a new
# symmetric signing key. This expects Keyczar and GSON to be on the classpath
if [ ! -f $1/meta ]; then 
    java -Dfile.encoding=UTF-8 -classpath $CP cz.cvut.keyczar.KeyczarTool create --location=$1 --purpose=sign
fi

java -Dfile.encoding=UTF-8 -classpath $CP cz.cvut.keyczar.KeyczarTool addkey --location=$1 --status=primary
