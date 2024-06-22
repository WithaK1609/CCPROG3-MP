import java.util.ArrayList;
import java.util.Scanner;

public class Hotel{
    private String name;
    private static ArrayList<Hotel> hotels = new ArrayList<>();
    private ArrayList<Room> rooms;

    // Constructor
    public Hotel(String name){
        this.name = name;
        this.rooms = new ArrayList<Room>();
        hotels.add(this);
    }
    
    // Getters and Setters
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public ArrayList<Room> getRooms(){ 
        return rooms;
    }

    // makes sure that the capacity of rooms does not exceed 50
    public boolean checkMaxCapacity(){
        return rooms.size() == 50;
    }

    // WIP
    public static Hotel createHotel() {
        String hotelName = InputLogic.readString("Enter the name of the new hotel:");
        int numberOfRooms = InputLogic.readInt("Enter how many rooms this hotel will have: ", 1, 50);
        String roomName = InputLogic.readString("Enter Room Name. (NOTE: Every room will start with this name): ");
    
        Hotel hotel = new Hotel(hotelName);
        for (int i = 0; i < numberOfRooms; i++) {
            hotel.getRooms().add(new Hotel.Room(roomName, numberOfRooms));
        }
        
        hotels.add(hotel); // Add the hotel to the ArrayList
        
        return hotel;
    }

    public void viewHotel(){

    }

    public void manageHotel(){

    }
}