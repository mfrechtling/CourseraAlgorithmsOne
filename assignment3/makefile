JCFLAGS = -g
JC = javac-algs4
.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
    Point.java \
	BruteCollinearPoints.java \
	FastCollinearPoints.java \
	TestBench.java \
	TestClient.java \
    LineSegment.java \

default: classes

classes: $(CLASSES:.java=.class)

zip:
	zip assigment3.zip BruteCollinearPoints.java FastCollinearPoints.java Point.java

clean:
	$(RM) *.class
	$(RM) *.zip
