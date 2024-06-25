import java.util.*;

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
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public List<Room> getRooms(){ 
        return rooms;
    }

    public List<Booking> getReservationDetails(){
        return reservationDetails;
    }
    
    public boolean checkMaxCapacity(){
        return rooms.size() == 50;  // makes sure that the capacity of rooms does not exceed 50
    }
    
    public String toString(){
        return "Hotel Name: " + getName() + "\nNumber of Rooms: " + getRooms().size();
    }


    public static Hotel createHotel() {
        TextDisplay.design();
        String hotelName = InputLogic.readString("Enter the hotel name: ");
        int numberOfRooms = InputLogic.readInt("Enter how many rooms this hotel will have: ", 1, 50);

        Hotel hotel = new Hotel(hotelName);     // instantiate new hotel

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
    
        return hotel;
    }
    
    
    /** 
     * Checks if room is available.
     * @param room
     * @param checkIn
     * @param checkOut
     * @return boolean
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
     * Finds available room.
     * @param checkIn
     * @param checkOut
     * @return Room
     */
    public Room findAvailableRoom(int checkIn, int checkOut){
        for (Room room : rooms) {
            if (isRoomAvailable(room, checkIn, checkOut)){
                return room;    // check if room is available and return it
            }
        }
        return null; // no room available
    }

    
    /** 
     * Prints available rooms.
     * @param checkIn
     * @param checkOut
     */
    public void printAvailableRooms(int checkIn, int checkOut) {
        for (Room room : rooms) {
            if (isRoomAvailable(room, checkIn, checkOut)){
                System.out.println(room);
            }
        }
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
    }

    
    /** 
     * Confirms booking.
     * @param booking
     */
    public void confirmBooking(Booking booking){
        reservationDetails.add(booking);    // add booking to list of the hotel class
        reserveRoom(booking.getRoom(), booking.getCheckIn(), booking.getCheckOut());    // call the reserve room method
    }
}