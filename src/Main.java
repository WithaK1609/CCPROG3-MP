public class Main{

    // prints out ======= for design purposes
    public static void design() {
        System.out.println("======================================================================================================================");
    }

    public static void main(String[] args){
        System.out.println("Welcome to the Hotel Reservation System!");
        
        int choice = -1; 
        
        // start of the program
        while(choice != 0){
            design();
            System.out.println("Select which feature to use:");
            System.out.println("\t[1] Create Hotel");
            System.out.println("\t[2] View Hotel");
            System.out.println("\t[3] Manage Hotel");
            System.out.println("\t[4] Simulate Booking");
            System.out.println("\t[0] Exit Program");
            design();

            choice = InputLogic.readInt("Choose: ", 0, 4);  // gets user input. Refer to the InputLogic for more details

            if (choice == 1){
                
            }

            else if(choice == 2){
            
            }

            else if(choice == 3){

            }

            else if(choice == 4){

            }
        }
        InputLogic.closeScanner();
        
    }
}
