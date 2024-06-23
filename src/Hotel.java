import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Hotel{
    private String name;
    private List<Room> rooms;
    private Map<String, Set<Integer>> reservations; // Key: Room number, Value: Set of booked days
    
    // Constructor
    public Hotel(String name){
        this.name = name;
        this.rooms = new ArrayList<Room>();
        this.reservations = new HashMap<>();
    }
    
    // Getters and Setters
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public List<Room> getRooms(){ 
        return rooms;
    }

    
    public boolean checkMaxCapacity(){
        return rooms.size() == 50;  // makes sure that the capacity of rooms does not exceed 50
    }
    
    public String toString(){
        return "Hotel Name: " + getName() + "\nNumber of Rooms: " + getRooms().size();
    }


    public static Hotel createHotel() {
        TextDisplay.clearConsole();
        TextDisplay.design();
        String hotelName = InputLogic.readString("Enter the hotel name: ");
        int numberOfRooms = InputLogic.readInt("Enter how many rooms this hotel will have: ", 1, 50);
        //String roomName = InputLogic.readString("Enter Room Name. (NOTE: Every room will start with this name): ");
        
        Hotel hotel = new Hotel(hotelName);


        int hotelFloors = numberOfRooms / 10;
        int createdRooms = 0;
        
        // i just felt like it'd be better if lahat ng hotels would have the same naming 
        // sense instead of user input names para mas clean
        while(numberOfRooms >= createdRooms){
            for (int i = 1; i <= hotelFloors; i++) {
                for (int j = 1; j <= 10; j++) {
                    hotel.rooms.add(new Room((i + "0" + j)));
                    createdRooms++;
                }
            }
        }


        for (int i = 1; i <= numberOfRooms; i++) {
            //hotel.rooms.add(new Room((roomName + (i+1))));
            //hotel.reservations.put((roomName + (i+1)), new HashSet<>()); initialize booking sets for each room
        }
    
        return hotel;
    }
    
    /*  ALL WIP BELOW THIS LINE. Haven't debugged all of these methods yet. Mostly AI generated din to HAHAHAHAH
        Ang idea is may hash map na kung saan nakaset yung booking dates sa Set integer (Set<Integer> bookedDays) 
        tapos tied in sa room name pero mali pa mga logic dito. Hindi din handled pa yung oras. Will fix later*/
    
    private boolean isRoomAvailable(String roomName, int checkIn, int checkOut) {
        if (checkIn == 31 || checkOut == 1 ) {
            return false; // makes sure Check-in cannot be on 31st and check-out cannot be on 1st
        }
        Set<Integer> bookedDays = reservations.get(roomName);
        for (int i = checkIn; i < checkOut; i++) {
            if (bookedDays.contains(i)) {
                return false; // room is not available if any date in the range is booked
            }
        }
        return true;
    }

    public Room findAvailableRoom(String roomName, int checkIn, int checkOut) {
        for (Room room : rooms) {
            if (room.getName().equals(roomName) && isRoomAvailable(room.getName(), checkIn, checkOut)) {
                return room;    // check if room is available and return it
            }
        }
        return null; // no room available
    }
}