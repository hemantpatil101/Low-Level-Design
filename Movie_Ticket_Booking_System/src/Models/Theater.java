package Models;

import java.util.ArrayList;
import java.util.List;

public class Theater {
    private String theaterID;
    private String city;
    private List<Show> shows;
    private List<Booking> bookings;

    public Theater(String theaterID,String city){
        this.bookings = new ArrayList<>();
        this.shows = new ArrayList<>();

        this.theaterID  =theaterID;
        this.city = city;
    }

    public List<Show> getShowsForMovie(String Movie){
        List<Show> showList = new ArrayList<>();

        for(Show show:shows){
            if(show.getMovieName() == Movie){
                showList.add(show);
            }
        }

        return showList;
    } 

    public void addShow(Show show){
        this.shows.add(show);
        return;
    }

    public Booking bookTIckets(User user,Show show,List<Integer> seats){
        if(show.bookSeats(seats)){
            Booking booking  = new Booking("Booking" + bookings.size(), show, user, seats);
            bookings.add(booking);
            return booking;
        }
        System.out.println("Seats selected by you sre not available. Pls select from below list : ");
        System.out.println(show.getAvailableSeats());
        return null;
    }

    public void cancelBooking(Booking booking){
        booking.getShow().cancelSeats(booking.getBookedSeats());

    }

    public String getCity(){
        return this.city;
    }
}
