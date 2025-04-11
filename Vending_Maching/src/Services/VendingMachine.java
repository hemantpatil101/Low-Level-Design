package Services;

import Models.Inventory;
import Models.Product;
import State.IdleState;
import State.VendingMachineState;

public class VendingMachine {
    private VendingMachineState vendingMachineState;
    private Inventory inventory;
    private int balance;
    private Product selectedProduct;

    public VendingMachine(){
        this.vendingMachineState = new IdleState();
        this.inventory = new Inventory();
        this.balance = 0;
        this.selectedProduct = null;
    }

    public void setSelectedProduct(Product product){
        this.selectedProduct = product;
    }
    
    public void insertMoney(int amount) { 
        vendingMachineState.insertMoney(this, amount); 
    }

    public void selectProduct(Product product) { 
        vendingMachineState.selectProduct(this, product); 
    }

    public void cancelTransaction() { 
        vendingMachineState.cancelTransaction(this); 
    }

    public void addBalance(int amount){
        this.balance += amount;
    }
    
    public void setBalance(int amount){
        this.balance = amount;
    }
    public void setState(VendingMachineState state){
        this.vendingMachineState = state;
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public int getBalance(){
        return this.balance;
    }

    public Product getSelectedProduct(){
        return this.selectedProduct;
    }

    public void dispenseProduct() { 
        vendingMachineState.dispenseProduct(this); 
    }
}
