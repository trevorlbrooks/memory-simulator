#
# Makefile for Generating C++ executables
# S17 CSCI 460 - Operating Systems
# Trevor Brooks

# Define Macros related to printing and submitting project
a2ps	= a2ps -T 2
tar		= tar -cvzf

# Define Macros related to object code and program generation
C++  = g++ -std=c++11
CFLAGS = -Wall -Werror
LD = g++
LDFLAGS =
LIBS = 
SRCS = Driver.cpp PageTable.h PageTable.cpp FrameTable.cpp FrameTable.h
OBJS = Driver.o PageTable.o FrameTable.o
EXEC = memorysimulator

# Default rule to make if now target specified on command line
all:		$(EXEC)

# Dependency Rules
${EXEC}: Driver.o
Driver.o: Driver.cpp FrameTable.o
FrameTable.o: FrameTable.h FrameTable.cpp PageTable.o
PageTable.o: PageTable.h PageTable.cpp

#Rules to create target file Driver
# If any files on line with colon are modified, then recompile the object file
# Target Rules for Make
$(EXEC):	$(OBJS)
		$(LD) $(LDFLAGS) -o $(EXEC) $(OBJS) $(LIBS)

clean:
		rm -f $(OBJS) $(EXEC) 
		

print:	$(SRCS) Makefile
	$(a2ps) $?

# Implcit Rules based on file extension dependencies
.cpp.o:
	$(C++) $(CFLAGS) -c $<
	
