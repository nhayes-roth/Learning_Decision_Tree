Nathan Hayes-Roth
COMS W4701 - Artificial Intelligence
Project #4
Decision Tree Learning

-----------------------------------

*** Development Information ***
-Programming Language: Java 
-Language Version: 1.7.0_07-b10
-Development Environment: Windows 7
-Development Software: Eclipse 

-----------------------------------

*** Instructions to compile and run on CLIC machines ***

1. From the parent directory, make the default target to compile the program.
    ~/.../nbh2113_assignment4_decision_tree$ make
2. From the parent directory, make the play target to run the program.
    ~/.../nbh2113_assignment4_decision_tree$ make play
3. 
4. Press Ctrl + c to exit the program.

-----------------------------------

*** Program Description ***
This package contains an implementation of Learning Decision Trees as they are described in "Artifical Intelligence - A Modern Approach (2nd)", Russel & Norvig. The program allows the user to build a tree from a comma deliminated .csv file and then receive instructions based on another .csv file. Please place all data files into the resources folder.

-----------------------------------

*** Files ***

/nbh2113_assignment4_decision_tree/
Makefile
    - make        - compile the program
    - make learn  - run the program
    - make clean  - delete executable files

/nbh2113_assignment4_decision_tree/decision_tree/src/
Tree.java
    - Encapsulates the Tree class that is used within the learning algorithm
Learner.java
    - Contains the decision tree learning algorithm
    - Administers the program
README.txt
    - You're reading it.

/nbh2113_assignment4_decision_tree/decision_tree/resources/
restaurant2_train.csv
    - default training file
test.csv
    - default testing file