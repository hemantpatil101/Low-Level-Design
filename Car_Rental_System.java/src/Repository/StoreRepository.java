package Repository;

import java.util.List;
import java.util.stream.Collectors;

import Models.Store;

public class StoreRepository {
    private List<Store> stores;

    public StoreRepository(List<Store> stores) {
        this.stores = stores;
    }

    public List<Store> getStoresByCity(String city) {
        return stores.stream()
                .filter(store -> store.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }
}
