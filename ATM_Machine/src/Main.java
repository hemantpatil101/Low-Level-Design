import Dispenser.Dispenser;
public class Main {
    public static void main(String[] args) throws Exception {
         
         Dispenser dispenser2000 = new Dispenser(2000);
         Dispenser dispenser500 = new Dispenser(500);
         Dispenser dispenser200 = new Dispenser(200);
         Dispenser dispenser100 = new Dispenser(100);
         
         dispenser2000.setNextDispenser(dispenser500);
         dispenser500.setNextDispenser(dispenser200);
         dispenser200.setNextDispenser(dispenser100);
 
         
         System.out.println("Requesting ₹2100:");
         dispenser2000.dispense(2100);
 
         System.out.println("\nRequesting ₹3700:");
         dispenser2000.dispense(3700);
 
         System.out.println("\nRequesting ₹250:");
         dispenser2000.dispense(250);
    }
}




