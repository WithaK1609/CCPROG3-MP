/**
 * Child of Room class.
 *
 * <p> This is a version of the Room class that is an Executive Room.
 * @version 1.0
 */
public class ExecutiveRoom extends Room{
    /**
     * Constructor for the Executive Room.
     * @param name
     */
    
    public ExecutiveRoom(String name){
        super(name, "This is a Executive Room", 6, RoomPricer.ExecutivePrice(1299.99));
    }

    /**
     * Constructor for the Executive Room.
     * @param name
     * @param price
     */
    public ExecutiveRoom(String name, int price){
        super(name, "This is a Executive Room", 6, RoomPricer.ExecutivePrice(price));
    }

    @Override
    public String toString() {
        return "Room Name: " + this.name + "\n" + "Description: " + this.description + "\n" + "Price: " + this.price + "\n" + "Capacity: " + this.capacity + "\n";
    }
}