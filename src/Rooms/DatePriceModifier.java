/**
 * Manages the price rates for specific dates in a hotel.
 *
 * <p> This class allows setting and getting price rates for specific dates. The price rates can vary between 50% and 150% of the base room price. By default, all dates have a price rate of 100%.
 * The class provides methods to set a price rate for a specific date, get the price rate for a specific date, and retrieve all the date-specific price rates.
 *
 * @author Jakob Hernandez && Kian Daylag
 * @version 1.0
 */

import java.util.HashMap;
import java.util.Map;

public class DatePriceModifier {
    private Map<Integer, Double> datePriceRates;

    /**
     * Constructs a DatePriceModifier object with default price rates set to 100% (1.0).
     * Initializes the price rates for dates from 1 to 31.
     */
    
    // Constructor
    public DatePriceModifier() {
        datePriceRates = new HashMap<>();
        for (int i = 1; i <= 31; i++) {
            datePriceRates.put(i, 1.0);  // Default rate is 100%
        }
    }

    /**
     * Sets the price rate for a specific date.
     *
     * @param date the date
     * @param rate the price rate
     *  
     */

    public void setPriceRate(int date, double rate) {
        datePriceRates.put(date, rate);
    }

    /**
     * Gets the price rate for a specific date.
     *
     * @param date the date
     * 
     * @return the price rate
     */

    public double getPriceRate(int date) {
        return datePriceRates.getOrDefault(date, 1.0);
    }

    /**
     * Gets all the date-specific price rates.
     *
     * @return a map containing all the date-specific price rates
     */

    public Map<Integer, Double> getAllRates() {
        return datePriceRates;
    }
}