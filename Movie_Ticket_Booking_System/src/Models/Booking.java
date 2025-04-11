package Models;

import java.util.List;

public class Booking {
    private String bookingID;
    private Show show;
    private User user;
    private List<Integer> bookedSeats;
    private int totalPrice;
    
    public Booking(String bookingID,Show show,User user,List<Integer> Seats){
        this.bookingID = bookingID;
        this.show = show;
        this.user = user;
        this.bookedSeats = Seats;
        this.totalPrice = Seats.size() * show.getPrice();
    }

    public Show getShow(){
        return this.show;
    }

    public List<Integer> getBookedSeats(){
        return this.bookedSeats;
    }
}
