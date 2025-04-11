
package Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Models.Car;
import Models.Reservation;
import Models.Store;
import Models.User;
import PricingStrategy.PricingStrategy;

public class CarRentalService {

    public List<Car> getAvailableCarsInCity(List<Store> stores) {
        List<Car> cars = new ArrayList<>();

        for(Store store:stores){
            List<Car> storecars = store.getAvailabelCars();
            for(Car car:storecars){
                cars.add(car);
            }
        }

        return cars;
    }

    public Reservation bookCar(User user, Store store, Car car, LocalDate startDate, LocalDate endDate, PricingStrategy pricingStrategy) {
        if (!car.isCarAvailable()) {
            throw new RuntimeException("Car is not available");
        }
        double totalCost = pricingStrategy.calculateRent(startDate, endDate, 100);
        car.bookCar();
        Reservation reservation = new Reservation("123", car, startDate, endDate, "100");
        store.addReservation(reservation);
        return reservation;
    }

    public void returnCar(Store store, String carId) {
        store.returnCar(carId);
    }
}
