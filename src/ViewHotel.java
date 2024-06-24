import java.util.*;
public class ViewHotel{
    
    public void viewSpecificHotel(List<Hotel> hotels){
        
        int choice = -1;

        while(true)
        {
            if (hotels.size() == 0){
                TextDisplay.design();
                System.out.println("No hotels have been created yet!");
                InputLogic.readString("Press enter to continue...");
                break;
            }
            // Removed extra design since nag repeat yung border
            TextDisplay.clearConsole();
            TextDisplay.displayViewHotelList(hotels);
            System.out.println("Please select a hotel to view its details (0 to EXIT): ");
            TextDisplay.design();
            
            choice = InputLogic.readInt("Hotel Number: ", 0, hotels.size());
            
            if (choice == 0){
                break;
            }
            
            Hotel selectedHotel = hotels.get(choice - 1);
            TextDisplay.clearConsole();
            TextDisplay.displayHotelInformation(choice, hotels);
            InputLogic.readString("Press enter to continue...");
            TextDisplay.design();
            
            /* WIP
            choice = InputLogic.readInt("Choose: ", 0, 2);
    
            switch (choice){
                case 1:
                    //viewRoomDetails();
                    break;
                case 2:
                    //viewReservations();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }*/
        }

    }
}