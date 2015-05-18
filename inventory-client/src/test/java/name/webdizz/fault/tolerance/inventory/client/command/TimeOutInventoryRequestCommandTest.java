package name.webdizz.fault.tolerance.inventory.client.command;

import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.DEFAULT_PRODUCT;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.DEFAULT_STORE;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.INVENTORY_SERVICE_URL;

import org.junit.Test;
import name.webdizz.fault.tolerance.inventory.client.InventoryRequester;

import com.netflix.hystrix.exception.HystrixRuntimeException;

public class TimeOutInventoryRequestCommandTest {

    private static final int TIMEOUT_IN_MILLIS = 10;
    private InventoryRequester inventoryRequester = new InventoryRequester(INVENTORY_SERVICE_URL);

    @Test(expected = HystrixRuntimeException.class)
    public void shouldNotCompleteInventoryRequestDueToTimeout() throws Exception {
        TimeOutInventoryRequestCommand timeOutInventoryRequestCommand;
        timeOutInventoryRequestCommand = new TimeOutInventoryRequestCommand(TIMEOUT_IN_MILLIS, inventoryRequester, DEFAULT_STORE, DEFAULT_PRODUCT);
        timeOutInventoryRequestCommand.execute();
    }
}