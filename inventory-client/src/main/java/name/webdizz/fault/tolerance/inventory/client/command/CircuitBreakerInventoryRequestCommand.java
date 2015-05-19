package name.webdizz.fault.tolerance.inventory.client.command;

import name.webdizz.fault.tolerance.inventory.client.InventoryRequester;
import name.webdizz.fault.tolerance.inventory.domain.Inventory;
import name.webdizz.fault.tolerance.inventory.domain.Product;
import name.webdizz.fault.tolerance.inventory.domain.Store;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class CircuitBreakerInventoryRequestCommand extends GenericInventoryRequestCommand {

    public CircuitBreakerInventoryRequestCommand(final InventoryRequester inventoryRequester, final Store store, final Product product, final boolean circuitState) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(CircuitBreakerInventoryRequestCommand.class.getSimpleName()))
                .andCommandKey(HystrixCommandKey.Factory.asKey(CircuitBreakerInventoryRequestCommand.class.getSimpleName()))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                .withCircuitBreakerForceOpen(circuitState)
                ), inventoryRequester, store, product);
    }

    @Override
    protected Inventory run() throws Exception {
        return inventoryRequester.requestInventoryFor(store, product);
    }

}
