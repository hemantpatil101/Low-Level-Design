import Factory.VehicleFactory;
import Product.Vehicle;
public class FactoryPatternDemo {
    public static void main(String[] args) throws Exception {
        VehicleFactory vehicleFactory = new VehicleFactory();

        Vehicle car = vehicleFactory.getVehicle("car");
        car.Drive();

        Vehicle bike = vehicleFactory.getVehicle("bike");
        bike.Drive();
    }
}
