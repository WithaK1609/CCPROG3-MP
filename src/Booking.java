// WIP CLASS
public class Booking{
    private Room room;
    private int checkIn;
    private int checkOut;
    private String guestName;
    private double totalPrice;
    

    public Booking(String guestName, Room room, int checkIn, int checkOut, double totalPrice) {
        this.guestName = guestName;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
    }
    // getters

    public String getGuestName() {
        return guestName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Room getRoom() {
        return room;
    }

    public int getCheckIn() {
        return checkIn;
    }

    public int getCheckOut() {
        return checkOut;
    }


    // setters
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
    
    public void getTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(int checkOut) {
        this.checkOut = checkOut;
    }




    private double calculateTotalPrice(){
        return (checkOut - checkIn) * room.getPrice();
    }



}