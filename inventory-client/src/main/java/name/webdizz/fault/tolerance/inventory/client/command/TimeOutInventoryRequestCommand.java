package name.webdizz.fault.tolerance.inventory.client.command;

import name.webdizz.fault.tolerance.inventory.client.InventoryRequester;
import name.webdizz.fault.tolerance.inventory.domain.Inventory;
import name.webdizz.fault.tolerance.inventory.domain.Product;
import name.webdizz.fault.tolerance.inventory.domain.Store;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class TimeOutInventoryRequestCommand extends GenericInventoryRequestCommand {

    public TimeOutInventoryRequestCommand(final int timeoutInMillis, final InventoryRequester inventoryRequester, final Store store, final Product product) {
        super(Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(TimeOutInventoryRequestCommand.class.getSimpleName()))
                        .andCommandKey(HystrixCommandKey.Factory.asKey(TimeOutInventoryRequestCommand.class.getSimpleName()))
                        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                .withCircuitBreakerForceClosed(true)
                                .withExecutionTimeoutInMilliseconds(timeoutInMillis)), inventoryRequester, store, product
        );
    }

    @Override
    protected Inventory run() throws Exception {
        return inventoryRequester.requestInventoryFor(store, product);
    }

}