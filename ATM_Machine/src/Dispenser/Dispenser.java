package Dispenser;

public class Dispenser extends CashDispenser{
    private int denomination;

    public Dispenser(int denomination) {
        this.denomination = denomination;
    }

    public void dispense(int amount) {
        int numNotes = amount / denomination;
        int remainder = amount % denomination;

        if (numNotes > 0) {
            System.out.println("Dispensing " + numNotes + " ₹" + denomination + " notes");
        }

        if (remainder > 0 && nextDispenser != null) {
            nextDispenser.dispense(remainder);
        }
    }
}
