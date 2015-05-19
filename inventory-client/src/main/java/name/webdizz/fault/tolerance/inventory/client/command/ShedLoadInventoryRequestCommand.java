package name.webdizz.fault.tolerance.inventory.client.command;

import name.webdizz.fault.tolerance.inventory.client.InventoryRequester;
import name.webdizz.fault.tolerance.inventory.domain.Inventory;
import name.webdizz.fault.tolerance.inventory.domain.Product;
import name.webdizz.fault.tolerance.inventory.domain.Store;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class ShedLoadInventoryRequestCommand extends GenericInventoryRequestCommand {

    protected ShedLoadInventoryRequestCommand(final InventoryRequester inventoryRequester, final Store store, final Product product) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(ShedLoadInventoryRequestCommand.class.getSimpleName()))
                .andCommandKey(HystrixCommandKey.Factory.asKey(ShedLoadInventoryRequestCommand.class.getSimpleName()))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                .withCircuitBreakerRequestVolumeThreshold(10)
                                .withCircuitBreakerForceClosed(true)
                                .withExecutionTimeoutInMilliseconds(200)
                ), inventoryRequester, store, product);
    }

    @Override
    protected Inventory run() throws Exception {
        return inventoryRequester.requestInventoryFor(store, product);
    }

}
