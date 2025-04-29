package MovieBooking;

import java.util.*;

class Movie {
    String title;
    public Movie(String title) {
        this.title = title;
    }
}

class User {
    String id, name;
    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Booking {
    String id;
    User user;
    List<String> seats;
    Date time;
    double totalPrice;

    public Booking(String id, User user, List<String> seats, double totalPrice) {
        this.id = id;
        this.user = user;
        this.seats = seats;
        this.time = new Date();
        this.totalPrice = totalPrice;
    }

    public String toString() {
        return "[Booking ID: " + id + ", User: " + user.name + ", Seats: " + seats + ", Time: " + time + ", Total Price: ₹" + totalPrice + "]";
    }
}

class Show {
    String id;
    Movie movie;
    String time;
    List<Booking> bookings = new ArrayList<>();
    Set<String> bookedSeats = new HashSet<>();
    double basePrice;

    public Show(String id, Movie movie, String time, double basePrice) {
        this.id = id;
        this.movie = movie;
        this.time = time;
        this.basePrice = basePrice;
    }

    public double calculateTotalPrice(List<String> seats) {
        double total = 0;
        for (String seat : seats) {
            total += basePrice;
        }
        return total;
    }

    public Booking bookSeats(User user, List<String> seats) {
        for (String seat : seats) {
            if (bookedSeats.contains(seat)) {
                System.out.println("Seat " + seat + " already booked!");
                return null;
            }
        }
        bookedSeats.addAll(seats);
        double totalPrice = calculateTotalPrice(seats);
        Booking booking = new Booking(UUID.randomUUID().toString(), user, seats, totalPrice);
        bookings.add(booking);
        return booking;
    }
}

class Theater {
    String id, name;
    List<Show> shows = new ArrayList<>();

    public Theater(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addShow(Show show) {
        shows.add(show);
    }
}

public class MovieBookingSystem {
    public static void main(String[] args) {
        Theater theater = new Theater("T1", "PVR");

        Movie movie = new Movie("Inception");
        Show show = new Show("S1", movie, "7:00 PM", 300);
        theater.addShow(show);

        User user = new User("U1", "Alice");

        List<String> requestedSeats = Arrays.asList("A1", "A2");
        Booking booking = show.bookSeats(user, requestedSeats);

        if (booking != null) {
            System.out.println("Booking Successful: " + booking);
        }
    }
}
