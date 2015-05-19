package name.webdizz.fault.tolerance.inventory.client.command;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.DEFAULT_PRODUCT;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.DEFAULT_STORE;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.INVENTORY_SERVICE_URL;

import org.junit.Test;
import name.webdizz.fault.tolerance.inventory.client.InventoryRequester;

public class TimeOutInventoryRequestCommandTest {

    private static final int TIMEOUT_IN_MILLIS = 10;
    private InventoryRequester inventoryRequester = new InventoryRequester(INVENTORY_SERVICE_URL);
    private final boolean timedOut = true;

    @Test
    public void shouldNotCompleteInventoryRequestDueToTimeout() {
        TimeOutInventoryRequestCommand timeOutInventoryRequestCommand;
        timeOutInventoryRequestCommand = new TimeOutInventoryRequestCommand(inventoryRequester, DEFAULT_STORE, DEFAULT_PRODUCT, TIMEOUT_IN_MILLIS);
        boolean request = timeOutInventoryRequestCommand.isResponseTimedOut();
        assertThat(request, is(timedOut));
    }
}