package name.webdizz.fault.tolerance.inventory.client;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import name.webdizz.fault.tolerance.inventory.domain.Inventory;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.rule.MockWebServerRule;

public class InventoryRequesterTest {

    @Rule
    public final MockWebServerRule server = new MockWebServerRule();

    @Before
    public void setUp() {
        server.enqueue(new MockResponse().setBody("{\"store\":{\"identity\":\"23434\"},\"product\":{\"identity\":\"1225\"},\"amount\":1000,\"key\":\"1225@23434\"}"));
    }

    @Test
    public void shouldPerformInventoryRequest() throws Exception {
        Inventory inventory = new InventoryRequester("http://127.0.0.1:" + server.getPort())
                .requestInventoryFor(InventoryApiTestConstants.DEFAULT_STORE, InventoryApiTestConstants.DEFAULT_PRODUCT);
        assertThat(inventory, notNullValue());
    }
}