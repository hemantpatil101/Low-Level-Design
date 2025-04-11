package PricingStrategy;

import java.time.LocalDate;

public interface PricingStrategy {
    int calculateRent(LocalDate startTime,LocalDate endTime,int rentPerHour);
}
