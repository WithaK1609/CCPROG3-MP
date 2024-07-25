/**
 * Calculates the price of a room based on the room type and the date.
 * 
 * <p> This class is used to calculate the price of a room based on the room type. It is used in the Room class to set the price of the room.
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */

public class RoomPricer{

    /**
     * A method that returns the base price of a room.
     * @param basePrice
     * @return basePrice
     */
    public static double BaseRoomPrice(double basePrice) {
        return basePrice;
    }

    /**
     * A method that returns the price of a deluxe room.
     * @param basePrice 
     * @return basePrice * 1.2
     */
    public static double DeluxePrice(double basePrice) {
        return basePrice * 1.2;
    }

    /**
     * A method that returns the price of an executive room.
     * @param basePrice
     * @return basePrice * 1.35
     */
    public static double ExecutivePrice(double basePrice){
        return basePrice * 1.35;
    }
}