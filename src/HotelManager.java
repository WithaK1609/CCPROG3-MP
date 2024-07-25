/**
 * Represents a utility class for managing a hotel.
 * <p> This class provides methods to create a hotel. It also provides methods to get and set the list of hotels.
 *
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */
import java.util.*;

public class HotelManager{

    private List<Hotel> hotels;     // list of hotels

    // Constructor
    public HotelManager() {
        hotels = new ArrayList<>();
    }

    public List<Hotel> getHotels() {
        return Collections.unmodifiableList(hotels);
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = new ArrayList<>(hotels);
    }
    /*
     * This methods handles the creation of rooms based on number of rooms with naming scheme of 101, 102, 103, etc.
     */
    public void addRooms(int numberofBaseRooms, int numberOfDeluxeRooms, int numberOfExecutiveRooms, Hotel hotel) {
        // Base Rooms
        if (numberofBaseRooms > 10){    // For over 10 rooms
            int hotelFloors = numberofBaseRooms / 10;
            int createdRooms = 0;
            while (createdRooms < numberofBaseRooms){
                for (int i = 1; i <= hotelFloors && createdRooms < numberofBaseRooms; i++) {
                    for (int j = 1; j <= 10 && createdRooms < numberofBaseRooms; j++) {
                        if (j == 10)
                            hotel.getRooms().add(new Room(("B" + i + "" + j)));
                        else
                            hotel.getRooms().add(new Room(("B" + i + "0" + j)));
                        createdRooms++;
                    }
                }
            }
        }

        // Create rooms based on number of rooms with naming scheme of 101, 102, 103, etc. For under 10 rooms
        else{
            
            for (int i = 1; i <= numberofBaseRooms; i++) {
                if(i < 10)
                    hotel.getRooms().add(new Room("B" + "10" + i));
                else
                    hotel.getRooms().add(new Room("B" + "1" + i));

            }
        }

        // Deluxe Rooms
        if (numberOfDeluxeRooms > 10){
            int hotelFloors = numberOfDeluxeRooms / 10;
            int createdRooms = 0;
            while (createdRooms < numberOfDeluxeRooms){
                for (int i = 1; i <= hotelFloors && createdRooms < numberOfDeluxeRooms; i++) {
                    for (int j = 1; j <= 10 && createdRooms < numberOfDeluxeRooms; j++) {
                        if (j == 10)
                            hotel.getDeluxeRooms().add(new DeluxeRoom(("D" + i + "" + j)));
                        else
                            hotel.getDeluxeRooms().add(new DeluxeRoom(("D" + i + "0" + j)));
                        createdRooms++;
                    }
                }
            }
        }

        else{
            
            for (int i = 1; i <= numberOfDeluxeRooms; i++) {
                if(i < 10)
                    hotel.getDeluxeRooms().add(new DeluxeRoom("D" +  "10" + i));
                else
                    hotel.getDeluxeRooms().add(new DeluxeRoom("D" +  "1" + i));

            }
        }

        // Executive Rooms
        if (numberOfExecutiveRooms > 10){
            int hotelFloors = numberOfExecutiveRooms / 10;
            int createdRooms = 0;
            while (createdRooms < numberOfExecutiveRooms){
                for (int i = 1; i <= hotelFloors && createdRooms < numberOfExecutiveRooms; i++) {
                    for (int j = 1; j <= 10 && createdRooms < numberOfExecutiveRooms; j++) {
                        if (j == 10)
                            hotel.getExecutiveRooms().add(new ExecutiveRoom(("E" + i + "" + j)));
                        else
                            hotel.getExecutiveRooms().add(new ExecutiveRoom(("E" + i + "0" + j)));
                        createdRooms++;
                    }
                }
            }
        }

        else{
            
            for (int i = 1; i <= numberOfExecutiveRooms; i++) {
                if(i < 10)
                    hotel.getExecutiveRooms().add(new ExecutiveRoom("E" + "10" + i));
                else
                    hotel.getExecutiveRooms().add(new ExecutiveRoom("E" + "1" + i));

            }
        }
    }

    /** 
     * This methods handles how to create a hotel.
     * 
     */
    public void createHotel(){
        // declare necessary variables
        Hotel hotel = new Hotel(null);
        boolean hotelCreationConfirm = false;
        int confirmHotel = -1;
        
        while(!hotelCreationConfirm){
            String hotelName = null;
            
            do{
                TextDisplay.design();
                hotelName = InputLogic.readString("Enter the hotel name: ");
                
                if (!getHotels().isEmpty()){     // checks if the list of hotels is empty

                    if(hotelName.isEmpty()){
                        System.out.println("Please enter a valid hotel name!");
                    }

                    if (!hotel.isHotelNameUnique(hotelName, getHotels())){    // checks if the hotel name is unique
                        System.out.println("Hotel name already exists!");
                    }
                }
            }while(!hotel.isHotelNameUnique(hotelName, getHotels()) || hotelName.isEmpty());
            
            // ask for number of rooms
            System.out.println("NOTE: You will be asked afterwards how many rooms to divide into BASE, DELUXE, and EXECUTIVE rooms.");
            
            int numberOfRooms = InputLogic.readInt("Enter how many TOTAL rooms this hotel will have: ", 1, 50);
            int numberOfBaseRooms = InputLogic.readInt("Enter how many BASE rooms this hotel will have: ", 0, numberOfRooms);
            int numberOfDeluxeRooms = InputLogic.readInt("Enter how many DELUXE rooms this hotel will have: ", 0, (numberOfRooms - numberOfBaseRooms));
            int numberOfExecutiveRooms = InputLogic.readInt("Enter how many EXECUTIVE rooms this hotel will have: ", 0, (numberOfRooms - numberOfBaseRooms - numberOfDeluxeRooms));

            hotel = new Hotel(hotelName);     // instantiate new hotel
            addRooms(numberOfBaseRooms, numberOfDeluxeRooms, numberOfExecutiveRooms, hotel);   // add rooms to hotel

            confirmHotel = InputLogic.readInt("Confirm Hotel Creation? (1 - Yes, 0 - No)? ", 0, 1);
            
            if (confirmHotel == 1){     // confirmed creation of hotel
                TextDisplay.design();
                System.out.println("Hotel successfully created!");
                InputLogic.readString("Press enter to exit...");
                hotelCreationConfirm = false;
                hotels.add(hotel);   // return hotel to be added to arraylist of hotels
                break;
            }
        }
    }
}