public class ExecutiveRoom extends Room{
    public ExecutiveRoom(String name){
        super(name);
        this.description = "This is a Executive Room";
        this.price = RoomPrice.calculateExecutivePrice(1299.99);
        this.capacity = 6;
    }

    public ExecutiveRoom(String name, int price){
        super(name);
        this.description = "This is a Executive Room";
        this.price = RoomPrice.calculateExecutivePrice(price);
        this.capacity = 6;
    }
}