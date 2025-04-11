package Models;

import java.time.LocalDate;

public class Reservation {
    private String reservationID;
    private Car car;
    private LocalDate startTime;
    private LocalDate endTime;
    private String totalAmount;

    public Reservation(String reservationID,Car car,LocalDate startTime,LocalDate endTime,String totalAmount){
          this.reservationID  =reservationID;
          this.car = car;
          this.startTime = startTime;
          this.endTime = endTime;
          this.totalAmount = totalAmount;
    }

    public Car getCar(){
        return this.car;
    }
}
