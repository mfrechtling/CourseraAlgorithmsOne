JCFLAGS = -g
JC = javac-algs4
.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Deque.java \
	DequeTests.java \
	RandomizedQueue.java \
	RandomizedQueueTests.java \
	Permutation.java \

default: classes

classes: $(CLASSES:.java=.class)

zip:
	zip assigment2.zip Deque.java RandomizedQueue.java Permutation.java

clean:
	$(RM) *.class
