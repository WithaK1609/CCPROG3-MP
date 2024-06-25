import java.util.*;

public class ManageHotel{
    
    public void manageSpecificHotel(List<Hotel> hotels){
        int choice = -1;

        while(true){
            if (hotels.size() == 0){
                TextDisplay.design();
                System.out.println("No hotels to manage!");
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
                    int numRoomsToAdd = InputLogic.readInt("Enter the number of rooms to add: ", 1, 50);
                    addHotelRoom(selectedHotel, numRoomsToAdd);
                    break;

                case 3:
                    //int numRoomsToRemove = InputLogic.readInt("Enter the number of rooms to remove: ", 1, 50);
                    //removeHotelRoom(selectedHotel, numRoomsToRemove);
                    break;

                case 4:
                    //double newPrice = InputLogic.readDouble("Enter the new room price: ", 100.0, 10000.0);
                    //updateRoomPrice(selectedHotel, newPrice);
                    break;

                case 5:
                    //removeReservation(selectedHotel);
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

        int confirm = InputLogic.readInt("Are you sure you want to change the name of the hotel " + hotel.getName() + "? (0 - No, 1 - Yes): ", 0, 1);
        if (confirm == 0) {
            System.out.println("Operation canceled.");
            InputLogic.readString("Press enter to continue...");
            return;
        }
        hotel.setName(newName);
        System.out.println("Hotel name changed successfully.");
        InputLogic.readString("Press enter to continue...");
    }

    private void addHotelRoom(Hotel hotel, int numRoomsToAdd) {
    List<Room> rooms = hotel.getRooms();
    int currentRoomCount = rooms.size();

        // checker if the hotel has reached the maximum capacity of 50 rooms
    if (currentRoomCount + numRoomsToAdd > 50) {
        System.out.println("Cannot add " + numRoomsToAdd + " rooms. The hotel can only have a maximum of 50 rooms.");
        System.out.println("You can add up to " + (50 - currentRoomCount) + " more rooms.");
        InputLogic.readString("Press any key to go back...");
        return;
    }

    int confirm = InputLogic.readInt("Are you sure you want to add " + numRoomsToAdd + " rooms? (0 - No, 1 - Yes): ", 0, 1);
    if (confirm == 0) {
        System.out.println("Operation canceled.");
        InputLogic.readString("Press enter to continue...");
        return;
    }

        int currentFloor = currentRoomCount / 10;
        int currentRoomNumber = currentRoomCount % 10;

    for (int i = 0; i < numRoomsToAdd; i++) {
        if (currentRoomNumber == 10) {
            currentFloor++;
            currentRoomNumber = 0;
        }
        currentRoomNumber++;
        String newRoomName = (currentFloor + 1) + String.format("%02d", currentRoomNumber);
        rooms.add(new Room(newRoomName));
    }
        System.out.println(numRoomsToAdd + " rooms added successfully.");
        InputLogic.readString("Press enter to continue...");
    }
    


    private void removeHotel(List<Hotel> hotels, Hotel hotel) {

        int confirm = InputLogic.readInt("Are you sure you want to remove the hotel " + hotel.getName() + "? (0 - No, 1 - Yes): ", 0, 1);
        if (confirm == 0) {
            System.out.println("Operation canceled.");
            InputLogic.readString("Press enter to continue...");
            return;
        }
        hotels.remove(hotel);
        System.out.println("Hotel removed successfully.");
        InputLogic.readString("Press enter to continue...");

    }


}
