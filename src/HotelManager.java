import java.util.*;

public class HotelManager{

    private List<Hotel> hotels;

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

    /** 
     * This methods handles how to create a hotel.
     * @return Hotel
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
            

            int numberOfRooms = InputLogic.readInt("Enter how many rooms this hotel will have: ", 1, 50);

            hotel = new Hotel(hotelName);     // instantiate new hotel

            // Create rooms based on number of rooms with naming scheme of 101, 102, 103, etc.
            if (numberOfRooms > 10){
                int hotelFloors = numberOfRooms / 10;
                int createdRooms = 0;
                while (createdRooms < numberOfRooms){
                    for (int i = 1; i <= hotelFloors && createdRooms < numberOfRooms; i++) {
                        for (int j = 1; j <= 10 && createdRooms < numberOfRooms; j++) {
                            if (j == 10)
                                hotel.getRooms().add(new Room((i + "" + j)));
                            else
                                hotel.getRooms().add(new Room((i + "0" + j)));
                            createdRooms++;
                        }
                    }
                }
            }

            // Create rooms based on number of rooms with naming scheme of 101, 102, 103, etc. For under 10 rooms
            else{
                
                for (int i = 1; i <= numberOfRooms; i++) {
                    if(i < 10)
                        hotel.getRooms().add(new Room("10" + i));
                    else
                        hotel.getRooms().add(new Room("1" + i));

                }
            }
            
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