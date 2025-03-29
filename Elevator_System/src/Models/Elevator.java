package Models;

import java.util.PriorityQueue;

public class Elevator {
    private final int id;
    private int currentFloor;
    private ElevatorStatus elevatorStatus;
    private Direction direction;

    private final PriorityQueue<Integer> upQueue;
    private final PriorityQueue<Integer> downQueue;

    public Elevator(int id)
    {
        this.id = id;
        this.currentFloor = 0;
        this.elevatorStatus = ElevatorStatus.IDLE;
        this.direction = Direction.IDLE;

        upQueue = new PriorityQueue<>();
        downQueue = new PriorityQueue<>((a,b) -> b - a);
    }

    public void AddRequest(int floor)
    {
        if(floor == currentFloor)
        {
            System.out.println("Elevator is currently on the same floor");
            return;
        }

        if(floor > currentFloor)
        {
            upQueue.add(floor);
            if(elevatorStatus == ElevatorStatus.IDLE)
            {
                elevatorStatus = ElevatorStatus.MOVING;
                direction = Direction.UP;
            }
        }
        else
        {
            downQueue.add(floor);
            if(elevatorStatus == ElevatorStatus.IDLE)
            {
                elevatorStatus = ElevatorStatus.MOVING;
                direction = Direction.DOWN;
            }
        }
    }

    public void Move()
    {
        while(!upQueue.isEmpty() && !downQueue.isEmpty())
        {
            if(direction == Direction.UP)
            {
                upQueue.poll();
                if(upQueue.isEmpty())
                {
                    direction = (downQueue.isEmpty() ? Direction.IDLE : Direction.DOWN);
                }
            }
            if(direction == Direction.DOWN)
            {
                downQueue.poll();
                if(downQueue.isEmpty())
                {
                    direction = (upQueue.isEmpty() ? Direction.IDLE : Direction.UP);
                }
            }
        }
        UpdateStatus();
    }

    private void UpdateStatus()
    {
        if(upQueue.isEmpty() && downQueue.isEmpty())
        {
            elevatorStatus = ElevatorStatus.IDLE;
            direction = Direction.IDLE;
        }
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public ElevatorStatus getStatus() {
        return elevatorStatus;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean canPickRequest(int floor, Direction requestDirection) {
        if (elevatorStatus == ElevatorStatus.IDLE) return true;

        if (direction == Direction.UP && requestDirection == Direction.UP && floor >= currentFloor) {
            return true;
        } 
        if (direction == Direction.DOWN && requestDirection == Direction.DOWN && floor <= currentFloor) {
            return true;
        }

        return false;
    }
}

