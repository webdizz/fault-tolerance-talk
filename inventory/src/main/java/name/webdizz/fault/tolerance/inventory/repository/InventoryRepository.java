package name.webdizz.fault.tolerance.inventory.repository;

import name.webdizz.fault.tolerance.inventory.domain.Inventory;
import name.webdizz.fault.tolerance.inventory.domain.Product;
import name.webdizz.fault.tolerance.inventory.domain.Store;

public interface InventoryRepository {
    void save(Inventory inventory);

    Inventory read(Product product, Store store);
}
