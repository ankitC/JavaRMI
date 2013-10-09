#!/bin/bash

if [ ! -z $1 ]
then
	java -cp src/javaRMI.jar $1
else
	echo "Sorry, must enter a valid program! (ex: Client, Registry, CapitalsServer)"
fi