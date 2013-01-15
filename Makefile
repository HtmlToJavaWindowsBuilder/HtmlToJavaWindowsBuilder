ANT_JAR := lib/ant-launcher.jar

ANT := java -cp lib -jar ${ANT_JAR}

all :
	${ANT}
	
.PHONY : test
test :
	${ANT} test

clean :
	${ANT} clean
