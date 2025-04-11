package State;

import Models.Product;
import Services.VendingMachine;

public class DispensingState implements VendingMachineState {
    @Override
    public void insertMoney(VendingMachine vendingMachine,int money){
        System.out.println("Please Wait, Dispensing Product");
    }
    @Override
    public void selectProduct(VendingMachine vendingMachine, Product product){
        System.out.println("Please wait. Dispensing product...");
    }

    @Override
    public void dispenseProduct(VendingMachine vendingMachine){
        System.out.println("Product dispensed. Returning ₹" + vendingMachine.getBalance() + " change.");
        vendingMachine.setBalance(0);
        vendingMachine.setState(new IdleState());
    }

    @Override
    public void cancelTransaction(VendingMachine vendingMachine){
        System.out.println("Cannot cancel, product is being dispensed.");
    }
}
