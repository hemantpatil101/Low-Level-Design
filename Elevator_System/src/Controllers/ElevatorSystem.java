package Controllers;

import java.util.List;

import Models.Direction;
import Models.Elevator;
import Models.Request;

public class ElevatorSystem {
    private final List<Elevator> elevatorList;

    public ElevatorSystem(List<Elevator> elevatorList)
    {
        this.elevatorList = elevatorList;
    }

    public void RequestBestElevator(Request request)
    {
        Elevator bestElevator = FindBestElevator(request);

        if(bestElevator != null)
        {
            bestElevator.AddRequest(request.getFloor());
            bestElevator.Move();
        }
        else
        {
            System.out.println("No Elevator is Available.");
        }
    }

    public Elevator FindBestElevator(Request request)
    {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for(Elevator elevator : elevatorList)
        {
            if((elevator.getDirection() == Direction.IDLE))
            {
                return elevator;
            }

            if((elevator.getDirection() == Direction.UP) && (request.getFloor() >= elevator.getCurrentFloor()))
            {
                int diff = request.getFloor() - elevator.getCurrentFloor();
                if(diff < minDistance)
                {
                    minDistance = diff;
                    bestElevator = elevator;
                }
            }
            if((elevator.getDirection() == Direction.DOWN) && (request.getFloor() <= elevator.getCurrentFloor()))
            {
                int diff = elevator.getCurrentFloor() - request.getFloor();
                if(diff < minDistance)
                {
                    minDistance = diff;
                    bestElevator = elevator;
                }
            }
        }

        if(bestElevator == null)
        {
            for(Elevator elevator : elevatorList)
            {
                if(Math.abs(elevator.getCurrentFloor() - request.getFloor()) > minDistance)
                {
                    minDistance = Math.abs(elevator.getCurrentFloor() - request.getFloor());
                    bestElevator = elevator;
                }
            }
        }

        return bestElevator;
    }
}
