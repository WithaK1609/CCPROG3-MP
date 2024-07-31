package Controller;
import java.util.ArrayList;
import hotel.Hotel;
import main.InputLogic;
import rooms.Room;
public class ViewHotelController {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public String viewHighLevelInfo (Hotel selectedHotel) {
        return "Hotel Name: " + selectedHotel.getName() + "\n" +
                "Number of rooms: " + selectedHotel.getRooms().size() + "\n" +
                "Estimated earnings: P" + String.format("%.2f", selectedHotel.getTotalIncome());
    }

    
    public String viewWhichRoomsareAvailable(Hotel selectedHotel, String checkIn, String checkOut) {
        int checkInDate = Integer.parseInt(checkIn);
        int checkOutDate = Integer.parseInt(checkOut);
        return "Number of booked rooms with specified dates: " + selectedHotel.numberOfReservedRooms(checkInDate, checkOutDate) + "\n" +
                "Available Rooms with specified dates: " + selectedHotel.numberofAvailableRooms(checkInDate, checkOutDate);
    }
    
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
