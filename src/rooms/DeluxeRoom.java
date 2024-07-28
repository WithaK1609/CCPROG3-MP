package rooms;
/**
 * Child of Room class.
 *
 * <p> This is a version of the Room class that is a Deluxe Room.
 * @version 1.0
 */

public class DeluxeRoom extends Room{
    
    /**
     * Constructor for the Deluxe Room.
     * @param name
     */
    
    public DeluxeRoom(String name) {
        super(name, "This is a Deluxe Room", 4, RoomPricer.ExecutivePrice(1299.99));
    }

    /**
     * Constructor for the Deluxe Room.
     * @param name
     * @param price
     */
    public DeluxeRoom(String name, int price) {
        super(name, "This is a Deluxe Room", 4, RoomPricer.DeluxePrice(price));
    }
}