import java.util.*;

/**
 * Represents a utility class for managing hotel information.
 *
 * <p>This class provides methods to change the hotel name, add rooms, remove rooms, update room prices, remove reservations, and remove the hotel.
 *
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */

public class ManageHotel{
    /**
     * Manages a specific hotel. 
     * 
     * @param hotels - list of hotels
     * @return void
     */
   
    public void manageSpecificHotel(List<Hotel> hotels){
        int choice = -1;

        while(true){
            if (hotels.size() == 0){
                TextDisplay.design();
                System.out.println("No hotels to manage!");
                InputLogic.readString("Press enter to continue...");
                break;
            }           

            
            TextDisplay.clearConsole();
            TextDisplay.displayViewHotelList(hotels);
            System.out.println("Please select a hotel to manage (0 to EXIT): ");
            TextDisplay.design();           
            
            
            choice = InputLogic.readInt("Hotel Number: ", 0, hotels.size());
            
            if (choice == 0){
                break;
            }
            
            Hotel selectedHotel = hotels.get(choice - 1);

            while (true) {
                TextDisplay.clearConsole();
                TextDisplay.displayManageHotelOptions(choice, hotels);            
                TextDisplay.design();

                int chooseViewFunction = InputLogic.readInt("Choose: ", 0, 6);
                switch (chooseViewFunction) {
                    case 1:
                        changeHotelName(selectedHotel);
                        break;

                    case 2:                 
                        addHotelRoom(selectedHotel);
                        break;

                    case 3:                    
                        removeHotelRoom(selectedHotel);
                        break;

                    case 4:
                        updateRoomPrice(selectedHotel);
                        break;

                    case 5:
                        removeReservation(selectedHotel);
                        break;

                    case 6:
                        removeHotel(hotels, selectedHotel);
                        break;

                    case 0:
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

    }

    /**
     * Changes the name of the hotel.
     * 
     * @param hotel hotel to change name
     */

    public void changeHotelName(Hotel hotel) {
        String newName = InputLogic.readString("Enter the new hotel name: ");
        List<Hotel> hotels = Main.getHotels();

        if(newName.equals(hotel.getName())){
            System.out.println("The new name is the same as the current name.");
            InputLogic.readString("Press enter to continue...");
            return;
        }

        for (Hotel h : hotels) {
            if (h.getName().equalsIgnoreCase(newName)) {
                System.out.println("A hotel with this name already exists.");
                InputLogic.readString("Press enter to continue...");
                return;
            }
        }

        int confirm = InputLogic.readInt("Are you sure you want to change the name of the hotel " + hotel.getName() + "? (0 - No, 1 - Yes): ", 0, 1);
        if (confirm == 0) {
            System.out.println("Operation canceled.");
            InputLogic.readString("Press enter to continue...");
            return;
        }

        hotel.setName(newName); // sets the new name after confirmation
        System.out.println("Hotel name changed successfully.");
        InputLogic.readString("Press enter to continue...");
    }

    /**
     * Adds a <user input> amount of 
     * rooms to the hotel.
     * 
     * @param hotel hotel to add rooms for
     *
     */

     public void addHotelRoom(Hotel hotel) {
        int numRoomsToAdd = InputLogic.readInt("Enter the number of rooms to add: ", 1, 50);
        List<Room> rooms = hotel.getRooms();
        int currentRoomCount = rooms.size();
        
        // Checker if the hotel has reached the maximum capacity of 50 rooms
        if (currentRoomCount + numRoomsToAdd > 50) {
            System.out.println("Cannot add " + numRoomsToAdd + " rooms. The hotel can only have a maximum of 50 rooms.");
            System.out.println("You can add up to " + (50 - currentRoomCount) + " more rooms.");
            InputLogic.readString("Press any key to go back...");
            return;
        }
        
        // Confirmation checker
        int confirm = InputLogic.readInt("Are you sure you want to add " + numRoomsToAdd + " rooms? (0 - No, 1 - Yes): ", 0, 1);
        if (confirm == 0) {
            System.out.println("Operation canceled.");
            InputLogic.readString("Press enter to continue...");
            return;
        }
    
        // Start from the current floor and room number
        int currentFloor = currentRoomCount / 10;
        int currentRoomNumber = currentRoomCount % 10 + 1;
    
        for (int i = 0; i < numRoomsToAdd; i++) {
            boolean roomExists = false;
            String newRoomName = (currentFloor + 1) + String.format("%02d", currentRoomNumber);
            
            // Check if room already exists
            for (Room room : rooms) {
                if (room.getName().equals(newRoomName)) {
                    roomExists = true;
                    break;
                }
            }
            
            if (!roomExists) {
                rooms.add(new Room(newRoomName));
            }
    
            // Update room number and floor
            currentRoomNumber++;
            if (currentRoomNumber > 10) {
                currentFloor++;
                currentRoomNumber = 1;
            }
        }
    
        System.out.println(numRoomsToAdd + " rooms added successfully.");
        InputLogic.readString("Press enter to continue...");
    }
    
  
    /**
     * Removes a <user input> amount of rooms from the hotel.
     * As long as there are no active reservations.
     * 
     * @param hotel hotel to remove rooms from
     */

    public void removeHotelRoom(Hotel hotel) {
        int numRoomsToRemove = InputLogic.readInt("Enter the number of rooms to remove: ", 1, 50);
        List<Room> removableRooms = new ArrayList<>(); // Corrected the declaration

        int totalRooms = hotel.getRooms().size();
        int maxRemovableRooms = totalRooms - 1; // Ensure at least one room remains
    
        if (numRoomsToRemove > maxRemovableRooms) {
            System.out.println("Cannot remove " + numRoomsToRemove + " rooms. The hotel must have a minimum of 1 room");
            System.out.println("You can remove up to " + maxRemovableRooms + " more rooms.");
            InputLogic.readString("Press any key to go back...");
            return;
        }

        // Filter out rooms with no reservations
        for (Room room : hotel.getRooms()) {
            if (room.getReservations().isEmpty()) {
                removableRooms.add(room);
            }
        }
    
        if (removableRooms.size() < numRoomsToRemove) {
            System.out.println("Only " + removableRooms.size() + " rooms can be removed as they have no reservations.");
            InputLogic.readString("Press enter to continue...");
            return;
        }
    
        // Confirmation checker
        int confirm = InputLogic.readInt("Are you sure you want to remove " + numRoomsToRemove + " rooms? (0 - No, 1 - Yes): ", 0, 1);
        if (confirm == 0) {
            System.out.println("Operation canceled.");
            InputLogic.readString("Press enter to continue...");
            return;
        }
    
        // Remove the rooms
        for (int i = 0; i < numRoomsToRemove; i++) {
            Room room = removableRooms.get(removableRooms.size() - 1 - i);
            hotel.getRooms().remove(room);
        }


        System.out.println(numRoomsToRemove + " rooms removed successfully.");
        InputLogic.readString("Press enter to continue...");
    }
    
    /**
     * Updates the price of all rooms in the hotel. 
     * As long as there are no active reservations.
     * 
     * @param hotel hotel to update room prices
     * 
     */

    public void updateRoomPrice(Hotel hotel) {
        if (hotel.getReservationDetails().isEmpty()) {
            double newPrice = InputLogic.readDouble("Enter the new room price: ", 100.0, 10000.0);
            for (Room room : hotel.getRooms()) {
                room.setPrice(newPrice);
            }
              
            int confirm = InputLogic.readInt("Are you sure you want to change the base price of the rooms in Hotel " + hotel.getName() + "? (0 - No, 1 - Yes): ", 0, 1);
            if (confirm == 0) {
                System.out.println("Operation canceled.");
                InputLogic.readString("Press enter to continue...");
                return;
            }
            else 
                System.out.println("Room prices updated successfully.");  

        } else {
            System.out.println("Cannot update room prices. There are active reservations.");
        }
        InputLogic.readString("Press enter to continue...");
    }

    /**
     * Removes a reservation from the hotel.
     * 
     * @param hotel hotel to remove reservation from
     * 
     */

    public void removeReservation(Hotel hotel) {
        List<Booking> bookings = hotel.getReservationDetails();

        if (bookings.isEmpty()) {
            System.out.println("No reservations to remove.");
            InputLogic.readString("Press enter to continue...");
            return;
        }

        int bookingNumber = 1;
        System.out.println("List of reservations:");
        for (Booking booking : bookings) {
            System.out.println(bookingNumber++ + ". Guest Name: " + booking.getGuestName() +
                               ", Room: " + booking.getRoom().getName() +
                               ", Check-In: " + booking.getCheckIn() +
                               ", Check-Out: " + booking.getCheckOut());
        }

        int reservationNumber = InputLogic.readInt("Enter the number of the reservation to remove (0 to cancel): ", 0, bookings.size());
        
        if (reservationNumber == 0) {
            System.out.println("Operation canceled.");
            InputLogic.readString("Press enter to continue...");
            return;
        }

        Booking bookingToRemove = bookings.get(reservationNumber - 1);
        Room room = bookingToRemove.getRoom();

        // update room availability
        for (int i = bookingToRemove.getCheckIn(); i <= bookingToRemove.getCheckOut(); i++) {
            room.getReservations().remove((Integer) i);
        }
        room.setAvailable(true);

        // remove the booking from the hotel's booking list
        bookings.remove(bookingToRemove);

        System.out.println("Reservation removed successfully.");
        InputLogic.readString("Press enter to continue...");
    }

    /**
     * Removes a hotel from the list of hotels.
     * 
     * @param hotels list of hotels
     * @param hotel hotel to remove from the list
     * 
     */

    public void removeHotel(List<Hotel> hotels, Hotel hotel) {

        int confirm = InputLogic.readInt("Are you sure you want to remove the hotel " + hotel.getName() + "? (0 - No, 1 - Yes): ", 0, 1);
        if (confirm == 0) {
            System.out.println("Operation canceled.");
            InputLogic.readString("Press enter to continue...");
            return;
        }
        hotels.remove(hotel);
        System.out.println("Hotel removed successfully.");
        InputLogic.readString("Press enter to continue...");

    }


}
