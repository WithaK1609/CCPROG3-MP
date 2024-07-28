package booking;
import java.util.*;

import main.InputLogic;
import rooms.Room;
/**
 * Manages the coupons. This is where the coupon logic of the booking system is implemented.
 * <p>Contains a list of coupons that can be applied to a booking.
 * <p>This is where the coupon logic of the booking system is implemented.
 * 
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */
public class CouponManager{
    private HashMap<String, Coupon> couponList;
    private Set<Integer> reservationDates;
    
    /**
     * Constructor
     * Constructs a new CouponManager.
     */
    public CouponManager() {
        Coupon iworkhere = new Coupon("I_WORK_HERE", "10% off for employees", 0.1);
        Coupon stay4get1 = new Coupon("STAY4_GET1", "Stay for 5 days or more, 1st night will be free", 1.0);
        Coupon payday = new Coupon("PAYDAY", "7% off for payday", 0.07);
        this.couponList = new HashMap<>();
        this.reservationDates = new HashSet<>();
        addCoupon(iworkhere);
        addCoupon(stay4get1);
        addCoupon(payday);
    }
    // Adds the coupon to the hashmap
    public void addCoupon(Coupon coupon) {
        couponList.put(coupon.getCode(), coupon);
    }

    // Removes the coupon from the hashmap
    public void removeCoupon(String code) {
        couponList.remove(code);
    }

    /**
     * Applies the coupon to the booking.
     * @param room
     * @param bookingPrice
     * @param checkIn
     * @param checkOut
     * @return double
     */
    public double applyCoupon(Room room, double bookingPrice, int checkIn, int checkOut) {
        reservationDates.clear();       // clears the reservation dates to ensure no duplication
        for (int i = checkIn; i <= checkOut; i++) {
            reservationDates.add(i);
            reservationDates.remove(checkOut);      // removes the check-out date
        }
        while(true) {
            String couponCode = InputLogic.readString("Enter Coupon Code (Enter 0 to Exit): ");
            // Exit if user enters 0
            if (couponCode.equals("0")) {
                break;
            }

            // Checks if the coupon code is valid
            else if (couponList.containsKey(couponCode)) {
                // Calculates the total discount and the new booking price
                double totalDiscount = (bookingPrice * couponList.get(couponCode).getDiscount());  
                double newBookingPrice = bookingPrice - totalDiscount;
                
                // Checks which coupon code was entered
                switch (couponCode) {
                    case "I_WORK_HERE":
                        System.out.println("Coupon Applied: " + couponList.get(couponCode).getDescription());
                        System.out.println("You have saved: " + String.format("%.2f", totalDiscount));
                        return newBookingPrice;     // returns the new booking price
                    case "STAY4_GET1":
                        // ensures that the guest stays for 5 days or more
                        if (checkOut - checkIn >= 5) {      
                            // ensures that the discount is only applied to the first night
                            totalDiscount = (room.getPrice() * couponList.get(couponCode).getDiscount());       
                            newBookingPrice = bookingPrice - totalDiscount;     // subtracts the discount from the total price
                            System.out.println("Coupon Applied: " + couponList.get(couponCode).getDescription());
                            System.out.println("You have saved: " + String.format("%.2f", totalDiscount));
                            return newBookingPrice;
                        }
                        else {
                            System.out.println("You must STAY for 5 days or more to avail this coupon.");
                            break;
                        }
                    case "PAYDAY":
                        // ensures that the reservation dates cover the 15th or 30th of the month
                        if (reservationDates.contains(15) || reservationDates.contains(30)) {
                            System.out.println("Coupon Applied: " + couponList.get(couponCode).getDescription());
                            System.out.println("You have saved: " + String.format("%.2f", totalDiscount));
                            return newBookingPrice;
                        }
                        else {
                            System.out.println("You MUST HAVE reservations days that cover the 15th or 30th of the month to avail this coupon BUT NOT AS CHECKOUT.");
                            break;
                        }
                    }
                }
            else{
                System.out.println("Invalid Coupon Code. Please try again.");
            }
        }
        return bookingPrice;
    }

    /**
     * Prints out the coupons.
     * Not being used in the main program.
     */
    public void printCoupons() {
        for (Coupon coupon : couponList.values()) {
            System.out.println(coupon);
        }
    }
}
