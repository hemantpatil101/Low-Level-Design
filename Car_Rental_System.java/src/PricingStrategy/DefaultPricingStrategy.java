package PricingStrategy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DefaultPricingStrategy {
    
    public int calculateRent(LocalDate startTime,LocalDate endTime,int rentPerHour){
        return rentPerHour*10;
    }
}
