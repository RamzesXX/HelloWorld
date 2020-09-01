#!/bin/bash 

URL=http://localhost:8082/test/1
TIMES=$((0 + ${1:-1000}))

echo "============== times: " $TIMES " ============"

for i in $(seq 1 $TIMES);
do 
	 curl $URL
	 echo $i
done