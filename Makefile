main: isolation/Node.java isolation/Play.java
	javac isolation/*.java

play: isolation/Play.class
	java isolation.Play

.Phony: clean
clean:
	rm -f isolation/*.class