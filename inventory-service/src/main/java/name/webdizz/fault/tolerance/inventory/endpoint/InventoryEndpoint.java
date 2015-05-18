package name.webdizz.fault.tolerance.inventory.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import name.webdizz.fault.tolerance.inventory.domain.Inventory;
import name.webdizz.fault.tolerance.inventory.domain.Product;
import name.webdizz.fault.tolerance.inventory.domain.Store;
import name.webdizz.fault.tolerance.inventory.repository.InventoryRepository;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/inventories")
public class InventoryEndpoint {

    @Autowired
    private InventoryRepository inventoryRepository;

    @HystrixCommand(commandKey = "RequestInventory")
    @RequestMapping("/{store}/{product}")
    public Inventory requestInventory(@PathVariable("store") String storeId, @PathVariable("product") String productId) {
        Store store = new Store(storeId);
        Product product = new Product(productId);
        return inventoryRepository.read(product, store);
    }

    @HystrixCommand(commandKey = "SaveInventory")
    @RequestMapping(value = "/{store}/{product}", method = RequestMethod.POST)
    public void saveInventory(@PathVariable("store") String storeId, @PathVariable("product") String productId, @RequestBody Long amount) {
        Store store = new Store(storeId);
        Product product = new Product(productId);
        Inventory inventory = new Inventory(store, product, amount);
        inventoryRepository.save(inventory);
    }
}
