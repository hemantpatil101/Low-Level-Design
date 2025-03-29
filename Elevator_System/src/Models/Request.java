package Models;

public class Request {
    private final int floor;
    private Direction direction;
    
    public Request(int floor,Direction direction)
    {
        this.floor = floor;
        this.direction = direction;
    }

    public int getFloor()
    {
        return this.floor;
    }

    public Direction getDirection()
    {
        return direction;
    }
}
