package name.webdizz.fault.tolerance.gateway.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import lombok.Data;
import name.webdizz.fault.tolerance.inventory.client.InventoryRequester;
import name.webdizz.fault.tolerance.inventory.client.command.CircuitBreakerInventoryRequestCommand;
import name.webdizz.fault.tolerance.inventory.domain.Inventory;
import name.webdizz.fault.tolerance.inventory.domain.Product;
import name.webdizz.fault.tolerance.inventory.domain.Store;

@RestController
@RequestMapping("/api/products")
public class ProductDetailsEndpoint {

    private final InventoryRequester inventoryRequester;

    @Autowired
    public ProductDetailsEndpoint(final InventoryRequester inventoryRequester) {
        this.inventoryRequester = inventoryRequester;
    }

    @RequestMapping("/{store}/{product}")
    public ProductDetails inventory(@PathVariable("store") String storeId, @PathVariable("product") String productId) {
        Store store = new Store(storeId);
        Product product = new Product(productId);
        Inventory inventory = new CircuitBreakerInventoryRequestCommand(inventoryRequester, store, product, false).execute();
        return new ProductDetails(inventory);
    }

    @Data
    @AllArgsConstructor
    class ProductDetails {
        private Inventory inventory;
    }
}
