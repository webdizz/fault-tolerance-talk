package name.webdizz.fault.tolerance.inventory.client.command;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.DEFAULT_PRODUCT;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.DEFAULT_STORE;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.INVENTORY_SERVICE_URL;

import org.junit.Test;
import name.webdizz.fault.tolerance.inventory.client.InventoryRequester;
import name.webdizz.fault.tolerance.inventory.domain.Inventory;

public class TimeOutInventoryRequestCommandTest {

    private static final int TIMEOUT_IN_MILLIS = 10;
    private InventoryRequester inventoryRequester = new InventoryRequester(INVENTORY_SERVICE_URL);

    @Test
    public void shouldNotCompleteInventoryRequestDueToTimeout() {
        TimeOutInventoryRequestCommand timeOutInventoryRequestCommand;
        timeOutInventoryRequestCommand = new TimeOutInventoryRequestCommand(TIMEOUT_IN_MILLIS, inventoryRequester, DEFAULT_STORE, DEFAULT_PRODUCT);
        Inventory inventory = timeOutInventoryRequestCommand.execute();
        assertThat(timeOutInventoryRequestCommand.isResponseTimedOut(), is(true));
    }
}