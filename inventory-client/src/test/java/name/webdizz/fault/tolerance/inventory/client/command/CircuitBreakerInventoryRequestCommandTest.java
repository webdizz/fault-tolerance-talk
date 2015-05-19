package name.webdizz.fault.tolerance.inventory.client.command;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.DEFAULT_PRODUCT;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.DEFAULT_STORE;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.INVENTORY_SERVICE_URL;

import org.junit.Test;
import name.webdizz.fault.tolerance.inventory.client.InventoryRequester;
import name.webdizz.fault.tolerance.inventory.domain.Inventory;

public class CircuitBreakerInventoryRequestCommandTest {
    private InventoryRequester inventoryRequester = new InventoryRequester(INVENTORY_SERVICE_URL);
    private static final boolean closed = false;

    @Test
    public void shouldExecuteWithCircuitBreakerClosed() throws Exception {
        CircuitBreakerInventoryRequestCommand circuitBreakerInventoryRequestCommand;
        circuitBreakerInventoryRequestCommand = new CircuitBreakerInventoryRequestCommand(inventoryRequester, DEFAULT_STORE, DEFAULT_PRODUCT, false);
        Inventory inventory = circuitBreakerInventoryRequestCommand.execute();
        boolean circuitBreaker = circuitBreakerInventoryRequestCommand.isCircuitBreakerOpen();
        assertThat(circuitBreaker, is(closed));
    }
}