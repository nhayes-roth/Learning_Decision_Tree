/*
 * Node.java
 * ------------
 * Name: Nathan Hayes-Roth
 * UNI: nbh2113
 * Project 4: Learning Decision Trees
 * ------------
 * Implementation of the decision tree learning algorithm described in AI - A Modern Approach 2nd (Russel & Norvig).
 */


import java.util.ArrayList;
import java.io.*;
import java.util.Hashtable;
import java.util.Set;
import java.util.HashSet;

public class Learner{
	
    /* static variables */
    public static String default_value = "No value assigned";
    private static ArrayList<String[]> examples = new ArrayList<String[]>();
    private static ArrayList<Boolean> attributes = new ArrayList<Boolean>();
    private static Hashtable<String, Integer> values = new Hashtable<String, Integer>();
    
    /* main function */
    public static void main(String [] args){
        
    	examples = fileToArrayList(getFileName());		// build examples from input
    	//examples = fileToArrayList("restaurant2_train.csv");		// build examples from default
    	System.out.println("Training complete!\n");
        
    	for (int i = 0; i < examples.get(0).length-1; i++)
        	attributes.add(true);							// make attributes correct length
        Tree tree = decisionTreeLearning(examples, attributes, default_value);
        
        examples = fileToArrayList(getFileName());		// build examples from input
    	//examples = fileToArrayList("test.csv");				// build examples from default
    	printResults(examples, tree);
    	//System.out.println(tree.toString());
    }
    
    /*
     * solveFor(ArrayList<String[]> examples, Tree tree)
     * solves for the goal variable 
     */
    private static void printResults(ArrayList<String[]> examples, Tree tree) {
		for (int i = 0; i < examples.size(); i++){
			System.out.println("\nRow " + i + "\n--------------------------");
			for (int j=0; j < examples.get(0).length; j++)
				System.out.print("|  " + examples.get(i)[j] + "  |");
			System.out.println(solveFor(examples.get(i), tree));
		}
	}

    /*
     * solveFor(ArrayList<String[]> data, Tree tree)
     * solves for the goal variable 
     */
	private static String solveFor(String[] data, Tree tree) {
		while(true){
			if (tree.isLeaf())
				return ("\n\nAnswer: " + tree.getValue() + "\n--------------------------");
			else{
				int index = Integer.parseInt(tree.getAttribute());
				String value = data[index];
				ArrayList<Tree> subtrees = tree.getSubtrees();
				for (int i = 0; i < subtrees.size(); i++){
					String to_check = subtrees.get(i).getValue();
					String to_check2 = subtrees.get(i).getAttribute();
					if ((to_check.equals(value)) || to_check2.equals(value)){
						tree = subtrees.get(i);
						return solveFor(data, tree);
					}
				}
				return "\n\nAnswer: Sorry, I couldn't figure it out : (" + "\n--------------------------";
			}
		}
	}

	/* Decision-Tree-Learning Algorithm */
    private static Tree decisionTreeLearning(ArrayList<String[]> examples,
    									ArrayList<Boolean> attributes,
    									String default_value){
    	// if examples is empty, return default
        if (examples.size()<1)
        	return new Tree("leaf", default_value);
        
        // else if all examples have the same classification, return that classification
        else if (sameClassification(examples))
        	return new Tree(default_value, examples.get(0)[examples.get(0).length-1]);
        
        //else if attributes is empty, return the Majority Value of examples
        else if (attributesIsEmpty())
        	return new Tree("leaf", majorityValue(examples));
        
        else{
        	// make a new tree from the best available attribute
        	int best = chooseAttribute(examples, attributes);
        	Tree tree = new Tree(Integer.toString(best), default_value);
        	String m = majorityValue(examples);
        	
        	// create a set of values of the best attribute
        	Set<String> best_values = new HashSet<String>();
        	for (String[] row : examples)
        		best_values.add(row[best]); 
        	
        	// for each value of column best
        	for (String value : best_values){
        		
        		// select rows where best = value
        		ArrayList<String[]> examples_i = matchingRows(examples, best, value);
        		attributes.set(best, false);
        		Tree subtree = decisionTreeLearning(examples_i, attributes, value);
        		tree.addSubtree(subtree);
        	}
        	return tree;
        }
    }
    
    private static ArrayList<String[]> matchingRows(
			ArrayList<String[]> examples, Integer column, String value) {
    	ArrayList<String[]> to_return = new ArrayList<String[]>();
    	for (String[] row : examples){
    		if (row[column].equals(value))
    			to_return.add(row);
    	}
    	return to_return;
	}

