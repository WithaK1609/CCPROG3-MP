import java.util.*;

public class CouponManager{
    private List<Coupon> couponsList;
    private HashMap<String, Coupon> coupons;
    
    public CouponManager(){
        this.couponsList = new ArrayList<>();
        this.coupons = new HashMap<>();
    }

    public List<Coupon> getCouponsList(){
        return Collections.unmodifiableList(couponsList);
    }

    public void setCouponsList(List<Coupon> couponsList){
        this.couponsList = new ArrayList<>(couponsList);
    }

    public boolean isCouponNameUnique(String name, List<Coupon> couponsList){
        for (Coupon coupon : couponsList) {
             if (coupon.getCode().equalsIgnoreCase(name)){
                return false;
            }
         }
        return true;
    }

    public void createCoupon(){
        Coupon coupon = new Coupon(null, null, 0);
        boolean couponCreationConfirm = false;
        int confirmCoupon = -1;
        
        while(!couponCreationConfirm){
            String couponName = null;
            String couponDescription = null;
            double couponDiscount = 0;

            do{
                TextDisplay.design();
                couponName = InputLogic.readString("Enter New Coupon Name: ");
                
                if (!getCouponsList().isEmpty()){     // checks if the list of hotels is empty

                    if(couponName.isEmpty()){
                        System.out.println("Please enter a valid hotel name!");
                    }

                    if (!isCouponNameUnique(couponName, getCouponsList())){    // checks if the hotel name is unique
                        System.out.println("Hotel name already exists!");
                    }
                }
            }while(!isCouponNameUnique(couponName, getCouponsList()) || couponName.isEmpty());
            
            couponDescription = InputLogic.readString("Enter New Coupon Description: ");
            couponDiscount = InputLogic.readDouble("Enter New Coupon Discount: ", 1, 100);
            coupon = new Coupon(couponName, couponDescription, couponDiscount);
            System.out.println("Coupon created:\n" + coupon);
            confirmCoupon = InputLogic.readInt("Enter 1 to confirm, 0 to cancel: ", 0, 1);
            
            if(confirmCoupon == 1)  {
                TextDisplay.design();
                System.out.println("Coupon created successfully!");
                InputLogic.readString("Press enter to exit...");
                couponCreationConfirm = true;
                addCoupon(coupon);
            }
        }


    }

    public void addCoupon(Coupon coupon) {
        coupons.put(coupon.getCode(), coupon);
    }

    public Coupon getCoupon(String code) {
        return coupons.get(code);
    }

    public void removeCoupon(String code) {
        coupons.remove(code);
    }

    public void updateCoupon(Coupon coupon) {
        coupons.put(coupon.getCode(), coupon);
    }
}
