package UberApp;
import java.util.*;

// Main class should be at the top
public class UberCarpooling {
    public static void main(String[] args) {
        RideManager rideManager = new RideManager();
        
        Rider rider1 = new Rider("1", "Alice", new Location(1.0, 1.0));
        Rider rider2 = new Rider("2", "Bob", new Location(1.1, 1.1));
        Driver driver = new Driver("D1", "John", new Location(1.0, 1.0));
        
        rideManager.rideMatchingService.availableDrivers.add(driver);
        
        Ride ride1 = rideManager.requestRide(rider1, new Location(1.0, 1.0), new Location(2.0, 2.0));
        Ride ride2 = rideManager.requestRide(rider2, new Location(1.1, 1.1), new Location(2.1, 2.1));
        
        System.out.println("Ride 1: " + ride1);
        System.out.println("Ride 2: " + ride2);
    }
}

// Base User Class
abstract class User {
    String id;
    String name;
    Location location;
    public User(String id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
}

class Rider extends User {
    public Rider(String id, String name, Location location) {
        super(id, name, location);
    }
}

class Driver extends User {
    boolean available;
    public Driver(String id, String name, Location location) {
        super(id, name, location);
        this.available = true;
    }
}

class Location {
    double latitude, longitude;
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public double distanceTo(Location other) {
        double latDiff = this.latitude - other.latitude;
        double lonDiff = this.longitude - other.longitude;
        return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff) * 111; // Approx km conversion
    }
}

enum RideStatus { REQUESTED, ONGOING, COMPLETED }

class Ride {
    String id;
    Driver driver;
    List<Rider> riders;
    Map<Rider, Location> pickupLocations;
    Map<Rider, Location> dropOffLocations;
    Map<Rider, Double> riderFares;
    RideStatus status;
    Location currentLocation;

    public Ride(String id, Driver driver) {
        this.id = id;
        this.driver = driver;
        this.riders = new ArrayList<>();
        this.pickupLocations = new HashMap<>();
        this.dropOffLocations = new HashMap<>();
        this.riderFares = new HashMap<>();
        this.status = RideStatus.REQUESTED;
        this.currentLocation = driver.location;
    }
    
    public void addRider(Rider rider, Location pickup, Location dropoff) {
        riders.add(rider);
        pickupLocations.put(rider, pickup);
        dropOffLocations.put(rider, dropoff);
        double distance = pickup.distanceTo(dropoff);
        riderFares.put(rider, distance * 20);
    }
}

class RideMatchingService {
    List<Ride> activeRides = new ArrayList<>();
    List<Driver> availableDrivers = new ArrayList<>();
    
    public Ride findOrCreateRide(Rider rider, Location pickup, Location dropoff) {
        for (Ride ride : activeRides) {
            if (ride.riders.size() < 3 && ride.currentLocation.distanceTo(pickup) < 20.0) { // Simple carpooling: max 3 riders, within 1km
                ride.addRider(rider, pickup, dropoff);
                return ride;
            }
        }
        
        for (Driver driver : availableDrivers) {
            if (driver.available) {
                driver.available = false;
                Ride newRide = new Ride(UUID.randomUUID().toString(), driver);
                newRide.addRider(rider, pickup, dropoff);
                activeRides.add(newRide);
                return newRide;
            }
        }
        return null; // No ride available
    }
}

class RideManager {
    RideMatchingService rideMatchingService = new RideMatchingService();
    
    public Ride requestRide(Rider rider, Location pickup, Location dropoff) {
        Ride ride = rideMatchingService.findOrCreateRide(rider, pickup, dropoff);
        if (ride != null) {
            ride.status = RideStatus.ONGOING;
        }
        return ride;
    }
}
