package UberApp;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

enum RideStatus {
    REQUESTED, ONGOING, COMPLETED, CANCELLED
}

class Location {
    double latitude;
    double longitude;

    Location(double lat, double lon) {
        this.latitude = lat;
        this.longitude = lon;
    }

    public double distanceTo(Location other) {
        double latDiff = this.latitude - other.latitude;
        double lonDiff = this.longitude - other.longitude;
        return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);
    }
}

abstract class User {
    static AtomicInteger idCounter = new AtomicInteger();
    int id;
    String name;
    Location location;

    User(String name, Location location) {
        this.id = idCounter.incrementAndGet();
        this.name = name;
        this.location = location;
    }
}

class Rider extends User {
    Rider(String name, Location location) {
        super(name, location);
    }
}

class Driver extends User {
    boolean isAvailable;

    Driver(String name, Location location) {
        super(name, location);
        this.isAvailable = true;
    }
}

class Payment {
    double amount;
    String paymentMethod;
    boolean isPaid;

    Payment(double amount, String paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.isPaid = false;
    }

    public void processPayment() {
        System.out.println("Processing payment of ₹" + amount + " via " + paymentMethod);
        this.isPaid = true;
        System.out.println("Payment successful.");
    }
}

class Ride {
    static AtomicInteger rideIdCounter = new AtomicInteger();
    int rideId;
    Rider rider;
    Driver driver;
    Location pickupLocation;
    Location dropoffLocation;
    RideStatus status;
    double fare;
    Payment payment;

    Ride(Rider rider, Location pickup, Location dropoff) {
        this.rideId = rideIdCounter.incrementAndGet();
        this.rider = rider;
        this.pickupLocation = pickup;
        this.dropoffLocation = dropoff;
        this.status = RideStatus.REQUESTED;
    }

    public void assignDriver(Driver driver) {
        this.driver = driver;
        this.status = RideStatus.ONGOING;
        driver.isAvailable = false;
    }

    public void completeRide(String paymentMethod) {
        this.status = RideStatus.COMPLETED;
        this.fare = calculateFare();
        this.payment = new Payment(fare, paymentMethod);
        this.payment.processPayment();
        driver.isAvailable = true;
    }

    private double calculateFare() {
        double distance = pickupLocation.distanceTo(dropoffLocation);
        return distance * 10;
    }
}

class RideService {
    List<Driver> drivers = new ArrayList<>();
    List<Ride> rides = new ArrayList<>();

    public Driver registerDriver(String name, Location location) {
        Driver driver = new Driver(name, location);
        drivers.add(driver);
        return driver;
    }

    public Rider registerRider(String name, Location location) {
        return new Rider(name, location);
    }

    public Ride requestRide(Rider rider, Location pickup, Location dropoff) {
        Driver nearestDriver = findNearestDriver(pickup);
        if (nearestDriver == null) {
            System.out.println("No available drivers nearby.");
            return null;
        }
        Ride ride = new Ride(rider, pickup, dropoff);
        ride.assignDriver(nearestDriver);
        rides.add(ride);
        System.out.println("Ride assigned to driver: " + nearestDriver.name);
        return ride;
    }

    public void completeRide(Ride ride, String paymentMethod) {
        ride.completeRide(paymentMethod);
        System.out.println("Ride completed. Fare: ₹" + ride.fare);
    }

    private Driver findNearestDriver(Location pickup) {
        Driver nearest = null;
        double minDistance = Double.MAX_VALUE;
        for (Driver driver : drivers) {
            if (driver.isAvailable) {
                double distance = driver.location.distanceTo(pickup);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearest = driver;
                }
            }
        }
        return nearest;
    }
}

public class CarBookingSystem {
    public static void main(String[] args) {
        RideService rideService = new RideService();
        rideService.registerDriver("Alice", new Location(12.9716, 77.5946));
        rideService.registerDriver("Bob", new Location(12.2958, 76.6394));
        Rider rider = rideService.registerRider("Charlie", new Location(12.9716, 77.5946));
        Ride ride = rideService.requestRide(rider, rider.location, new Location(12.2958, 76.6394));
        if (ride != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter payment method (e.g., Credit Card, UPI): ");
            String paymentMethod = scanner.nextLine();
            rideService.completeRide(ride, paymentMethod);
            scanner.close();
        }
    }
}
