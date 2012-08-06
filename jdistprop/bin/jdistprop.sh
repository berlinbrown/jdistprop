#!/bin/sh
dir="$(dirname $0)"
cp="$dir/jdistprop.jar"
if [ -n "$CLASSPATH" ] ; then
  cp="$cp:$CLASSPATH"
fi
java -cp "$cp" org.berlin.jdistprop.application.MainStart $@

