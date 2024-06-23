public class Room{
    private String name;
    private double price;
    private boolean isAvailable;


    // Constructor
    public Room(String name){
        this.name = name;
        this.price = 1299.00;
        this.isAvailable = true;
    }


    // Getters and Setters
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    public  void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

}