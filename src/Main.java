import java.util.*;
/**
 * Represents the main class of the program.
 *
 * <p>This class initializes the list of hotels and bookings, and creates instances of the `ViewHotel` and `ManageHotel` classes.
 * It then enters a loop where it displays the main choices and performs the corresponding actions based on the user's input.
 *
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */
public class Main{
    /**
     * The list of hotels.
     */
    private static List<Hotel> hotels = new ArrayList<>();
    
    /**
     * The list of bookings.
     */
    private static List<Booking> bookings = new ArrayList<>();

    /**
     * The main method of the program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args){
        ViewHotel viewHotel = new ViewHotel();
        ManageHotel manageHotel = new ManageHotel();
        System.out.println("Welcome to the Hotel Reservation System!");
        
        int choice = -1; 
        
        // start of the program
        do{
            TextDisplay.displayMainChoices();
            choice = InputLogic.readInt("Choose: ", 0, 4);  // gets user input. Refer to the InputLogic for more details

            if (choice == 1){
                Hotel hotel = Hotel.createHotel();
                hotels.add(hotel);
            }

            else if(choice == 2){
                viewHotel.viewSpecificHotel(hotels);
            }

            else if(choice == 3){
                manageHotel.manageSpecificHotel(hotels);
            }

            else if(choice == 4){
                Booking.createBooking(hotels);
            }
            /* Used for debugging. Prints out the hashsets used for booking the room.
            else if(choice == 5){
                System.out.println(getHotels().get(0).getRooms().get(0).getReservations());
                InputLogic.readString("Press enter to continue...");
            }*/
        }while(choice != 0);

        InputLogic.closeScanner();
    }

    
    /**
     * This method returns a list of hotels.
     * @return List<Hotel> A list of hotels
     */
    public static List<Hotel> getHotels(){
        return hotels;
    }

    /**
     * This method sets the list of hotels.
     * @param hotels List<Hotel> The list of hotels to set
     */
    public static void setHotels(List<Hotel> hotels){
        Main.hotels = hotels;
    }

    /**
     * This method returns a list of bookings.
     * @return List<Booking> A list of bookings
     */
    public static List<Booking> getBookings(){
        return bookings;
    }

    /**
     * This method sets the list of bookings.
     * @param bookings List<Booking> The list of bookings to set
     */
    public static void setBookings(List<Booking> bookings){
        Main.bookings = bookings;
    }
    
}

    
