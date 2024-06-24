import java.util.*;

public class Booking{
    private int checkIn;
    private int checkOut;
    private Room room;
    private String guestName;
    private double totalPrice;
    
    public Booking(String guestName, Room room, int checkIn, int checkOut) {
        this.guestName = guestName;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = calculateTotalPrice();
    }
    
    // getters
    public String getGuestName(){
        return guestName;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public Room getRoom(){
        return room;
    }

    public int getCheckIn(){
        return checkIn;
    }

    public int getCheckOut(){
        return checkOut;
    }


    // setters
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
    
    public void getTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(int checkOut) {
        this.checkOut = checkOut;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    private double calculateTotalPrice(){
        return (checkOut - checkIn) * room.getPrice();
    }


    
    /** 
     * This methods handles how to create a booking.
     * @param hotels
     * @return Booking
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
            System.out.println("Here are the rooms available within your specified dates: (" + checkIn + " - " + checkOut + ")");
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
            double TotalPrice = (checkOut - checkIn) * selectedRoom.getPrice();     // calculate total price. Can't use the method above as it is not static
            booking = new Booking(guestName, selectedRoom, checkIn, checkOut);      // create new booking object
            
            // prints out booking details
            TextDisplay.design();
            System.out.println("Guest Name: " + guestName);
            System.out.println("Staying at Room number: " + selectedRoom.getName());
            System.out.println("Check-In: " + checkIn);
            System.out.println("Check-Out: " + checkOut);
            System.out.println("Total Booking fee: " + TotalPrice);

            confirmBooking = InputLogic.readInt("Confirm booking?(0 - No, 1 - Yes) ", 0, 1);
            
            if (confirmBooking == 1){      // booking confirmed
                selectedRoom.setAvailable(false);
                for (int i = checkIn; i <= checkOut; i++) {
                    selectedRoom.getReservations().add(i);
                    selectedHotel.confirmBooking(booking);
                }
                bookingConfirmed = true;    // breaks the loop
                InputLogic.readString("Room Sucessfully Booked! Have a nice day");
                return booking;
            }
        }
        return null;        // booking not confirmed  
    }

}