import java.util.List;

public class TextDisplay{
    
    // prints out ======= for design purposes
    public static void design() {
        System.out.println("======================================================================================================================");
    }

    // clear console method
    public static void clearConsole(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    

    public static void displayViewHotelList(List<Hotel> hotels){
        TextDisplay.design();
            System.out.println("Here is a list of all available Hotels: ");
            
            for (int i = 0; i < hotels.size(); i++){
                System.out.println("\t[" + (i+1) + "]" + hotels.get(i).getName());
                System.out.print("\n");
            }
    }

    public static void displayHotelInformation(int choice, List<Hotel> hotels){
        TextDisplay.design();
        System.out.println("Hotel Name: " + hotels.get(choice-1).getName());
        System.out.println("Number of rooms: " + hotels.get(choice-1).getRooms().size());;
        TextDisplay.design();
        System.out.println("Select an option:");
        System.out.println("\t[1] View Room Details");
        System.out.println("\t[2] View Reservations");
        System.out.println("\t[0] Go Back");
    
    }

    public static void displayManageHotelOptions(int choice, List<Hotel> hotels){
        TextDisplay.design();
        System.out.println("Select an option:");
        System.out.println("\t[1] Change Hotel Name");
        System.out.println("\t[2] Add Room/s");
        System.out.println("\t[3] Remove Room/s");
        System.out.println("\t[4] Update Room price");
        System.out.println("\t[5] Remove Reservation");
        System.out.println("\t[6] Remove Hotel");
        System.out.println("\t[0] Go Back");
    }

}