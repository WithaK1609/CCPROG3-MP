import java.util.List;
import java.util.ArrayList;

public class Main{
    private static List<Hotel> hotels = new ArrayList<>();

    public static List<Hotel> getHotels(){
        return new ArrayList<Hotel>(hotels);
    }

    public static void setHotels(List<Hotel> hotels){
        Main.hotels = hotels;
    }

    public static void main(String[] args){
        ViewHotel viewHotel = new ViewHotel();
        ManageHotel manageHotel = new ManageHotel();
        System.out.println("Welcome to the Hotel Reservation System!");
        
        int choice = -1; 
        
        // start of the program
        do{
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
                
                //for(Hotel index : hotels){
                  //  System.out.println(index);
                //}
            }

            else if(choice == 2){
                viewHotel.viewSpecificHotel(hotels);
            }

            else if(choice == 3){
                manageHotel.manageSpecificHotel(hotels);
            }

            else if(choice == 4){
                //Hotel.simulateBooking();
            }
        }while(choice != 0);

        InputLogic.closeScanner();
        
    }

    
}

    
