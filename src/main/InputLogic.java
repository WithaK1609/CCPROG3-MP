package main;
/**
 * Represents a utility class for handling input logic.
 * 
 * <p>This class provides methods for reading double values, integer values, and strings from the console.
 * <p>It ensures that the input values are within specified ranges and handles invalid inputs gracefully.
 * 
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */
public class InputLogic{
    
    
    /**
      * Reads a double value from the console within a specified range.
      * 
      * @param inputTextField the input entered by the user from the GUI
      * @param minChoice the minimum allowed value
      * @param maxChoice the maximum allowed value
      * @return the valid double value entered by the user
      */
     public static double readDouble(String inputTextField, double minChoice, double maxChoice) {
        double input;
        
        try{
            input = Double.parseDouble(inputTextField);
            if (input < minChoice || input > maxChoice) {
                return -1.0;      // if input is not within the range of min and max values
            }
        }catch(NumberFormatException e) {
            return -2.0;      // if input is not a double  
        }

        return input;
    }

    /** 
     * This method reads an int from the user. 
     * It will only accept a number between minChoice and maxChoices.
     * @param inputTextField the input entered by the user from the GUI
     * @param minChoice the minimum allowed value
     * @param maxChoices the maximum allowed value
     * @return int
     */
    
     public static int readInt(String inputTextField, int minChoice, int maxChoices){

        int input;

        try{
            input = Integer.parseInt(inputTextField);
            if (input < minChoice || input > maxChoices) {
                return -1;      // if input is not within the range of min and max values
            }
        }catch(NumberFormatException e) {
            return -2;      // if input is not an integer  
        }
        
        return input;
    }
}   