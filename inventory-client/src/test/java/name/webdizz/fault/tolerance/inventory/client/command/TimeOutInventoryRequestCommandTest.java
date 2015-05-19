package name.webdizz.fault.tolerance.inventory.client.command;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.DEFAULT_PRODUCT;
import static name.webdizz.fault.tolerance.inventory.client.InventoryApiTestConstants.DEFAULT_STORE;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import name.webdizz.fault.tolerance.inventory.client.InventoryRequester;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.rule.MockWebServerRule;

public class TimeOutInventoryRequestCommandTest {

    private static final int TIMEOUT_IN_MILLIS = 1;
    private InventoryRequester inventoryRequester;
    private final boolean timedOut = true;

    @Rule
    public final MockWebServerRule server = new MockWebServerRule();

    @Before
    public void setUp() {
        server.enqueue(new MockResponse().setBodyDelayTimeMs(100));
        inventoryRequester = new InventoryRequester("http://127.0.0.1:" + server.getPort());
    }

    @Test
    public void shouldNotCompleteInventoryRequestDueToTimeout() {
        TimeOutInventoryRequestCommand timeOutInventoryRequestCommand;
        timeOutInventoryRequestCommand = new TimeOutInventoryRequestCommand(inventoryRequester, DEFAULT_STORE, DEFAULT_PRODUCT, TIMEOUT_IN_MILLIS);
        timeOutInventoryRequestCommand.execute();
        boolean request = timeOutInventoryRequestCommand.isResponseTimedOut();
        assertThat(request, is(timedOut));
    }
}