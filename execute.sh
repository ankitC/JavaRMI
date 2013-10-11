#!/bin/bash

if [ ! -z $1 ]
then
	java -cp javaRMI.jar $1
else
	echo "Sorry, must enter a valid program! (ex: Client, Registry, Server)"
fi