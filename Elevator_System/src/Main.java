import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Controllers.ElevatorSystem;
import Models.Direction;
import Models.Elevator;
import Models.Request;

public class Main {
    public static void main(String[] args) throws Exception {

        List<Elevator> elevators = new ArrayList<>();

        int numElevators = 2;

        for(int i=1;i<=numElevators;i++)
        {
            elevators.add(new Elevator(i));
        }

        ElevatorSystem elevatorSystem = new ElevatorSystem(elevators);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter floor request (or -1 to exit):");
            int floor = scanner.nextInt();
            if (floor == -1) break;

            System.out.println("Enter direction (UP/DOWN):");
            String dirInput = scanner.next().toUpperCase();
            Direction direction = dirInput.equals("UP") ? Direction.UP : Direction.DOWN;

            elevatorSystem.RequestBestElevator(new Request(floor, direction));
        }

        scanner.close();

    }
}
