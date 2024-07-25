import java.util.Scanner;
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
    
    static Scanner scanner = new Scanner(System.in);
    
    /**
      * Reads a double value from the console within a specified range.
      * 
      * @param prompt the message displayed to the user to input a value
      * @param minChoice the minimum allowed value
      * @param maxChoice the maximum allowed value
      * @return the valid double value entered by the user
      */
     public static double readDouble(String prompt, double minChoice, double maxChoice) {
        double input = 0;
        
        do {
            System.out.print(prompt);

            try {
                input = Double.parseDouble(scanner.next());
                scanner.nextLine();
                if (input < minChoice || input > maxChoice) {
                    System.out.println("Invalid Input! Please choose only what is displayed! " + minChoice + " to " + maxChoice + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input! Please choose only a double value!");
                scanner.nextLine();
            }
        } while (input < minChoice || input > maxChoice);

        return input;
    }

    /** 
     * This method reads an int from the user. 
     * It will only accept a number between minChoice and maxChoices.
     * The user will be asked again if the input is not within the range.
     * The prompt is displayed before the input is read.
     * @param prompt the message displayed to the user to input a value
     * @param minChoice the minimum allowed value
     * @param maxChoices the maximum allowed value
     * @return int
     */
    
     public static int readInt(String prompt, int minChoice, int maxChoices){

        int input;
        
        do
        {
            System.out.print(prompt);

            try{
                input = Integer.parseInt(scanner.next());
                scanner.nextLine();
                if (input < minChoice || input > maxChoices) 
                {
                    System.out.println("Invalid Input! Please choose only what is displayed! " + minChoice + " to " + maxChoices + ".");
                }
            }catch(NumberFormatException e){
                input = -1;
                System.out.println("Invalid Input! Please choose only an Integer!");
                scanner.nextLine();
            }
        }while (input < minChoice || input > maxChoices);

        return input;
    }

    /**
      * Reads a string value from the console.
      * 
      * @param prompt the message displayed to the user to input a value
      * @return the string value entered by the user
      */
      public static String readString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
      * Closes the scanner used for reading input.
      */
    public static void closeScanner(){
        scanner.close();
    }
    
}   