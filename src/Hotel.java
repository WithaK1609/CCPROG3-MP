import java.util.*;
/**
 * Represents a hotel.
 *
 * <p>A hotel has a name and a list of rooms. Each room has a name and a list of reservations.
 *
 * <p>The class provides methods to create a hotel, add rooms to a hotel, check if a room is available, and print all rooms in a hotel.
 *
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */
public class Hotel{
    private String name;
    private List<Room> rooms;
    private List<Booking> reservationDetails;

    // Constructor
    public Hotel(String name){
        this.name = name;
        this.rooms = new ArrayList<Room>();
        this.reservationDetails = new ArrayList<Booking>();
    }
    
    
    // Getters and Setters
    /**
     * Returns the name of the hotel.
     *
     * @return the name of the hotel
     */
    public String getName(){
        return name;
    }
    
    /**
     * Sets the name of the hotel.
     *
     * @param name the new name of the hotel
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Returns the list of rooms in the hotel.
     *
     * @return the list of rooms in the hotel
     */
    public List<Room> getRooms(){ 
        return rooms;
    }

    /**
     * Returns the list of reservation details in the hotel.
     *
     * @return the list of reservation details in the hotel
     */

    public List<Booking> getReservationDetails(){
        return reservationDetails;
    }
    
    @Override
    public String toString(){
        return "Hotel Name: " + getName() + "\nNumber of Rooms: " + getRooms().size();
    }

    
    /** 
     * Checks if hotel name is unique.
     * @param name
     * @param hotels
     * @return boolean
     */
    private static boolean isUnique(String name, List<Hotel> hotels){
        for (Hotel hotel : hotels) {
            if (hotel.getName().equalsIgnoreCase(name)){
                return false;
            }
        }
        return true;
    }

    
    /** 
     * This methods handles how to create a hotel.
     * @return Hotel
     */
    public static Hotel createHotel(){
        // declare necessary variables
        Hotel hotel = null;
        boolean hotelCreationConfirm = false;
        int confirmHotel = -1;
        
        while(!hotelCreationConfirm){
            String hotelName = null;
            
            do{
                TextDisplay.design();
                hotelName = InputLogic.readString("Enter the hotel name: ");
                
                if(hotelName.isEmpty()){
                    System.out.println("Please enter a valid hotel name!");
                }

                if (!isUnique(hotelName, Main.getHotels())){    // checks if the hotel name is unique
                    System.out.println("Hotel name already exists!");
                }
            }while(!isUnique(hotelName, Main.getHotels()) || hotelName.isEmpty());
            

            int numberOfRooms = InputLogic.readInt("Enter how many rooms this hotel will have: ", 1, 50);

            hotel = new Hotel(hotelName);     // instantiate new hotel

            // Create rooms based on number of rooms with naming scheme of 101, 102, 103, etc.
            if (numberOfRooms > 10){
                int hotelFloors = numberOfRooms / 10;
                int createdRooms = 0;
                while (createdRooms < numberOfRooms){
                    for (int i = 1; i <= hotelFloors && createdRooms < numberOfRooms; i++) {
                        for (int j = 1; j <= 10 && createdRooms < numberOfRooms; j++) {
                            if (j == 10)
                                hotel.rooms.add(new Room((i + "" + j)));
                            else
                                hotel.rooms.add(new Room((i + "0" + j)));
                            createdRooms++;
                        }
                    }
                }
            }

            // Create rooms based on number of rooms with naming scheme of 101, 102, 103, etc. For under 10 rooms
            else{
                
                for (int i = 1; i <= numberOfRooms; i++) {
                    if(i < 10)
                        hotel.rooms.add(new Room("10" + i));
                    else
                        hotel.rooms.add(new Room("1" + i));

                }
            }
            
            confirmHotel = InputLogic.readInt("Confirm Hotel Creation? (1 - Yes, 0 - No)? ", 0, 1);
            
            if (confirmHotel == 1){     // confirmed creation of hotel
                TextDisplay.design();
                System.out.println("Hotel successfully created!");
                InputLogic.readString("Press enter to exit...");
                hotelCreationConfirm = false;
                return hotel;   // return hotel to be added to arraylist in main
            }
        }
        return null;    // hotel not confirmed
    }
    
    
    /**
     * Checks if a room is available in the hotel.
     *
     * @param room   the room to check
     * @param checkIn the check-in date
     * @param checkOut the check-out date
     * @return true if the room is available, false otherwise
     */
    public boolean isRoomAvailable(Room room, int checkIn, int checkOut) {
        Set<Integer> reservations = room.getReservations();
        for (int i = checkIn; i < checkOut; i++){
            if (reservations.contains(i)){
                return false;
            }
        }

        return true;
    }

    
    /** 
     * Finds available room. This method is not used. However, we kept it as it might be useful for part 2.
     * @param checkIn   
     * @param checkOut
     * @return Room
     *
    public Room findAvailableRoom(int checkIn, int checkOut){
        for (Room room : rooms) {
            if (isRoomAvailable(room, checkIn, checkOut)){
                return room;    // check if room is available and return it
            }
        }
        return null; // no room available
    }*/


