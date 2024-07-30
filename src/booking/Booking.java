package booking;
import rooms.Room;

/**
 * Represents a booking in a hotel.
 *
 * <p>A booking has a guest name, a room, a check-in and check-out date, and a total price.
 *
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */

public class Booking {
    private int checkIn;
    private int checkOut;
    private Room room;
    private String guestName;
    private double totalPrice;

    /**
     * Constructs a new booking.
     *
     * @param guestName    the name of the guest
     * @param room         the room to be booked
     * @param checkIn      the check-in date
     * @param checkOut     the check-out date
     * @param totalPrice   the total price of the booking
     */
    public Booking(String guestName, Room room, int checkIn, int checkOut, double totalPrice) {
        this.guestName = guestName;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
    }

    // getters
    
    /**
     * Returns the name of the guest.
     *
     * @return the name of the guest
     */
    public String getGuestName(){
        return guestName;
    }

    /**
     * Returns the total price of the booking.
     *
     * @return the total price of the booking
     */
    public double getTotalPrice(){
        return totalPrice;
    }
    
    /**
     * Returns the room associated with the booking.
     *
     * @return the room associated with the booking
     */
    public Room getRoom(){
        return room;
    }

    /**
     * Returns the check-in date of the booking.
     *
     * @return the check-in date of the booking
     */
    public int getCheckIn(){
        return checkIn;
    }

    /**
     * Returns the check-out date of the booking.
     *
     * @return the check-out date of the booking
     */
    public int getCheckOut(){
        return checkOut;
    }

    // setters
     /**
     * Sets the guest name for the booking.
     *
     * @param guestName the name of the guest to set
     */
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    /**
     * Sets the check-in date of the booking.
     *
     * @param checkIn the new check-in date
     */
    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }

    /**
     * Sets the check-out date of the booking.
     *
     * @param checkOut the new check-out date
     */
    public void setCheckOut(int checkOut) {
        this.checkOut = checkOut;
    }

    /**
     * Sets the room associated with the booking.
     *
     * @param room the new room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Sets the total price of the booking.
     *
     * @param totalPrice the new total price
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString(){
        return "\tGuest name: " + guestName + " " + room + " Check in: " + checkIn + ", Check out: " + checkOut + ", Total price: " + totalPrice;
    }

   
}
