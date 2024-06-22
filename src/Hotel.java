import java.util.ArrayList;
import java.util.Scanner;

public class Hotel{
    private String name;
    private static ArrayList<Hotel> hotels = new ArrayList<>();
    private ArrayList<Room> rooms;

    // Constructor
    public Hotel(){
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

    public static ArrayList<Hotel> getHotels(){
        return hotels;
    }

    // makes sure that the capacity of rooms does not exceed 50
    public boolean checkMaxCapacity(){
        return rooms.size() == 50;
    }

    // WIP
    public static Hotel createHotel() {
        System.out.print("\033c");
        String hotelName = InputLogic.readString("Enter the hotel name: ");
        int numberOfRooms = InputLogic.readInt("Enter how many rooms this hotel will have: ", 1, 50);
        String roomName = InputLogic.readString("Enter Room Name. (NOTE: Every room will start with this name): ");
    
        Hotel hotel = new Hotel();
        hotel.setName(hotelName); // Set the hotel name
        for (int i = 0; i < numberOfRooms; i++) {
            hotel.getRooms().add(new Room(roomName, numberOfRooms)); // not sure if the room names are named correctly
        }
    
        return hotel;
    }

    public void viewHotel(){
        System.out.print("\033c");
        System.out.println("Hotel Name: " + getName());
        System.out.println("Number of Rooms: " + getRooms().size());
    
        System.out.println("Select an option:");
        System.out.println("\t[1] View Room Details");
        System.out.println("\t[2] View Reservations");
        System.out.println("\t[0] Go Back");
    
        int choice = InputLogic.readInt("Choose: ", 0, 2);
    
        switch (choice) {
            case 1:
                //viewRoomDetails();
                break;
            case 2:
                //viewReservations();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void manageHotel(){

    }

    public void viewRoomDetails(){

    }

    public void viewReservations(){

    }
}