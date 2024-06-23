// WIP CLASS
public class Booking{
    private Room room;
    private int checkIn;
    private int checkOut;
    

    public Booking(Room room, int checkIn, int checkOut) {
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
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

}