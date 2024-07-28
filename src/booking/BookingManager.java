package booking;
import main.InputLogic;
import java.util.*;
import GUI.TextDisplay;
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
     * @return a new Booking object with the provided details
     */

    public Booking createBooking(List<Hotel> hotels){
        // declare necessary variables
        Booking booking = null;
        CouponManager couponManager = new CouponManager();
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
            System.out.println("Type 0 in GUEST NAME to exit booking immediately");
            String guestName = InputLogic.readString("Enter guest name: ");
            
            if (guestName.equals("0")){   // if user wants to exit booking
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
            double totalPrice = 0.0;
            DatePriceModifier modifier = selectedRoom.getDatePriceModifier();
            for (int i = checkIn; i <= checkOut; i++) {
                double rate = modifier.getPriceRate(i);
                totalPrice += selectedRoom.getPrice() * rate;
            }
            totalPrice = couponManager.applyCoupon(selectedRoom, totalPrice, checkIn, checkOut);     // apply coupon discount to booking
            booking = new Booking(guestName, selectedRoom, checkIn, checkOut, totalPrice);      // create new booking object
            
            
            // prints out booking details
            TextDisplay.displayGuestDetails(booking);

            confirmBooking = InputLogic.readInt("Confirm booking? (1 - Yes, 0 - No)? ", 0, 1);
            
            if (confirmBooking == 1){      // booking confirmed
                //selectedRoom.setAvailable(false);     // not being used
                for (int i = checkIn; i <= checkOut; i++) {
                    selectedRoom.getReservations().add(i);
                }
                selectedHotel.confirmBooking(booking);    // confirm booking
                bookingConfirmed = true;    // breaks the loop
                TextDisplay.design();
                InputLogic.readString("Room Successfully Booked! Have a nice day");
                return booking;
            }
        }

        return null;        // booking not confirmed  
    }
}