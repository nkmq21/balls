/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Nguyen Dinh Duy
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import model.Circle;

public class CircleTest {
    //Change the name of input and output file based on practical paper
    String inputFile = "input.txt";
    String outputFile = "output.txt";

    //--VARIABLES - @STUDENT: DECLARE YOUR VARIABLES HERE:
    int n;
    String line;
    ArrayList<String> list = new ArrayList<>();
    String result = "";
    ArrayList<Circle> listcircle = new ArrayList<>();


	//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
	//--START FIXED PART--------------------------    
    String fi, fo;
    
    /**
     * Set input and output file for automatic rating
     * @param args path of input file and path of output file
     */
    public void setFile (String[] args){
        fi = args.length>=2? args[0]: inputFile;
        fo = args.length>=2? args[1]: outputFile;
    }
    
    /**
     * Reads data from input file
     */
    public void read(){
        try (Scanner sc  = new Scanner(new File(fi))){
    //--END FIXED PART----------------------------

            //INPUT - @STUDENT: ADD YOUR CODE FOR INPUT HERE:
            if (sc.hasNextLine()) {
                int n = Integer.parseInt(sc.nextLine());
                for (int i = 0; i < n; i++) {
                    if (sc.hasNextLine()) {
                        line = sc.nextLine();
                        list.add(line);
                    } else {
                        System.out.println("Error: INSUFFICIENT DATA IN INPUT FILE");
                        break;
                    }
                }
            } else {
                System.out.println("Error: no data found in input file");
            }
	//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
	//--START FIXED PART--------------------------    
            sc.close();
        }catch(FileNotFoundException ex){
            System.out.println("Input Exception # " + ex);
        }
    }
    //--END FIXED PART----------------------------
    
    //ALGORITHM - @STUDENT: ADD YOUROWN METHODS HERE (IF NEED):
    

    
	//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
	//--START FIXED PART--------------------------    
    /**
     * Main algorithm
     */
    public void solve(){
    //--END FIXED PART----------------------------

        //ALGORITHM - @STUDENT: ADD YOUR CODE HERE:
        for (String str : list) {
            String[] arr = str.split("\\s+");
            if (arr[0].equalsIgnoreCase("Circle")) {
                int x = Integer.parseInt(arr[1]);
                int y = Integer.parseInt(arr[2]);
                double radius = Double.parseDouble(arr[3]);
                Circle c = new Circle(x, y, radius);
                result += String.format("Add Circle (%d, %d, %.2f)\n", x, y, radius);
                listcircle.add(c);
            } else if (arr[0].equalsIgnoreCase("Show")) {
                result += "+++Show+++";
                for (Circle circle : listcircle) {
                    result += circle.toString() + "\n";
                }
                result += "+++++++++\n";
            } else if (arr[0].equalsIgnoreCase("Sort")) {
                result += "+++Sort+++\n";
                Collections.sort(listcircle, (c1, c2) -> Double.compare(c1.getArea(), c2.getArea()));
                for (Circle circle : listcircle) {
                    result += circle.toString() + "\n";
                }
                result += "+++++++++\n";
            }
        }
        
        


	//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
	//--START FIXED PART-------------------------- 
    }
    
    /**
     * Write result into output file
     */
    public void printResult(){
	    try{
            FileWriter fw = new FileWriter(fo);
	//--END FIXED PART----------------------------
                
        	//OUTPUT - @STUDENT: ADD YOUR CODE FOR OUTPUT HERE:
            fw.write(result);



	//--FIXED PART - DO NOT EDIT ANY THINGS HERE--
	//--START FIXED PART-------------------------- 
            fw.flush();
            fw.close();
        }catch (IOException ex){
            System.out.println("Output Exception # " + ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CircleTest q = new CircleTest();
        q.setFile(args);
        q.read();
        q.solve();
        q.printResult();
    }
	//--END FIXED PART----------------------------    
}
