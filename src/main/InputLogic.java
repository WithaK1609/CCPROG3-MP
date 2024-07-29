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
      * Validates a double value within a specified range.
      * 
      * @param inputTextField the input entered by the user from the GUI
      * @param minChoice the minimum allowed value
      * @param maxChoice the maximum allowed value
      * @return the valid double value entered by the user
      */
     public static boolean validateDouble(String inputTextField, double minChoice, double maxChoice) {
        double input;
        if (inputTextField == null || inputTextField.trim().isEmpty()) {
            return false; // if input is null or empty
        }
        
        try{
            input = Double.parseDouble(inputTextField);
            if (input < minChoice || input > maxChoice) {
                return false;      // if input is not within the range of min and max values
            }
        }catch(NumberFormatException e) {
            return false;      // if input is not a double  
        }

        return true;
    }

    /** 
     * Validates a double value within a specified range.
     * @param inputTextField the input entered by the user from the GUI
     * @param minChoice the minimum allowed value
     * @param maxChoices the maximum allowed value
     * @return int
     */
    
     public static boolean validateInt(String inputTextField, int minChoice, int maxChoices){
        int input;
        
        if (inputTextField == null || inputTextField.trim().isEmpty()) {
            return false; // if input is null or empty
        }

        try{
            input = Integer.parseInt(inputTextField);
            if (input < minChoice || input > maxChoices) {
                return false;      // if input is not within the range of min and max values
            }
        }catch(NumberFormatException e) {
            return false;      // if input is not an integer  
        }
        
        return true;
    }
}   