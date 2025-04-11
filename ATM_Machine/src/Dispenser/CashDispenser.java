package Dispenser;

public class CashDispenser {
    protected CashDispenser nextDispenser;

    public void setNextDispenser(CashDispenser nextDispenser) {
        this.nextDispenser = nextDispenser;
    }

    public void dispense(int amount) {
        if (nextDispenser != null) {
            nextDispenser.dispense(amount);
        } else {
            System.out.println("Cannot dispense amount: " + amount);
        }
    }
}
