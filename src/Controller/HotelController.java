/**
 * Represents a the controller class for managing the creation of hotel and the GUI.
 * <p> This class provides methods to check if the hotel name is unique, validate the number of rooms
 * <p> and create a hotel if all conditions were met.
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */
package Controller;
import hotel.Hotel;
import hotel.HotelManager;
import main.InputLogic;

import java.util.*;
public class HotelController {
    HotelManager hotelManager = HotelManager.getInstance();
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Checks if hotel name is unique.
     * @param name - name of the hotel
     * @param hotels - list of hotels
     * @return boolean
     */
    public boolean isHotelNameUnique(String name, List<Hotel> hotels) {
        for (Hotel hotel : hotels) {
             if (hotel.getName().equalsIgnoreCase(name)){
                errorMessage = "Hotel name already exists! Please choose a different name.";
                return false;
            }
        }
        return true;
    }


    /**
     * Validates the number of rooms.
     * @param numberofBaseRooms 
     * @param numberOfDeluxeRooms
     * @param numberOfExecutiveRooms
     * @return boolean
     */
    public boolean validateNumberofRooms (String numberofBaseRooms, String numberOfDeluxeRooms, String numberOfExecutiveRooms) {
        // Validate each input using InputLogic.validateInt
        boolean isNumberofBaseRoomsValid = InputLogic.validateInt(numberofBaseRooms, 1, 50);
        boolean isNumberOfDeluxeRoomsValid = InputLogic.validateInt(numberOfDeluxeRooms, 1, 50);
        boolean isNumberOfExecutiveRoomsValid = InputLogic.validateInt(numberOfExecutiveRooms, 1, 50);

        // Check if all inputs are valid
        if (!isNumberofBaseRoomsValid || !isNumberOfDeluxeRoomsValid || !isNumberOfExecutiveRoomsValid) {
            errorMessage = "Please enter a number between 1 and 50 for each room type. The total number of rooms should not exceed 50.";
            return false;
        }

        // Parse the strings to integers
        int baseRooms = Integer.parseInt(numberofBaseRooms);
        int deluxeRooms = Integer.parseInt(numberOfDeluxeRooms);
        int executiveRooms = Integer.parseInt(numberOfExecutiveRooms);

        // Calculate the total number of rooms
        int maxRooms = baseRooms + deluxeRooms + executiveRooms;

        // Ensure the total number of rooms does not exceed 50
        if (maxRooms > 50) {
            errorMessage = "The total number of rooms should not exceed 50.";
            return false;
        }

        return true;
    }

    /**
     * Creates a hotel if all conditions are met.
     * @param hotelName
     * @param numberofBaseRooms
     * @param numberOfDeluxeRooms
     * @param numberOfExecutiveRooms
     * @return boolean
     */
    public boolean isHotelCreated(String hotelName, String numberofBaseRooms, String numberOfDeluxeRooms, String numberOfExecutiveRooms) {

        // Check if the list of hotels is empty. This block will not run ishotelnameunique.
        if (hotelManager.getHotels().isEmpty()) {
            // Check if the hotel name is empty, if the number of rooms are valid
            if (hotelName.isBlank() || !validateNumberofRooms(numberofBaseRooms, numberOfDeluxeRooms, numberOfExecutiveRooms)) {
                errorMessage = "Please enter a valid hotel name!";
                return false;
            }

            // Conditions are met. Hotel is created.
            else {
                // Parse the strings to integers
                int baseRooms = Integer.parseInt(numberofBaseRooms);
                int deluxeRooms = Integer.parseInt(numberOfDeluxeRooms);
                int executiveRooms = Integer.parseInt(numberOfExecutiveRooms);
                hotelManager.createHotel(hotelName, baseRooms, deluxeRooms, executiveRooms);
                return true;
            }


        } 
        
        // If the list of hotels is not empty, check if the hotel name is unique
        else {
            if (hotelName.isBlank() || !validateNumberofRooms(numberofBaseRooms, numberOfDeluxeRooms, numberOfExecutiveRooms) || !isHotelNameUnique(hotelName, hotelManager.getHotels())) {
                errorMessage = "Please enter a valid hotel name!";
                return false;
            }
            // Conditions are met. Hotel is created.
            else {
                // Parse the strings to integers
                int baseRooms = Integer.parseInt(numberofBaseRooms);
                int deluxeRooms = Integer.parseInt(numberOfDeluxeRooms);
                int executiveRooms = Integer.parseInt(numberOfExecutiveRooms);
                hotelManager.createHotel(hotelName, baseRooms, deluxeRooms, executiveRooms);
                return true;
            }
        }
    }
}