# Trevor Brooks
# Operating Systems CSCI 460 
# Spring 2017
# Capstone - Memory Simulator

JCC = javac
JAR = jar cfe

.SUFFIXES: .java .class

JFLAGS = 

EXEC= memorysimulator.jar
SRCS = Process.java Driver.java FrameTable.java
OBJS = Process.class Driver.class FrameTable.class

all:	$(EXEC)

${EXEC}: Driver.class FrameTable.class Process.class
Driver.class: Driver.java
FrameTable.class: FrameTable.java
Process.class: Process.java

$(EXEC): $(OBJS)
		$(JAR) $(EXEC) Driver $(OBJS)

clean:
		rm -f $(OBJS) $(EXEC)

.java.class:
		$(JCC) $(JFLAGS) $*.java