    /**
     * Returns a list of available dates for a given room.
     *
     * @param room the room for which to find available dates
     * @return a list of integers representing the available dates
     */
    public ArrayList<Integer> availableRoomDates(Room room){
        Set<Integer> reservations = room.getReservations();
        ArrayList<Integer> availableDates = new ArrayList<>();
        
        for (int i = 1; i <= 30; i++) {
            if (!reservations.contains(i)) {
                availableDates.add(i);
            }
        }

        return availableDates;
    }
    
    /** 
     * Prints ALL rooms.
     * 
     */
    public void printAllRooms() {
        int i = 0;
        for (Room room : rooms) {
            i++;
            System.out.println("[" + i + "] " + room);
        }
    }
    
    /** 
     * Prints available rooms. Based on check in and check out.
     * @param checkIn   check in date
     * @param checkOut  check in date
     */
    public void printAvailableRooms(int checkIn, int checkOut) {
        int i = 0;
        for (Room room : rooms) {
            if (isRoomAvailable(room, checkIn, checkOut)){
                i++;
                System.out.println("[" + i + "] " + room);
            }
        }
    }

    /** 
     * Counts number of reserved rooms.
     * @param checkIn   check in date
     * @param checkOut  check in date
     * @return int
     */
    public int numberOfReservedRooms(int checkIn, int checkOut){
        int i = 0;
        for (Room room : rooms){
            if (!isRoomAvailable(room, checkIn, checkOut)){
                i++;
            }
        }
        return i;
    }

    /** 
     * Counts number of available rooms.
     * @param checkIn   check in date
     * @param checkOut  check in date
     * @return int
     */
    public int numberofAvailableRooms(int checkIn, int checkOut) {
        int i = 0;
        for (Room room : rooms){
            if (isRoomAvailable(room, checkIn, checkOut)){
                i++;
            }
        }
        return i;
    }

    
    /** 
     * Reserves room.
     * @param room  
     * @param checkIn
     * @param checkOut
     */
    public void reserveRoom(Room room, int checkIn, int checkOut){
        Set<Integer> reservations = room.getReservations(); // gets the list of hashset in the room class    
        for (int i = checkIn; i < checkOut; i++){
            reservations.add(i); // add it to the hashset
        }
        reservations.remove(checkOut); // remove the last day so it can be reserved
    }

    
    /** 
     * Confirms booking.
     * @param booking
     */
    
    public void confirmBooking(Booking booking){
        reservationDetails.add(booking);    // add booking to list of the hotel class
        reserveRoom(booking.getRoom(), booking.getCheckIn(), booking.getCheckOut());    // call the reserve room method
    }


    /** 
     * Prints all booking made. Only reveals the guest name.
     * 
     */
    public void printAllReservations(){
        int i = 0;
        for (Booking booking : reservationDetails){
            i++;
            System.out.println("\t[" + i + "] " + booking.getGuestName());
        }
    }

    
    /** 
     * Calculates total income of the hotel using the booking list.
     * @return double
     */
    public double getTotalIncome(){
        double totalIncome = 0;
        for (Booking booking : reservationDetails) {
            totalIncome += booking.getTotalPrice();
        }
        return totalIncome;
    }
}