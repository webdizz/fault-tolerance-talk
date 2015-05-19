package name.webdizz.fault.tolerance.inventory.client.command;

import name.webdizz.fault.tolerance.inventory.client.InventoryRequester;
import name.webdizz.fault.tolerance.inventory.client.health.ResourceHealthIndicator;
import name.webdizz.fault.tolerance.inventory.domain.Inventory;
import name.webdizz.fault.tolerance.inventory.domain.Product;
import name.webdizz.fault.tolerance.inventory.domain.Store;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class FailFastInventoryRequestCommand extends GenericInventoryRequestCommand {

    private final ResourceHealthIndicator healthIndicator;

    public FailFastInventoryRequestCommand(final InventoryRequester inventoryRequester, final Store store, final Product product, final ResourceHealthIndicator healthIndicator) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(FailFastInventoryRequestCommand.class.getSimpleName()))
                .andCommandKey(HystrixCommandKey.Factory.asKey(FailFastInventoryRequestCommand.class.getSimpleName()))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                .withCircuitBreakerForceClosed(true)
                ), inventoryRequester, store, product);
        this.healthIndicator = healthIndicator;
    }

    @Override
    protected Inventory run() throws Exception {
        if (healthIndicator.isHealthy()) {
            return inventoryRequester.requestInventoryFor(store, product);
        } else {
            throw new ResourceIsNotInAgooShapeException();
        }
    }

    private static class ResourceIsNotInAgooShapeException extends RuntimeException {
        public ResourceIsNotInAgooShapeException() {
            super("Resource is not in a good shape so please fail fast");
        }
    }

}
