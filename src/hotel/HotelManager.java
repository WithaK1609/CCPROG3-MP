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
        if (numberofBaseRooms > 10){    // For over 10 rooms
            int hotelFloors = numberofBaseRooms / 10;
            int createdRooms = 0;
            while (createdRooms < numberofBaseRooms){
                for (int i = 1; i <= hotelFloors && createdRooms < numberofBaseRooms; i++) {
                    for (int j = 1; j <= 10 && createdRooms < numberofBaseRooms; j++) {
                        if (j == 10)
                            hotel.getRooms().add(new Room(("B" + i + "" + j)));
                        else
                            hotel.getRooms().add(new Room(("B" + i + "0" + j)));
                        createdRooms++;
                    }
                }
            }
        }

        // Create rooms based on number of rooms with naming scheme of 101, 102, 103, etc. For under 10 rooms
        else{
            for (int i = 1; i <= numberofBaseRooms; i++) {
                if(i < 10)
                    hotel.getRooms().add(new Room("B" + "10" + i));
                else
                    hotel.getRooms().add(new Room("B" + "1" + i));

            }
        }

        // Deluxe Rooms
        if (numberOfDeluxeRooms > 10){
            int hotelFloors = numberOfDeluxeRooms / 10;
            int createdRooms = 0;
            while (createdRooms < numberOfDeluxeRooms){
                for (int i = 1; i <= hotelFloors && createdRooms < numberOfDeluxeRooms; i++) {
                    for (int j = 1; j <= 10 && createdRooms < numberOfDeluxeRooms; j++) {
                        if (j == 10)
                            hotel.getRooms().add(new DeluxeRoom(("D" + i + "" + j)));
                        else
                            hotel.getRooms().add(new DeluxeRoom(("D" + i + "0" + j)));
                        createdRooms++;
                    }
                }
            }
        }

        else{
            
            for (int i = 1; i <= numberOfDeluxeRooms; i++) {
                if(i < 10)
                    hotel.getRooms().add(new DeluxeRoom("D" +  "10" + i));
                else
                    hotel.getRooms().add(new DeluxeRoom("D" +  "1" + i));

            }
        }

        // Executive Rooms
        if (numberOfExecutiveRooms > 10){
            int hotelFloors = numberOfExecutiveRooms / 10;
            int createdRooms = 0;
            while (createdRooms < numberOfExecutiveRooms){
                for (int i = 1; i <= hotelFloors && createdRooms < numberOfExecutiveRooms; i++) {
                    for (int j = 1; j <= 10 && createdRooms < numberOfExecutiveRooms; j++) {
                        if (j == 10)
                            hotel.getRooms().add(new ExecutiveRoom(("E" + i + "" + j)));
                        else
                            hotel.getRooms().add(new ExecutiveRoom(("E" + i + "0" + j)));
                        createdRooms++;
                    }
                }
            }
        }

        else{
            
            for (int i = 1; i <= numberOfExecutiveRooms; i++) {
                if(i < 10)
                    hotel.getRooms().add(new ExecutiveRoom("E" + "10" + i));
                else
                    hotel.getRooms().add(new ExecutiveRoom("E" + "1" + i));

            }
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