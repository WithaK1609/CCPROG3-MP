package Controller;
import hotel.Hotel;
import rooms.*;
import main.InputLogic;
import booking.BookingManager;
import booking.CouponManager;
import booking.DatePriceModifier;


public class BookingController {
    private String errorMessage;
    private CouponManager couponManager = new CouponManager();
    private BookingManager bookingManager = new BookingManager();

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean validateGuestName(String guestName) {
        if (guestName == null || guestName.trim().isEmpty()) {
            errorMessage = "Please enter a valid guest name.";
            return false;
        }

        return true;
    }
    
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
    
    public boolean validateCouponCode(String couponCode) {
        if (couponCode == null || couponCode.isBlank()) {
            return true;
        }

        if (!couponManager.getCouponList().containsKey(couponCode)) {
            errorMessage = "Invalid coupon code.";
            return false;
        }
        return true;

    }

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
    
    public boolean isBookingCreated (Hotel hotel, String guestName, Room selectedRoom, int checkIn, int checkOut, double totalPrice) {
        bookingManager.createBooking(hotel, guestName, selectedRoom, checkIn, checkOut, totalPrice);
        return true;
    }
}