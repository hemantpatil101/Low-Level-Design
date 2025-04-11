import Models.Product;
import Services.VendingMachine;

public class VendingMachineApp {
    public static void main(String[] args) throws Exception {
        VendingMachine machine = new VendingMachine();
        
        Product chips = new Product("Chips", 50);
        Product soda = new Product("Soda", 100);
        
        machine.getInventory().addProduct(chips, 10);
        machine.getInventory().addProduct(soda, 5);
        
        // Sample transactions
        machine.insertMoney(100);
        machine.selectProduct(chips);
        machine.dispenseProduct();
        
        System.out.println();
    }
}
