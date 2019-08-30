#!/bin/sh

JAVA_HOME=/usr/local/scm/dev/build/sdks/jdk1.6.0_23
JAVA=$JAVA_HOME/bin/java
GIT_HOME=/tmp/Gitpoc
echo $JAVA

CLASSPATH=$GIT_HOME/lib/classes.jar:$GIT_HOME/lib/org.apache.commons.io.jar:$GIT_HOME/lib/org.eclipse.jgit-3.3.2.201404171909-r.jar:$GIT_HOME/lib/javax.ws.rs.jar:$GIT_HOME/lib/jersey-client-1.9.jar:$GIT_HOME/lib/com.sun.jersey.jersey-core-1.4.0.jar:$GIT_HOME/lib/java-json.jar:$GIT_HOME/lib/json-simple.jar

echo $CLASSPATH


exec $JAVA -classpath $CLASSPATH ConnectionTest

echo "done"


