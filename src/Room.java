public class Room{
    private String name;
    private int number;
    private static double price = 1299.00;

    // Constructor
    public Room(String name, int number){
        this.name = name;
        this.number = number;
    }

    // Getters and Setters
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getNumber(){
        return number;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public double getPrice(){
        return price;
    }

    public static void setPrice(double price){
        Room.price = price;
    }

}