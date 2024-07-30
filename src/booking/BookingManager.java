package booking;
import hotel.Hotel;
import rooms.Room;
/**
 * Manages the creation of bookings.
 *
 * <p>Allows the user to create a booking by entering the guest name, check-in and check-out dates.
 * <p>Also asks the user to enter coupon codes.
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */
public class BookingManager {
     /** 
     * Creates a new booking with the specified details.
     * @param hotels list of hotels
     * @param guestName name of the guest
     * @param selectedRoom room selected by the guest
     * @param checkIn check-in date
     * @param checkOut check-out date
     * @param totalPrice total price of the booking
     * @return void
     * 
     */
    
    public void createBooking(Hotel hotel, String guestName, Room selectedRoom, int checkIn, int checkOut, double totalPrice){
        // declare necessary variables
        Booking booking = new Booking(guestName, selectedRoom, checkIn, checkOut, totalPrice);      // create new booking object
        hotel.confirmBooking(booking);    // confirm booking 
             
    }
}