/**
 * Represents a room in a hotel.
 *
 * <p>A room has a name, a price, and a set of reservations. The class provides methods to get and set the name, price, and availability of the room, as well as to get the set of reservations.
 *
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */
import java.util.*;
public class Room {

    protected String name;
    protected String description;
    protected int capacity;
    protected double price;
    //private boolean isAvailable;       // this is not being used but might be useful for part 2
    protected Set<Integer> reservations; // Key: booked day
    protected DatePriceModifier datePriceModifier;
    
    // Constructor
    public Room(String name, String description, int capacity, double price) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.price = price;
        this.reservations = new HashSet<>();
        this.datePriceModifier = new DatePriceModifier();
    }
    
    /**
     * Creates a new Room with the specified name and price.
     *
     * @param name the name of the room
     */
    // Constructor with name parameter
    public Room(String name){
        this.name = name;
        this.description = "This is a base room";
        this.price = RoomPricer.BaseRoomPrice(1299.99);
        this.capacity = 2;
        this.reservations = new HashSet<>();
        this.datePriceModifier = new DatePriceModifier();
    }

    // Constructor with price parameter
    public Room(String name, double price){
        this.name = name;
        this.description = "This is a base room";
        this.price = RoomPricer.BaseRoomPrice(price);
        this.capacity = 2;
        this.reservations = new HashSet<>();
        this.datePriceModifier = new DatePriceModifier();
    }


    // Getters and Setters
    /**
     * Gets the name of the room.
     *
     * @return the name of the room
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the room.
     *
     * @param name the new name of the room
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the price of the room.
     *
     * @return the price of the room
     */
    public double getPrice(){
        return price;
    }
    
    /**
     * Gets the date price modifier.
     *
     * @return returns the date price modifier
     */

    public DatePriceModifier getDatePriceModifier() {
        return datePriceModifier;
    }

    /**
     * Sets the price of the room.
     *
     * @param price the new price of the room
     */
    public void setPrice(double price){
        this.price = price;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Checks if the room is available.
     *
     * @return true if the room is available, false otherwise
     
    public boolean isAvailable(){
        return isAvailable;
    }*/

    /**
     * Sets the availability of the room.
     *
     * @param isAvailable the new availability of the room
     
    public void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }*/

    /**
     * Gets the set of reservations for the room.
     *
     * @return the set of reservations for the room
     */
    public Set<Integer> getReservations() {
        return reservations;
    }

     /**
     * Sets the reservations for the room.
     *
     * This method replaces the existing set of reservations with the provided set.
     *
     * @param reservations the new set of reservations for the room
     */
    public void setReservations(Set<Integer> reservations) {
        this.reservations = reservations;
    }



     /**
     * Returns a string representation of the room.
     *
     * @return a string representation of the room
     */
    @Override
    public String toString() {
        return "Room Name: " + this.name + "\n" + "Description: " + this.description + "\n" + "Price: " + this.price + "\n" + "Capacity: " + this.capacity + "\n";
    }

}