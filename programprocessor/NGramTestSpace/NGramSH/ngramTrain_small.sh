#!/bin/bash
Cur_Dir=$(pwd)
echo $Cur_Dir
cd ../
Par_Dir=$(pwd)
echo $Par_Dir
cd $Cur_Dir

if [ -e path.txt ]
then
    rm path.txt
fi
files=`ls $Par_Dir`
for file in $files
do
    if [ "${file##*.}" = "txt" ]
    then
        echo $Par_Dir"/"$file >> path.txt
    fi
done
echo $filelist
if [ ! -e counts ]
then
    mkdir counts
else
    rm -rf counts
    mkdir counts
fi
make-batch-counts path.txt 1 cat counts -order 3
merge-batch-counts counts
if [ -e trainfile.lm ]
then
    rm trainfile.lm
fi
make-big-lm -read counts/*.gz -order 3 -lm trainfile.lm
sort-lm trainfile.lm > sorted-trainfile.lm
line=$(grep -n "\\\3-grams" sorted-trainfile.lm | cut -f1 -d:)
(sed -n "$line,\$p" < sorted-trainfile.lm) > truncated-sorted-trainfile.lm
