JAVA_DIR=/usr/local/jdk1.6.0
UBISERVICE_DIR=/webapp/myapp/UbiService
PROPERTY_DIR=$UBISERVICE_DIR
FONT_DIR=$UBISERVICE_DIR/fonts/
CLASSPATH=$UBISERVICE_DIR/lib/UbiServer.jar
XMS=1024M
XMX=2048M

GREP_STR=$UBISERVICE_DIR/lib/UbiServer.jar

#For SunOS
#if [ `/usr/ucb/ps -auxwww | grep $GREP_STR | grep -v grep | awk '{print $2}'` ];then

if [ `ps -ef | grep $GREP_STR | grep -v grep | awk '{print $2}'` ];then
	echo ""
	echo ">>> UbiService is running."
	echo ""
else
	$JAVA_DIR/bin/java -Xms$XMS -Xmx$XMX -Dfile.encoding=UTF-8 -Djava.awt.headless=true -Dsun.java2d.fontpath=$FONT_DIR -classpath $CLASSPATH:. com.ubireport.service.UbiService $PROPERTY_DIR & 
	echo ""
	echo ">>> UbiService has started."
	echo ""
fi
