######### MAKEFILE #########

CLASSPATH = .:jFuzzyLogic.jar
JFLAGS = -g -cp $(CLASSPATH)
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

DOS = \
	  	DOS_ataque.java \
		Escritor.java

all: dos run

dos: $(DOS:.java=.class)

run: 
	java -cp $(CLASSPATH) DOS_ataque

###### CLEAN #####
clean:	
		rm -rf *.class
		@echo "clean"

