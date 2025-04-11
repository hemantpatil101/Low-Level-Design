package Models;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Product,Integer> stock = new HashMap<>();

    public void addProduct(Product product,int quantity){
        stock.put(product, stock.getOrDefault(product, 0) + quantity);
    }

    public Boolean isAvailable(Product product){
        return stock.getOrDefault(product, 0) > 0;
    }

    public void dispenseProduct(Product product){
        if(isAvailable(product)){
            stock.put(product, stock.get(product) - 1);
        }
    }
}
