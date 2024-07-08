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
     * The main method of the program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args){
        HotelManager hotelManager = new HotelManager();
        ViewHotel viewHotel = new ViewHotel();
        ManageHotel manageHotel = new ManageHotel();
        System.out.println("Welcome to the Hotel Reservation System!");
        
        int choice = -1; 
        
        // start of the program
        do{
            TextDisplay.displayMainChoices();
            choice = InputLogic.readInt("Choose: ", 0, 4);  // gets user input. Refer to the InputLogic for more details

            if (choice == 1){
                hotelManager.createHotel();
            }

            else if(choice == 2){
                viewHotel.viewSpecificHotel(hotelManager.getHotels());
            }

            else if(choice == 3){
                manageHotel.manageSpecificHotel(hotelManager.getHotels());
            }

            else if(choice == 4){
                Booking.createBooking(hotelManager.getHotels());
            }
            /* Used for debugging. Prints out the hashsets used for booking the room.
            else if(choice == 5){
                System.out.println(getHotels().get(0).getRooms().get(0).getReservations());
                InputLogic.readString("Press enter to continue...");
            }*/
        }while(choice != 0);

        InputLogic.closeScanner();
    }    
}

    
