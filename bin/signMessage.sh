#!/bin/bash
. libs.sh

java -Dfile.encoding=UTF-8 -classpath $CP cz.cvut.keyczar.homework.CreateSignedFile $@