	/* getFileName() */
    public static String getFileName(){
        System.out.println("Please name the file you'd like to read in:");

        String file_name = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            file_name = br.readLine();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return file_name;
    }
    /* readFile()
     * finds the designated file in the resources folder, returns a String
     * representation of the file's contents
     */
    public static String readFile(String file_name){
        String to_return = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader("resources/" + file_name));
            StringBuilder builder = new StringBuilder();
            String line = in.readLine();
            while (line != null) {
                builder.append(line);
                builder.append("\n");
                line = in.readLine();
            }
            in.close();
            to_return = builder.toString();
        }
        catch (Exception e){
            System.out.println("Exception: " + e);
        }
        return to_return;
    }
    
    /* fileToArrayList(String filename)
     * builds an arrayList of String[] containing all the example data,
     * to be used to train the decision tree.
     */
    public static ArrayList<String []> fileToArrayList(String filename){		
    	ArrayList<String []> list = new ArrayList<String []>();
    	 try{
    		FileInputStream fstream = new FileInputStream("resources/" + filename);
    		DataInputStream in = new DataInputStream(fstream);
    		BufferedReader br = new BufferedReader(new InputStreamReader(in));
    		String strLine;
    		while ((strLine = br.readLine()) != null){
    			list.add(strLine.split(","));
    		}
    	 }catch(Exception e){
    		 System.out.println(e.getMessage());
    	 }
    	 for (String[] row : list){
         	for (int i = 0; i < row.length; i++)
         		row[i] = row[i].replaceAll("\\s","");
         }
    	return list;
    }
    
    /* 
     * sameClassification(ArrayList<String[]> examples)
     * returns true if all the examples have the same classification
     */
	private static boolean sameClassification(ArrayList<String[]> examples) {
		int length = examples.get(0).length;
		String first = examples.get(0)[length-1];
		for (String[] array : examples){
			if (!array[length-1].equals(first))
				return false;
		}
		return true;
	}
    
    /* 
     * isEmpty(ArrayList<Boolean> booleans)
     * returns true if the attributes arraylist is filled with false values
     */
	private static boolean attributesIsEmpty() {
		for (boolean b : attributes){
			if (b == true)
				return false;
		}
		return true;
	}
	
	/*
	 * majorityValue(ArrayList<String[]> examples)
	 * returns the most common value from the given ArrayList of examples
	 */
	private static String majorityValue(ArrayList<String[]> examples) {
		// clear the Hashtable
		values.clear();
		// fill the table with correct counts
		//		either put a new value with associated count of 1
		//		or increment a previous value's count
		for (String[] row : examples){
			if (!values.containsKey(row[row.length-1]))
					values.put(row[row.length-1], 1);
			else values.put(row[row.length-1], values.get(row[row.length-1])+1);
		}
		// find the one with the biggest number
		String majority_value = "";
		int majority_count = 0;
		Set<String> key_set = values.keySet();
		for (String key : key_set){
			if (values.get(key) > majority_count){
				majority_value = key;
				majority_count = values.get(key);
			}
		}
		return majority_value;
	}
    
    /*
     * chooseAttribute(ArrayList<String[]> examples, ArrayList<Boolean> attributes)
     * selects the best attribute to next use in the decision tree
     */
	private static int chooseAttribute(ArrayList<String[]> examples,
			ArrayList<Boolean> attributes) {
		// calculate information
		double information = calculateInformation(examples, attributes);
		// create an arrayList of attribute gains
		ArrayList<Double> remainders = new ArrayList<Double>(attributes.size());
		// step through attributes
		for (int i = 0; i < attributes.size(); i++){
			// if the value is false, set it to negative information
			if (attributes.get(i) == false)
				remainders.add(-9999.);
			// otherwise, calculate the remainder
			else remainders.add(calculateRemainder(i, examples, attributes));
		}
		// weird bug fix
		if (remainders.get(remainders.size()-1) == 0)
			remainders.remove(remainders.size()-1);
		// find index of maximum value (chosen attribute)
		int index = maxGain(information, remainders);
		return index;
	}
	
	private static Double calculateRemainder(int i, ArrayList<String[]> examples,
			ArrayList<Boolean> attributes) {
		double to_return = 0;
		int total = 0;
		// clear the hashtable
		values.clear();
		// make another hashtable of the column values
		Hashtable<String, Integer> col_values = new Hashtable<String, Integer>();
		for (String[] row : examples){
			total = total + 1;
			if (!col_values.containsKey(row[i]))
					col_values.put(row[i], 1);
			else 
				col_values.put(row[i], col_values.get(row[i])+1);
		}
		Set<String> col_set = col_values.keySet();
		ArrayList<String[]> new_examples = new ArrayList<String[]>();
		// for each column value
		for (String value : col_set){
			new_examples.clear();
			// for each example row
			for (String[] row : examples){
				// add the row to the new ArrayList if the column value matches
				if (row[i].equals(value)){
					new_examples.add(row);
				}
			}
			double count = new_examples.size();
			to_return = to_return - (count/total)*calculateInformation(new_examples, attributes);
		}
		return to_return;
	}
	private static double calculateInformation(ArrayList<String[]> examples,
			ArrayList<Boolean> attributes){
		double total = 0;
		double to_return = 0;
		// clear the Hashtable
		values.clear();
		// fill the table with correct counts, also count total number
		for (String[] row : examples){
			total = total + 1;
			if (!values.containsKey(row[row.length-1]))
					values.put(row[row.length-1], 1);
			else values.put(row[row.length-1], values.get(row[row.length-1])+1);
		}
		// for each value
		Set<String> key_set = values.keySet();
		for (String key : key_set){
			double p = (double)values.get(key);
			to_return = to_return - (p/total)*(Math.log(p/total)/Math.log(2));
		}
		return to_return;
	}
	
	private static int maxGain(Double information, ArrayList<Double> remainders){
		int to_return = 0;
		double gain = -100.;
		for (int i = 0; i < remainders.size(); i++)
			if (information + remainders.get(i) > gain){
				to_return = i;
				gain = information + remainders.get(i);
			}
		//System.out.println(gain);
		return to_return;
	}
}