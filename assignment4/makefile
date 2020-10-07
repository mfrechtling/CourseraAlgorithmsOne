JCFLAGS = -g
JC = javac-algs4
.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Board.java \
	Solver.java \
	PuzzleChecker.java \

default: classes

classes: $(CLASSES:.java=.class)

zip:
	zip assigment4.zip Board.java Solver.java

clean:
	$(RM) *.class
	$(RM) *.zip
