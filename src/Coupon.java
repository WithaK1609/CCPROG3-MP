public class Coupon{
    private String code;
    private String description;
    private double discount;

    // Constructor
    public Coupon(String code, String description, double discount){
        this.code = code;
        this.description = description;
        this.discount = discount;
    }

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Code: " + this.code + "\n" + "Description: " + this.description + "\n" + "Discount: " + this.discount + "\n";
    }
}