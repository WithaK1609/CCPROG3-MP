import java.util.Scanner;

public class InputLogic{
    
    static Scanner scanner = new Scanner(System.in);
    
     public static double readDouble(String prompt, double minChoice, double maxChoice) {
        double input = 0;
        
        do {
            System.out.print(prompt);

            try {
                input = Double.parseDouble(scanner.next());
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
     * @param prompt
     * @param minChoice
     * @param maxChoices
     * @return int
     */
    
     public static int readInt(String prompt, int minChoice, int maxChoices){

        int input;
        
        do
        {
            System.out.print(prompt);

            try{
                input = Integer.parseInt(scanner.next());
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

    
    public static String readString(String prompt){
       System.out.print(prompt);

       scanner.nextLine();

       return scanner.nextLine(); 
    }

    public static void closeScanner(){
        scanner.close();
    }
    
}   