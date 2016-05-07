#!/bin/bash
ORDER=5
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
if [ ! -e counts ]
then
    mkdir counts
else
    rm -rf counts
    mkdir counts
fi
make-batch-counts path.txt 5 cat counts -order $ORDER -sort -no-sos -no-eos
merge-batch-counts counts
finalrawcountfile="unknown.count"
files=`ls ./counts`
for file in $files
do
    if [ "${file##*.}" = "gz" ]
    then
        echo $Par_Dir"/"$file >> path.txt
	finalrawcountfile=$Cur_Dir"/counts/"$file
    fi
done

if [ -e results ]
then
    rm -rf results
fi
mkdir results
execmd="ngram-count -read $finalrawcountfile -order $ORDER -sort -write results/final.count -write-vocab results/final.vocab"
for ((i=1;i<=$ORDER;i++)); do 
    execmd=${execmd}" -write$i results/order${i}final.count"
done
eval $execmd