package hotel;
import java.util.*;

import booking.Booking;
import rooms.Room;
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
     * Sets the list of rooms in the hotel.
     *
     * @param rooms the new list of rooms in the hotel
     */
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
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
        return String.format("Hotel Name: %-40s | Number of Rooms: %d", getName(), getRooms().size());
    }

    /** 
     * Checks if hotel name is unique.
     * @param name - name of the hotel
     * @param hotels - list of hotels
     * @return boolean
     */
    public boolean isHotelNameUnique(String name, List<Hotel> hotels){
       for (Hotel hotel : hotels) {
            if (hotel.getName().equalsIgnoreCase(name)){
                return false;
            }
        }
        return true;
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
     * @param checkIn check in date
     * @param checkOut check out date
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
     * @param room  room to reserve
     * @param checkIn check in date 
     * @param checkOut check out date
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