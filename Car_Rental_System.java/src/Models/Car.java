package Models;

public class Car{
    private String carNo;
    private String manufactureDate;
    private String modelName;
    private int rentPerHour;
    private boolean isAvailable;
    private int numberOfSeats;
    

    public Car(String carNo,String manufactureDate,String modelName,int rentPerHour){
        this.carNo = carNo;
        this.manufactureDate = manufactureDate;
        this.modelName = modelName;
        this.rentPerHour = rentPerHour;
        this.numberOfSeats = numberOfSeats;
        isAvailable = true;
    }

    public boolean isCarAvailable()
    {
        return isAvailable;
    }

    public String getCarNo()
    {
        return carNo;
    }

    public void bookCar()
    {
        isAvailable = false;
    }

    public void endBooking()
    {
        isAvailable = true;
    }

}

