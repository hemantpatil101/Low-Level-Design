package State;

import Models.Product;
import Services.VendingMachine;

public interface VendingMachineState {
    void insertMoney(VendingMachine vendingMachine,int money);
    void selectProduct(VendingMachine vendingMachine, Product product);
    void dispenseProduct(VendingMachine vendingMachine);
    void cancelTransaction(VendingMachine machine);
}
