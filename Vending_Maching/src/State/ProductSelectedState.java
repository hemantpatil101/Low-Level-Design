package State;

import Models.Product;
import Services.VendingMachine;

public class ProductSelectedState implements VendingMachineState{
    @Override
    public void insertMoney(VendingMachine vendingMachine,int money){
        vendingMachine.addBalance(money);
        System.out.println("Added Money " + money + "to your Account.");
    }
    @Override
    public void selectProduct(VendingMachine vendingMachine, Product product){
        System.out.println("Product Already Selected");
    }

    @Override
    public void dispenseProduct(VendingMachine vendingMachine){
        System.out.println("Dispensing Product");
        vendingMachine.getInventory().dispenseProduct(vendingMachine.getSelectedProduct());
        vendingMachine.setBalance(vendingMachine.getBalance() - vendingMachine.getSelectedProduct().getPrice());
        vendingMachine.setState(new DispensingState());
    }

    @Override
    public void cancelTransaction(VendingMachine vendingMachine){
        System.out.println("Transaction canceled. Returning ₹" + vendingMachine.getBalance());
        vendingMachine.setBalance(0);
        vendingMachine.setState(new IdleState());
    }
}
