#!/bin/bash
find . -type f -not \( -name '*.java' -or -name '*.sh' \) -delete

numberoffiles=`ls -lR|grep "^-"|wc -l`

echo $numberoffiles
