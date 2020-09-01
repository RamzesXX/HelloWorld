#!/bin/bash

java -jar 																\
	 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 \
	 -XX:+OmitStackTraceInFastThrow 									\
	 -XX:-StackTraceInThrowable 										\
	 hello-world-1.0-SNAPSHOT.jar