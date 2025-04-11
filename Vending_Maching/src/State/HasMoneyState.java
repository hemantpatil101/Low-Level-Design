package State;

import Models.Product;
import Services.VendingMachine;

public class HasMoneyState implements VendingMachineState{
    @Override
    public void insertMoney(VendingMachine vendingMachine,int money){
        vendingMachine.addBalance(money);
        System.out.println("Added Money " + money + "to your Account.");
    }
    @Override
    public void selectProduct(VendingMachine vendingMachine, Product product){
        if(vendingMachine.getInventory().isAvailable(product)){
            if(product.getPrice() <= vendingMachine.getBalance()){
                vendingMachine.setSelectedProduct(product);
                vendingMachine.setState(new ProductSelectedState());
                System.out.println("Selected: " + product.getName());
            }
            else{
                System.out.println("Not enough money! Insert more.");
            }
        }
        else{
            System.out.println("Product Not available! Select other one.");
        }
    }

    @Override
    public void dispenseProduct(VendingMachine vendingMachine){
        System.out.println("Select Product First");
    }

    @Override
    public void cancelTransaction(VendingMachine vendingMachine){
        System.out.println("Transaction canceled. Returning ₹" + vendingMachine.getBalance());
        vendingMachine.setBalance(0);
        vendingMachine.setState(new IdleState());
    }
}
