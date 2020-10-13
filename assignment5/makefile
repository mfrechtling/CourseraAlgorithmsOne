JCFLAGS = -g
JC = javac-algs4
.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	KdTree.java \
	PointSET.java \

default: classes

classes: $(CLASSES:.java=.class)

zip:
	zip assigment5.zip KdTree.java PointSET.java

clean:
	$(RM) *.class
	$(RM) *.zip
