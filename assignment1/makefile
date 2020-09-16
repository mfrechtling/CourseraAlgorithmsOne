JCFLAGS = -g
JC = javac-algs4
.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	PercolationStats.java \
	Percolation.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
