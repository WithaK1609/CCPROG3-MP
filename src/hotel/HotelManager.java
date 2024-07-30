package hotel;
/**
 * Represents a utility class for managing a hotel.
 * <p> This class provides methods to create a hotel. It also provides methods to get and set the list of hotels.
 *
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */
import java.util.*;
import rooms.DeluxeRoom;
import rooms.ExecutiveRoom;
import rooms.Room;

public class HotelManager{
    private static HotelManager instance;
    private List<Hotel> hotels;     // list of hotels

    // Constructor
    private HotelManager() {
        hotels = new ArrayList<>();
    }

    // Ensures there is only one instance. Singleton pattern
    public static synchronized HotelManager getInstance() {
        if (instance == null) {
            instance = new HotelManager();
        }
        return instance;
    }
    
    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = new ArrayList<>(hotels);
    }

    /*
     * This methods handles the creation of rooms based on number of rooms with naming scheme of 101, 102, 103, etc.
     */
    
    public void addRooms(int numberofBaseRooms, int numberOfDeluxeRooms, int numberOfExecutiveRooms, Hotel hotel) {
        // Base Rooms
        int existingBaseRooms = (int) hotel.getRooms().stream().filter(room -> room.getName().startsWith("B")).count();
        for (int i = 1; i <= numberofBaseRooms; i++) {
            int roomNumber = existingBaseRooms + i;
            String roomName = String.format("B%02d", roomNumber);
            hotel.getRooms().add(new Room(roomName));
        }
    
        // Deluxe Rooms
        int existingDeluxeRooms = (int) hotel.getRooms().stream().filter(room -> room.getName().startsWith("D")).count();
        for (int i = 1; i <= numberOfDeluxeRooms; i++) {
            int roomNumber = existingDeluxeRooms + i;
            String roomName = String.format("D%02d", roomNumber);
            hotel.getRooms().add(new DeluxeRoom(roomName));
        }
    
        // Executive Rooms
        int existingExecutiveRooms = (int) hotel.getRooms().stream().filter(room -> room.getName().startsWith("E")).count();
        for (int i = 1; i <= numberOfExecutiveRooms; i++) {
            int roomNumber = existingExecutiveRooms + i;
            String roomName = String.format("E%02d", roomNumber);
            hotel.getRooms().add(new ExecutiveRoom(roomName));
        }
    }
    
    /** 
     * This methods handles how to create a hotel.
     * 
     * @param hotelName - name of the hotel
     * @param numberOfBaseRooms - number of base rooms
     * @param numberOfDeluxeRooms - number of deluxe rooms
     * @param numberOfExecutiveRooms - number of executive rooms
     * 
     */
    public void createHotel(String hotelName, int numberOfBaseRooms, int numberOfDeluxeRooms, int numberOfExecutiveRooms) {
        // declare necessary variables
        Hotel hotel = new Hotel(hotelName);     // instantiate new hotel   
        addRooms(numberOfBaseRooms, numberOfDeluxeRooms, numberOfExecutiveRooms, hotel);   // add rooms to hotel
        addHotel(hotel); // add hotel to list of hotels
    }

    /**
     * This method removes a hotel from the list of hotels.
     * @param hotel
     */

    public void removeHotel(Hotel hotel) {
        hotels.remove(hotel);
    }
}