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

1. Deposit training and testing csv files into the src folder.
    $ cp file.csv ~/.../Decision_Learning_Tree/src/
2. From the root directory, compile all .java files in the srce folder.
    $ java c src/*.java
3. cd into the src folder and run Learner.
    $ cd src/
    $ java Learner
4. Enter the name of a csv file to build a tree.
    train.csv
5. Enter the name of a csv file to receive suggestions from the tree.
    test.csv

-----------------------------------

*** Program Description ***
This package contains an implementation of Learning Decision Trees as they
are described in "Artifical Intelligence - A Modern Approach (2nd)", Russel
& Norvig. The program allows the user to build a tree from a comma
deliminated .csv file and then receive instructions based on another .csv
file. All files are contained within the src folder to facilitate easier
running on CLIC lab machines. Two default files (train.csv and test.csv) are
made available to demonstrate the program's functionality.

-----------------------------------

*** Files ***

/nbh2113_assignment4_decision_tree/
    README.txt
        - You're reading it.
    /nbh2113_assignment4_decision_tree/decision_tree/src/
    Tree.java
        - Encapsulates the Tree class that is used within the learning algorithm
    Learner.java
        - Contains the decision tree learning algorithm
        - Administers the program
    train.csv
        - default training file
    test.csv
        - default testing file
