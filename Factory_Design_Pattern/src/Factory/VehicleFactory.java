package Factory;

import Product.Bike;
import Product.Car;
import Product.Vehicle;

public class VehicleFactory {
    public static Vehicle getVehicle(String type)
    {
        if(type.equalsIgnoreCase("CAR"))
        {
            return new Car();
        }
        else if(type.equalsIgnoreCase("BIKE"))
        {
            return new Bike();
        }
        else
        {
            System.out.println("Invalid type of Vehicle !!");
            throw new IllegalArgumentException("Invalid type of Vehicle : " + type);
        }
    }
}
