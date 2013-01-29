ANT_JAR := lib/ant-launcher.jar

ANT := java -cp lib -jar ${ANT_JAR}

all :
	${ANT}
	
.PHONY : test sample
test :
	${ANT} test

clean :
	${ANT} clean

sample :
	${ANT} sample
