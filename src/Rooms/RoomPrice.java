public class RoomPrice{
    
    public static double calculateBaseRoomPrice(double baseRate) {
        return baseRate;
    }

    
    public static double calculateDeluxePrice(double baseRate) {
        return baseRate * 1.2;
    }

    
    public static double calculateExecutivePrice(double baseRate){
        return baseRate * 1.35;
    }
}