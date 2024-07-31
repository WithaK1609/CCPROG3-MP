/**
 * Represents a the controller class for managing the viewHotelGUI windows.
 * <p> This class provides methods to view the high level information of a hotel, check which rooms are available
 * <p> view specific details of a room and view booking details.
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */

package Controller;
import java.util.ArrayList;
import main.InputLogic;
import hotel.Hotel;
import rooms.Room;
public class ViewHotelController {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Displays the high level information of a hotel.
     * @param selectedHotel
     * @return String
     */
    public String viewHighLevelInfo (Hotel selectedHotel) {
        return "Hotel Name: " + selectedHotel.getName() + "\n" +
                "Number of rooms: " + selectedHotel.getRooms().size() + "\n" +
                "Estimated earnings: P" + String.format("%.2f", selectedHotel.getTotalIncome());
    }

    
    /**
     * Displays which rooms are available.
     * @param selectedHotel
     * @param checkIn
     * @param checkOut
     * @return String
     */
    public String viewWhichRoomsareAvailable(Hotel selectedHotel, String checkIn, String checkOut) {
        int checkInDate = Integer.parseInt(checkIn);
        int checkOutDate = Integer.parseInt(checkOut);
        return "Number of booked rooms with specified dates: " + selectedHotel.numberOfReservedRooms(checkInDate, checkOutDate) + "\n" +
                "Available Rooms with specified dates: " + selectedHotel.numberofAvailableRooms(checkInDate, checkOutDate);
    }
    
    /**
     * Validates the booking dates.
     * @param checkin
     * @param checkout
     * @return true if the booking dates are valid, false otherwise
     */
    public boolean validateBookingDates(String checkin, String checkout) {
        boolean isCheckInValid = InputLogic.validateInt(checkin, 1, 30);

        if (!isCheckInValid) {
            errorMessage = "Please enter a valid check-in date.";
            return false;
        }
        
        int checkInDate = Integer.parseInt(checkin);
        boolean isCheckOutValid = InputLogic.validateInt(checkout, checkInDate + 1, 31);

        if (!isCheckOutValid) {
            errorMessage = "Please enter a valid check-out date.";
            return false;
        }

        return true;
    }
    /**
     * Displays the specific details of a room.
     * @param selectedHotel
     * @param room
     * @return String
     */
    public String viewSpecificRoomDetails(Hotel selectedHotel, Room room) {
        ArrayList<Integer> roomAvailableDates = selectedHotel.availableRoomDates(room);     // gets the available dates from the Hotel class for the chosen room
        
        if (roomAvailableDates.size() == 0){     // no available dates
            return "No available dates for this room!";
        }

        StringBuilder result = new StringBuilder();
        result.append("Room Name: " + room.toString()).append("\n");
        result.append("Room Price: " + room.getPrice()).append("\n");
        result.append("Number of available dates: \n");
        for (int i = 0; i < roomAvailableDates.size(); i++) {
            result.append(roomAvailableDates.get(i)).append(" ");
        }
        result.append("\n");
        return result.toString();
    }
}
