package name.webdizz.jeeconf.fault.tolerance.inventory.endpoint;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import name.webdizz.jeeconf.fault.tolerance.inventory.domain.Inventory;
import name.webdizz.jeeconf.fault.tolerance.inventory.domain.Product;
import name.webdizz.jeeconf.fault.tolerance.inventory.domain.Store;

@RestController
@RequestMapping("/inventories")
public class InventoryEndpoint {

    @RequestMapping(value = "/{store}/{product}", method = RequestMethod.GET)
    public Inventory request(@PathVariable("store") String storeId, @PathVariable("product") String productId) {
        Store store = new Store(storeId);
        Product product = new Product(productId);
        return new Inventory(store, product, 1000);
    }
}
