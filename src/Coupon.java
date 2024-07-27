import java.util.*;
/**
 * Represents a coupon that can be applied to a booking.
 * <p>A coupon has a code, a description, and a discount.
 * 
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */
public class Coupon{
    private final String code;
    private final String description;
    private final double discount;

    // Constructor
    /**
     * Constructs a new coupon.
     * @param code
     * @param description
     * @param discount
     */
    public Coupon(String code, String description, double discount){
        this.code = code;
        this.description = description;
        this.discount = discount;
    }

    // Getters
    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "Code: " + this.code + "\n" + "Description: " + this.description + "\n" + "Discount: " + this.discount + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } 
            
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Coupon coupon = (Coupon) obj;
        return Double.compare(coupon.discount, discount) == 0 &&
               Objects.equals(code, coupon.code) &&
               Objects.equals(description, coupon.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, description, discount);
    }
}