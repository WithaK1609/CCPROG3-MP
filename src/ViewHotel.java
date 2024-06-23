import java.util.ArrayList;
public class ViewHotel{

    private ArrayList<Hotel> hotels = Main.getHotels();
    
    
    public void viewHotel(){
        
        int choice = -1;

        while(choice != 0){
        TextDisplay.design();
        System.out.println("Here is a list of all of the hotels created: ");
        
        for (int i = 0; i < hotels.size(); i++){
            System.out.println("\t[" + (i+1) + "]" + "Hotel Name: " + hotels.get(i).getName());
            System.out.println("\t[" + (i+1) + "]" + "Number of rooms: " + hotels.get(i).getRooms().size());
            System.out.print("\n");
        }
        System.out.println("Please select a hotel to view its details (0 to EXIT): ");
        TextDisplay.design();
        choice = InputLogic.readInt("Hotel Number: ", 0, hotels.size());

        if(choice != 0){
            TextDisplay.design();
            System.out.println("Hotel Name: " + hotels.get(choice-1).getName());
            System.out.println("Number of rooms: " + hotels.get(choice-1).getRooms().size());
            TextDisplay.design();
        }
        }

    }
}