public class DeluxeRoom extends Room{
    public DeluxeRoom(String name) {
        super(name);
        this.description = "This is a Deluxe Room";
        this.price = RoomPrice.calculateDeluxePrice(1299.99);
        this.capacity = 4;
    }

    public DeluxeRoom(String name, int price) {
        super(name);
        this.description = "This is a Deluxe Room";
        this.price = RoomPrice.calculateDeluxePrice(price);
        this.capacity = 4;
    }
}