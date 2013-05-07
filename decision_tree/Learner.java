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
import java.io.*;

public class Learner{
    
    /* constants */
    public static String default_value = "No attribute assigned";

    /* class attributes */
    private String attribute;
    private ArrayList<Tree> subtrees;

    
    /* train()
     * returns: 
     * args: 
     */
    public void train(){
        
    }
    /* getFileName()
     * returns: 
     * args: 
     */
    public static String getFileName(){
        System.out.println("Please name the file you'd like to train from:");

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
     * returns: 
     * args: 
     */
    public static String readFile(String file_name){
        String to_return = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(file_name));
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
    
    /* main function */
    public static void main(String [] args){
        String file_name = getFileName();
        String training_data = readFile(file_name);
        System.out.println(training_data);
    }
    
}