#!/bin/bash

find src/ -iname \*.class -exec rm '{}' ';'
rm javaRMI.jar