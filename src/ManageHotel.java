import java.util.List;

public class ManageHotel {
    
    public void manageSpecificHotel(List<Hotel> hotels){
        int choice = -1;

        while(true)
        {
            if (hotels.size() == 0){
                TextDisplay.design();
                System.out.println("No hotels to manage yet!");
                InputLogic.readString("Press enter to continue...");
                break;
            }           

            
            TextDisplay.clearConsole();
            TextDisplay.displayViewHotelList(hotels);
            System.out.println("Please select a hotel to manage (0 to EXIT): ");
            TextDisplay.design();           
            
            
            choice = InputLogic.readInt("Hotel Number: ", 0, hotels.size());
            
            if (choice == 0){
                break;
            }
            
            Hotel selectedHotel = hotels.get(choice - 1);
            TextDisplay.clearConsole();
            TextDisplay.displayManageHotelOptions(choice, hotels);            
            TextDisplay.design();


            choice = InputLogic.readInt("Choose: ", 0, 6);
            switch (choice) {
                case 1:
                    changeHotelName(selectedHotel);
                    break;
                case 2:
                    // addHotelRoom(selectedHotel);
                    break;

                case 3:
                    // removeHotelRoom(hotels, selectedHotel);
                    break;  

                case 4:
                    // updateRoomPrice(selectedHotel);       
                    break;

                case 5:
                    // removeReservation(selectedHotel);
                    break;

                case 6:
                    removeHotel(hotels, selectedHotel);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }

    }

    private void changeHotelName(Hotel hotel) {
        String newName = InputLogic.readString("Enter the new hotel name: ");
        hotel.setName(newName);
        System.out.println("Hotel name changed successfully.");
    }

    

    private void removeHotel(List<Hotel> hotels, Hotel hotel) {
        hotels.remove(hotel);
        System.out.println("Hotel removed successfully.");

    }


}
