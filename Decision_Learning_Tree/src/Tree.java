/*
 * Node.java
 * ------------
 * Name: Nathan Hayes-Roth
 * UNI: nbh2113
 * Project 4: Learning Decision Trees
 * ------------
 * Class of trees to be used in the decision tree learning algorithm.
 */

import java.util.ArrayList;

public class Tree{
    
    /* class attributes */
    private String attribute;
    private String value;
    private ArrayList<Tree> subtrees;

    /*
     * basic constructor
     * returns: Tree
     * args: NA
     */
    public Tree(){
        this.attribute = Learner.default_value;
        this.value = Learner.default_value;
        this.subtrees = new ArrayList<Tree>();
    }
    
    /*
     * attribute constructor
     * returns: Tree
     * args: String
     */
    public Tree(String index, String value){
        this.attribute = index;
        this.value = value;
        this.subtrees = new ArrayList<Tree>();
    }
    
    /* getAttribute()
     * returns: String
     * args: NA
     */
    public String getAttribute(){
        return this.attribute;
    }
    
    /* getValue()
     * returns: String
     * args: NA
     */
    public String getValue(){
        return this.value;
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
    
    /* setSubtrees(ArrayList<Tree> trees)
     * returns: NA
     * args: ArrayList<Tree>
     */
    public void setSubtrees(ArrayList<Tree> trees){
        this.subtrees = trees;
    }
    
    /* addSubtree(Tree tree)
     * returns: NA
     * args: Tree
     */
    public void addSubtree(Tree tree){
        this.subtrees.add(tree);
    }
    
    /* addSubtree(String str)
     * returns: NA
     * args: Tree
     */
    public void addSubtree(String index, String value){
        this.subtrees.add(new Tree(index, value));
    }

	public boolean isLeaf() {
		if (this.subtrees.isEmpty())
			return true;
		return false;
	}
	
	public String toString(){
		return this.toString("");
	}
	public String toString(String x){
		String to_return = "\n" + x + "Attribute: " + this.attribute;
		to_return += "\n" + x + "Value: " + this.value;
		to_return += "\n" + x + "--------------";
		for (int i = 0; i < this.subtrees.size(); i++)
			to_return += this.subtrees.get(i).toString(x + "\t");
		return to_return;
	}
}