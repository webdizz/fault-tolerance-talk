package name.webdizz.fault.tolerance.inventory.client.command;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.DEFAULT_PRODUCT;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.DEFAULT_STORE;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import name.webdizz.fault.tolerance.inventory.client.InventoryRequester;
import name.webdizz.fault.tolerance.inventory.domain.Inventory;
import name.webdizz.fault.tolerance.inventory.domain.Product;
import name.webdizz.fault.tolerance.inventory.domain.Store;

public class ShedLoadInventoryRequestCommandTest {

    private static final int TIMEOUT_IN_MILLIS = 100;
    private static final int MAX_ALLOWED_THREADS = 10;
    private InventoryRequester inventoryRequester;

    @Before
    public void setUp() {
        inventoryRequester = Mockito.mock(InventoryRequester.class);
        Answer<Inventory> answer = new Answer<Inventory>() {
            @Override
            public Inventory answer(final InvocationOnMock invocation) throws Throwable {
                Thread.sleep(TIMEOUT_IN_MILLIS); //add some waiting time
                return new Inventory(new Store("store"), new Product("1225"), 100);
            }
        };
        when(inventoryRequester.requestInventoryFor(any(Store.class), any(Product.class))).thenAnswer(answer);
    }

    @Test
    public void shouldShedLoadInventoryRequest() throws ExecutionException, InterruptedException {
        List<Future<Inventory>> scheduledCommands = new LinkedList<>();
        for (int i = 0; i < MAX_ALLOWED_THREADS; i++) {
            scheduledCommands.add(enqueueRequest());
        }
        Inventory inventory = new ShedLoadInventoryRequestCommand(inventoryRequester, DEFAULT_STORE, DEFAULT_PRODUCT).execute();
        assertThat(inventory.getProduct().getIdentity(), is("fallback product")); // thread pool is almost full, this should be rejected

        for (Future<Inventory> scheduledCommand : scheduledCommands) {
            assertThat(scheduledCommand.get().getProduct().getIdentity(), is("1225"));// as these are already scheduled commands they should succeed
        }
    }

    private Future<Inventory> enqueueRequest() {
        ShedLoadInventoryRequestCommand inventoryRequestCommand;
        inventoryRequestCommand = new ShedLoadInventoryRequestCommand(inventoryRequester, DEFAULT_STORE, DEFAULT_PRODUCT);
        return inventoryRequestCommand.queue();
    }
}