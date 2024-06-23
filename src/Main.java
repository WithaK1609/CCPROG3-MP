import java.util.ArrayList;

public class Main{
    private static ArrayList<Hotel> hotels = new ArrayList<>();

    public static void main(String[] args){
        
        System.out.println("Welcome to the Hotel Reservation System!");
        
        int choice = -1; 
        
        // start of the program
        while(choice != 0){
            TextDisplay.clearConsole();
            TextDisplay.design();
            System.out.println("Select which feature to use:");
            System.out.println("\t[1] Create Hotel");
            System.out.println("\t[2] View Hotel");
            System.out.println("\t[3] Manage Hotel");
            System.out.println("\t[4] Simulate Booking");
            System.out.println("\t[0] Exit Program");
            TextDisplay.design();

            choice = InputLogic.readInt("Choose: ", 0, 4);  // gets user input. Refer to the InputLogic for more details

            if (choice == 1){
                Hotel hotel = Hotel.createHotel();
                hotels.add(hotel);
                
                for(Hotel index : hotels){
                    System.out.println(index);
                }
            }

            else if(choice == 2){
                //Hotel.viewHotel();
            }

            else if(choice == 3){
                //Hotel.manageHotel();
            }

            else if(choice == 4){
                //Hotel.simulateBooking();
            }
        }
        InputLogic.closeScanner();
        
    }

    public static ArrayList<Hotel> getHotels(){
        return new ArrayList<>(hotels);
    }

    public static void setHotels(ArrayList<Hotel> hotels){
        Main.hotels = hotels;
    }
}

    
