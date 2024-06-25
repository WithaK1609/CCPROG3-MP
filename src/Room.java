/**
 * Represents a room in a hotel.
 *
 * <p>A room has a name, a price, and a set of reservations. The class provides methods to get and set the name, price, and availability of the room, as well as to get the set of reservations.
 *
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */
import java.util.*;
public class Room{
    private String name;
    private double price;
    private boolean isAvailable;
    private Set<Integer> reservations; // Key: booked day

    /**
     * Creates a new Room with the specified name and price.
     *
     * @param name the name of the room
     */
    // Constructor
    public Room(String name){
        this.name = name;
        this.price = 1299.00;
        this.isAvailable = true;
        this.reservations = new HashSet<>();
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
     * Sets the price of the room.
     *
     * @param price the new price of the room
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * Checks if the room is available.
     *
     * @return true if the room is available, false otherwise
     */
    public boolean isAvailable(){
        return isAvailable;
    }

    /**
     * Sets the availability of the room.
     *
     * @param isAvailable the new availability of the room
     */
    public void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

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
        return "Room " + name + ": Price " + price;
    }

}