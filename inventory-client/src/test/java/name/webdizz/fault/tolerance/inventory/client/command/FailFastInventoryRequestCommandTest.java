package name.webdizz.fault.tolerance.inventory.client.command;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.DEFAULT_PRODUCT;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.DEFAULT_STORE;

import org.junit.Test;
import org.mockito.Mockito;
import name.webdizz.fault.tolerance.inventory.client.InventoryRequester;

public class FailFastInventoryRequestCommandTest {
    private InventoryRequester inventoryRequester = Mockito.mock(InventoryRequester.class);
    private boolean healthIsOK = true;
    private boolean healthIsNOK = false;

    @Test
    public void shouldSucceedIfResourceIsNotOK() throws Exception {
        FailFastInventoryRequestCommand requestCommand;
        requestCommand = new FailFastInventoryRequestCommand(inventoryRequester, DEFAULT_STORE, DEFAULT_PRODUCT, () -> healthIsOK);
        requestCommand.execute();
        boolean complete = true;
        boolean execution = requestCommand.isExecutionComplete();
        assertThat(execution, is(complete));
    }

    @Test
    public void shouldFailFastIfResourceIsNotOK() throws Exception {
        FailFastInventoryRequestCommand requestCommand;
        requestCommand = new FailFastInventoryRequestCommand(inventoryRequester, DEFAULT_STORE, DEFAULT_PRODUCT, () -> healthIsNOK);
        requestCommand.execute();
        boolean failed = true;
        boolean execution = requestCommand.isFailedExecution();
        assertThat(execution, is(failed));
    }
}