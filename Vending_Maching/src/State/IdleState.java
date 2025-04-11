package State;

import Models.Product;
import Services.VendingMachine;

public class IdleState implements VendingMachineState {
    @Override
    public void insertMoney(VendingMachine vendingMachine,int money){
        vendingMachine.addBalance(money);
        vendingMachine.setState(new HasMoneyState());
        System.out.println("Added Money " + money + "to your Account.");
    }

    @Override
    public void selectProduct(VendingMachine vendingMachine, Product product){
        System.out.println("Insert Money First");
    }

    @Override
    public void dispenseProduct(VendingMachine vendingMachine){
        System.out.println("Insert Money and Select Product First");
    }

    @Override
    public void cancelTransaction(VendingMachine machine){
        System.out.println("No Transaction to Cancel");
    }
}
