
package main;
/**
import GUI.TextDisplay;
import GUI.ViewHotel;
import booking.BookingManager;
import hotel.Hotel;
import hotel.HotelManager;
import hotel.ManageHotel;

/**
 * Represents the main class of the program.
 *
 * <p>This class initializes the list of hotels and bookings, and creates instances of the `ViewHotel` and `ManageHotel` classes.
 * <p>It then enters a loop where it displays the main choices and performs the corresponding actions based on the user's input.
 *
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */
@Deprecated
public class Main{

    /**
     * The main method of the program.
     *
     * @param args the command line arguments
     
    public static void main(String[] args){
        //new GUI();
        HotelManager hotelManager = new HotelManager();
        ViewHotel viewHotel = new ViewHotel();
        ManageHotel manageHotel = new ManageHotel();
        BookingManager bookingManager = new BookingManager();
        System.out.println("Welcome to the Hotel Reservation System!");
        
        int choice = -1; 
        
        // start of the program
        do{
            TextDisplay.displayMainChoices();
            choice = InputLogic.readInt("Choose: ", 0, 5);  // gets user input. Refer to the InputLogic for more details
            if (choice == 1){
                //hotelManager.createHotel();
            }

            else if(choice == 2){
                viewHotel.viewSpecificHotel(hotelManager.getHotels());
            }

            else if(choice == 3){
                manageHotel.manageSpecificHotel(hotelManager.getHotels());
            }

            else if(choice == 4){
                //bookingManager.createBooking(hotelManager.getHotels());
            }
            
            else if(choice == 5){
                for(Hotel hotel : hotelManager.getHotels()){
                    System.out.println(hotel.getRooms());
                }
                InputLogic.readString("Press enter to continue...");
            }
        }while(choice != 0);

        InputLogic.closeScanner();
    } */  
}

    
