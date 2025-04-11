package Models;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Store {
    private String storeID;
    private String city;
    private List<Car> carList = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public List<Car> getAvailabelCars()
    {
        List<Car> availableCars = new ArrayList<>();

        for(Car car:carList)
        {
            if(car.isCarAvailable())
            availableCars.add(car);
        }

        return availableCars;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public String getCity(){
        return city;
    }

    public void returnCar(String carNo)
    {
        for(Reservation reservation : reservations)
        {
            if(reservation.getCar().getCarNo() == carNo)
            {
                reservation.getCar().endBooking();
                reservations.remove(reservation);
                break;
            }
        }
    }
}
