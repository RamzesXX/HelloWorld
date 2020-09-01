#!/bin/bash 

TIMES=$((0 + ${1:-1000}))

echo "============== times: " $TIMES " ============"

for i in $(seq 1 $TIMES);
do 
	 curl -X POST http://localhost:8082/course/section/9052143 \
	 	 -H "X-API-KEY: 1234" \
	 	 -H "cengage-wa-principal-type: SYS"						\
		 -H "cengage-wa-principal-id: test"							\
		 -H "Accept: application/json"								\
		 -H "Content-Type: application/json"
	 echo 
	 echo $i
done