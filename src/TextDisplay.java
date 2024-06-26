import java.util.List;
/**
 * Represents a utility class for displaying text in the console.
 *
 * <p>This class provides methods to clear the console, display a design, and display various options and information.
 *
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */
public class TextDisplay{
    
    /**
     * Clears the console.
     */
    public static void design() {
        System.out.println("======================================================================================================================");
    }

     /**
     * Displays a design in the console.
     */
    public static void clearConsole(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    
    /**
     * Displays the main choices in the console.
     */
    public static void displayMainChoices(){
        TextDisplay.clearConsole();
        TextDisplay.design();
        System.out.println("Select which feature to use:");
        System.out.println("\t[1] Create Hotel");
        System.out.println("\t[2] View Hotel");
        System.out.println("\t[3] Manage Hotel");
        System.out.println("\t[4] Simulate Booking");
        System.out.println("\t[0] Exit Program");
        TextDisplay.design();
    }

    /**
     * Displays a list of all available hotels in the console.
     *
     * @param hotels the list of hotels
     */
    public static void displayViewHotelList(List<Hotel> hotels){
        TextDisplay.design();
            System.out.println("Here is a list of all available Hotels: ");
            
            for (int i = 0; i < hotels.size(); i++){
                System.out.println("\t[" + (i+1) + "] " + hotels.get(i).getName());
                System.out.print("\n");
            }
    }
 
    /**
     * Displays the information of a specific hotel in the console.
     *
     * @param choice the choice of the hotel
     * @param hotels the list of hotels
     */
    public static void displayHotelInformation(int choice, List<Hotel> hotels){
        TextDisplay.design();
        System.out.println("Hotel Name: " + hotels.get(choice-1).getName());
        System.out.println("Number of rooms: " + hotels.get(choice-1).getRooms().size());
        System.out.println("Estimated earnings: " + "P" + hotels.get(choice-1).getTotalIncome());
        TextDisplay.design();
        System.out.println("Select an option:");
        System.out.println("\t[1] View Which Rooms are Available");
        System.out.println("\t[2] View Specific Room Details");
        System.out.println("\t[3] View Specific Reservation Details");
        System.out.println("\t[0] Go Back");
    
    }

    /**
     * Displays the options to manage a specific hotel in the console.
     *
     * @param choice the choice of the hotel
     * @param hotels the list of hotels
     */
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

    /**
     * Displays the details of a reservation in the console.
     *
     * @param booking the reservation
     */
    public static void displayGuestDetails(Booking booking){
        TextDisplay.design();
        System.out.println("Guest Name: " + booking.getGuestName());
        System.out.println("Staying at Room number: " + booking.getRoom().getName());
        System.out.println("Check-In: " + booking.getCheckIn());
        System.out.println("Check-Out: " + booking.getCheckOut());
        System.out.println("Base Room fee: " + booking.getRoom().getPrice());
        System.out.println("Total Booking fee: " + booking.getTotalPrice());
    }

}