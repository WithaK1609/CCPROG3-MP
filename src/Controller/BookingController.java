/**
 *
 * Represents a the controller class for managing the creation of hotel and the GUI.
 * <p> This class provides methods to check if the booking details are valid, calculate the total price
 * <p> and create a booking objects if all conditions were met
 *
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */
package Controller;
import hotel.Hotel;
import rooms.*;
import main.InputLogic;
import java.util.Set;
import booking.BookingManager;
import booking.CouponManager;
import booking.DatePriceModifier;
import java.util.HashSet;


public class BookingController {
    private String errorMessage;
    private String couponMessage;
    private CouponManager couponManager = new CouponManager();
    private BookingManager bookingManager = new BookingManager();

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getCouponMessage() {
        return couponMessage;
    }

    /**
     * Validates the guest name.
     * @param guestName
     * @return true if the guest name is valid, false otherwise
     */
    public boolean validateGuestName(String guestName) {
        if (guestName == null || guestName.trim().isEmpty()) {
            errorMessage = "Please enter a valid guest name.";
            return false;
        }

        return true;
    }
    
    /**
     * Validates the booking dates.
     * @param checkin
     * @param checkout
     * @return true if the booking dates are valid, false otherwise
     */
    public boolean validateBookingDates(String checkin, String checkout) {
        boolean isCheckInValid = InputLogic.validateInt(checkin, 1, 30);

        if (!isCheckInValid) {
            errorMessage = "Please enter a valid check-in date.";
            return false;
        }
        
        int checkInDate = Integer.parseInt(checkin);
        boolean isCheckOutValid = InputLogic.validateInt(checkout, checkInDate + 1, 31);

        if (!isCheckOutValid) {
            errorMessage = "Please enter a valid check-out date.";
            return false;
        }

        return true;
    }
    
    /**
     * Validates the coupon code.
     * @param couponCode
     * @param checkIn
     * @param checkOut
     * @return true if the coupon code is valid, false otherwise
     */
    public boolean validateCouponCode(String couponCode, String checkIn, String checkOut) {
        int checkInDate = Integer.parseInt(checkIn);
        int checkOutDate = Integer.parseInt(checkOut);

        if (couponCode == null || couponCode.isBlank()) {
            couponMessage = "No coupon applied.";
            return true;
        }

        if (!couponManager.getCouponList().containsKey(couponCode)) {
            errorMessage = "Invalid coupon code.";
            return false;
        }

        Set<Integer> reservationDates = new HashSet<>();
        reservationDates.clear(); // clears the reservation dates to ensure no duplication
        for (int i = checkInDate; i <= checkOutDate; i++) {
            reservationDates.add(i);
            reservationDates.remove(checkOutDate); // removes the check-out date
        }

        // Checks which coupon code was entered
        switch (couponCode) {
            case "I_WORK_HERE":
                couponMessage = "Coupon Applied: " + couponManager.getCouponList().get(couponCode).getDescription();
                return true;
            case "STAY4_GET1":
                if (checkOutDate - checkInDate >= 5) {
                    couponMessage = "Coupon Applied: " + couponManager.getCouponList().get(couponCode).getDescription();
                    return true;
                } else {
                    errorMessage = "You must STAY for 5 days or more to avail this coupon.";
                    break;
                }
            case "PAYDAY":
                // ensures that the reservation dates cover the 15th or 30th of the month
                if (reservationDates.contains(15) || reservationDates.contains(30)) {
                    couponMessage = "Coupon Applied: " + couponManager.getCouponList().get(couponCode).getDescription();
                    return true;
                } else {
                    errorMessage = "You MUST HAVE reservations days that cover the 15th or 30th of the month to avail this coupon BUT NOT AS CHECKOUT.";
                    break;
                }
            default:
                errorMessage = "Invalid coupon code.";
                return false;
        }

        return false;

    }

    /**
     * Calculates the total price of the booking.
     * @param couponCode
     * @param selectedRoom
     * @param checkIn
     * @param checkOut
     * @return the total price of the booking
     */
    public double calculateTotalPrice(String couponCode, Room selectedRoom, int checkIn, int checkOut) {
        double totalPrice = 0.0;
        DatePriceModifier modifier = selectedRoom.getDatePriceModifier();
        for (int i = checkIn; i <= checkOut; i++) {
            double rate = modifier.getPriceRate(i);
            totalPrice += selectedRoom.getPrice() * rate;
        }
        totalPrice = couponManager.applyCoupon(couponCode, selectedRoom, totalPrice, checkIn, checkOut);     // apply coupon discount to booking
        return totalPrice;
    }
    
    /**
     * Creates a booking object.
     * @param hotel
     * @param guestName
     * @param selectedRoom
     * @param checkIn
     * @param checkOut
     * @param totalPrice
     * @return true if the booking was created, false otherwise
     */
    public boolean isBookingCreated (Hotel hotel, String guestName, Room selectedRoom, int checkIn, int checkOut, double totalPrice) {
        bookingManager.createBooking(hotel, guestName, selectedRoom, checkIn, checkOut, totalPrice);
        return true;
    }
}