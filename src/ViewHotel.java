import java.util.*;
/**
 * Represents a utility class for viewing hotel information.
 *
 * <p>This class provides methods to view the availability of rooms, room details, reservation details, and a specific hotel.
 *
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */
public class ViewHotel{

    /**
     * Displays the availability of rooms in a hotel for a specified date range.
     *
     * @param hotel the hotel to view availability for
     */
    public void viewAvailabilityofRooms(Hotel hotel){
        TextDisplay.design();
        // Enter Check-In and Check-Out Dates
        int checkInDate = InputLogic.readInt("Enter Check-In date: ", 1, 30);
        int checkOutDate = InputLogic.readInt("Enter Check-Out date: ", (checkInDate + 1), 31);
        
        // Print number of booked rooms and available rooms within the specified dates
        System.out.println("Number of booked rooms with specified dates: " + hotel.numberOfReservedRooms(checkInDate, checkOutDate));
        System.err.println("Available Rooms with specified dates: " + hotel.numberofAvailableRooms(checkInDate, checkOutDate));
        TextDisplay.design();
    }

    /**
     * Displays the details of a specific room in a hotel.
     *
     * @param hotel the hotel to view room details for
     */
    public void viewRoomDetails(Hotel hotel){
        TextDisplay.design();
        hotel.printAllRooms();      // prints all rooms
        TextDisplay.design();
        int chosenRoom = InputLogic.readInt("Which Room Info would you like to check? ", 1, hotel.getRooms().size());
        Room specificRoom = hotel.getRooms().get(chosenRoom - 1);       // gets the chosen room
        TextDisplay.design();
        ArrayList<Integer> roomAvailableDates = hotel.availableRoomDates(specificRoom);     // gets the available dates from the Hotel class for the chosen room
        System.out.println(specificRoom.toString());
        
        if (roomAvailableDates.size() == 0){     // no available dates
            TextDisplay.design();
            System.out.println("No available dates for this room!");
            return;
        }
        System.out.println("Number of available dates: ");
        for (int i = 0; i < roomAvailableDates.size(); i++){
            System.out.print(roomAvailableDates.get(i) + " ");
        }
        System.out.println("");
    }

    /**
     * Displays the reservation details for a specific hotel.
     *
     * @param hotel the hotel to view reservation details for
     */
    public void viewReservationDetails(Hotel hotel){
        TextDisplay.design();
        
        if (hotel.getReservationDetails().size() == 0){     // no reservations made
            TextDisplay.design();
            System.out.println("No reservations have been made yet!");
            return;
        }

        hotel.printAllReservations();       // prints all reservations
        int chosenReservation = InputLogic.readInt("Which Reservation Info would you like to check? ", 1, hotel.getReservationDetails().size());
        Booking specificReservation = hotel.getReservationDetails().get(chosenReservation - 1);     // gets the chosen reservation
        TextDisplay.displayGuestDetails(specificReservation);   // prints the details of the chosen reservation
        TextDisplay.design();
    }

    /**
     * Displays the details of a specific hotel.
     *
     * @param hotels a list of hotels to choose from
     */
    public void viewSpecificHotel(List<Hotel> hotels){
        
        int choice = -1;

        while(true)
        {
            if (hotels.size() == 0){        // if no hotels have been created
                TextDisplay.design();
                System.out.println("No hotels have been created yet!");
                InputLogic.readString("Press enter to continue...");
                break;
            }

            TextDisplay.clearConsole();
            TextDisplay.displayViewHotelList(hotels);
            System.out.println("Please select a hotel to view its details (0 to EXIT): ");
            TextDisplay.design();
            
            choice = InputLogic.readInt("Hotel Number: ", 0, hotels.size());
            
            if (choice == 0){
                break;
            }
            Hotel selectedHotel = hotels.get(choice - 1);   // gets the selected hotel
            
            while (true){
                TextDisplay.clearConsole();
                TextDisplay.displayHotelInformation(choice, hotels);       // prints the details of the selected hotel
                TextDisplay.design();
                
                int chooseViewFunction = InputLogic.readInt("Choose: ", 0, 3);
        
                switch (chooseViewFunction){
                    case 1:
                        viewAvailabilityofRooms(selectedHotel);     // prints the availability of rooms
                        InputLogic.readString("Press enter to continue...");
                        break;
                    case 2:
                        viewRoomDetails(selectedHotel);            // prints the room details
                        InputLogic.readString("Press enter to continue...");
                        break;
                    case 3:
                        viewReservationDetails(selectedHotel);      // prints the reservation details
                        InputLogic.readString("Press enter to continue...");
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            
        }

    }
}