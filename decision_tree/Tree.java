/*
 * Node.java
 * ------------
 * Name: Nathan Hayes-Roth
 * UNI: nbh2113
 * Project 4: Learning Decision Trees
 * ------------
 * Class of trees to be used and all related methods.
 */

package decision_tree;

import java.util.ArrayList;

public class Tree{
    
    /* constants */

    /* class attributes */
    private String attribute;
    private ArrayList<Tree> subtrees;

    /*
     * basic constructor
     * returns: Tree
     * args: NA
     */
    public Tree(){
        this.attribute = Learner.default_value;
        this.subtrees = new ArrayList<Tree>();
    }
    
    /*
     * attribute constructor
     * returns: Tree
     * args: String
     */
    public Tree(String str){
        this.attribute = str;
        this.subtrees = new ArrayList<Tree>();
    }
    
    /* getAttribute()
     * returns: String
     * args: NA
     */
    public String getAttribute(){
        return this.attribute;
    }
    
    /* getSubtrees()
     * returns: ArrayList<Tree>
     * args: NA
     */
    public ArrayList<Tree> getSubtrees(){
        return this.subtrees;
    }
    
    /* setAttribute(String str)
     * returns: NA
     * args: String
     */
    public void setAttribute(String str){
        this.attribute = str;
    }
    
    /* setSubtrees(ArrayList<Tree>)
     * returns: NA
     * args: ArrayList<Tree>
     */
    public void setSubtrees(ArrayList<Tree> trees){
        this.subtrees = trees;
    }
    
    /* addSubtree(Tree)
     * returns: NA
     * args: Tree
     */
    public void addSubtree(Tree tree){
        this.subtrees.add(tree);
    }
    
    /* function()
     * returns: 
     * args: 
     */
    
}