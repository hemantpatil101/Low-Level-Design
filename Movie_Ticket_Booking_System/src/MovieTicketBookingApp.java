import java.util.Arrays;
import java.util.List;

import Models.*;
import Models.Show;
import Models.Theater;
import Models.User;
import MovieService.Movieservice;

public class MovieTicketBookingApp {
    public static void main(String[] args) throws Exception {
        Movie movie1 = new Movie("Batman", "180 Minutes");
        Movie movie2 = new Movie("The Dark Knight", "180 Minutes");

        Theater theater1 = new Theater("T01", "Pune");
        Theater theater2 = new Theater("T02", "Blr");
        
        theater1.addShow(new Show("S01",movie1,"01-12-12","01-03-13",250));
        theater2.addShow(new Show("S01",movie2,"01-12-12","01-03-13",250));

        Movieservice movieService = new Movieservice(Arrays.asList(theater1,theater2));

        List<Theater> theaters = movieService.getTheatersByCity("Batman");

        User user = new User("U01","Hemant");
        Booking booking = theater1.bookTIckets(user,new Show("S01",movie1,"01-12-12","01-03-13",250) , Arrays.asList(3,4,5,6));
        System.out.println("Booking Confirmed");
    }
}
