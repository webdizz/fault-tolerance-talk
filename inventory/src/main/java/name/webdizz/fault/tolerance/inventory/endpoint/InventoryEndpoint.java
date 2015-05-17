package name.webdizz.fault.tolerance.inventory.endpoint;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import name.webdizz.fault.tolerance.inventory.domain.Inventory;
import name.webdizz.fault.tolerance.inventory.domain.Product;
import name.webdizz.fault.tolerance.inventory.domain.Store;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/inventories")
public class InventoryEndpoint {

    @HystrixCommand
    @RequestMapping(value = "/{store}/{product}", method = RequestMethod.GET)
    public Inventory requestInventory(@PathVariable("store") String storeId, @PathVariable("product") String productId) {
        Store store = new Store(storeId);
        Product product = new Product(productId);
        return new Inventory(store, product, 1000);
    }
}
