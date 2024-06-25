import java.util.*;
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
        return "\tGuest name: " + guestName + "\n\t" + room + "\n\tCheck in: " + checkIn + "\n\tCheck out: " + checkOut + "\n\tTotal price: " + totalPrice;
    }

    /** 
     * Creates a new booking with the specified details.
     * @param hotels list of hotels
     * @return a new Booking object with the provided details
     */
    public static Booking createBooking(List<Hotel> hotels){
        // declare necessary variables
        Booking booking = null;
        boolean bookingConfirmed = false;
        int confirmBooking = -1;
        ArrayList<Room> availableRooms = new ArrayList<>();     // to store available rooms
        
        while(!bookingConfirmed){ 
            availableRooms.clear();     // makes sure it is empty to ensure no duplication while inside loop
            TextDisplay.design();
            
            if (hotels.size() == 0){    // if no hotels have been created
                System.out.println("No hotels have been created. No bookings can be made");
                InputLogic.readString("Press any key to go back...");
                break;
            }

            // Enters guest name, check in, check out
            System.out.println("Type 999 in GUEST NAME to exit booking immediately");
            String guestName = InputLogic.readString("Enter guest name: ");
            
            if (guestName.equals("999")){   // if user wants to exit booking
                break;
            }

            int checkIn = InputLogic.readInt("Enter check-in date: ", 1, 30);
            int checkOut = InputLogic.readInt("Enter check-out date: ", (checkIn + 1), 31);
            
            // Selects which hotel to stay
            TextDisplay.displayViewHotelList(hotels);
            int selectHotel = InputLogic.readInt("Select which hotel to stay: ", 1, hotels.size());
            Hotel selectedHotel = hotels.get(selectHotel - 1);
            
            // Prints out the rooms available
            System.out.println("Here are the rooms available within your specified dates (" + checkIn + " - " + checkOut + "): ");
            selectedHotel.printAvailableRooms(checkIn, checkOut);
            
            // Finds available rooms
            for (Room room : selectedHotel.getRooms()) {
                if (selectedHotel.isRoomAvailable(room, checkIn, checkOut)) {
                    availableRooms.add(room);
                }
            }

            // If there are no available rooms
            if (availableRooms.isEmpty()) {
                System.out.println("No available rooms for the selected dates.");
                continue;
            }

            int selectRoom = InputLogic.readInt("Enter which room you will be staying (1 - " + availableRooms.size() + "): ", 1, availableRooms.size());
            
            Room selectedRoom = availableRooms.get(selectRoom - 1);                
            double totalPrice = (checkOut - checkIn) * selectedRoom.getPrice();     // calculate total price. Can't use the method above as it is not static
            booking = new Booking(guestName, selectedRoom, checkIn, checkOut, totalPrice);      // create new booking object
            
            // prints out booking details
            TextDisplay.displayGuestDetails(booking);

            confirmBooking = InputLogic.readInt("Confirm booking? (1 - Yes, 0 - No)? ", 0, 1);
            
            if (confirmBooking == 1){      // booking confirmed
                selectedRoom.setAvailable(false);
                for (int i = checkIn; i <= checkOut; i++) {
                    selectedRoom.getReservations().add(i);
                }
                selectedHotel.confirmBooking(booking); // moved this out the for loop cause it was causing duplicates of bookings
                bookingConfirmed = true;    // breaks the loop
                TextDisplay.design();
                InputLogic.readString("Room Successfully Booked! Have a nice day");
                return booking;
            }
        }
        return null;        // booking not confirmed  
    }
}
