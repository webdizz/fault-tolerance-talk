package name.webdizz.fault.tolerance.inventory.client;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import name.webdizz.fault.tolerance.inventory.domain.Inventory;

public class InventoryRequesterTest {

    @Test
    public void shouldPerformInventoryRequest() throws Exception {
        Inventory inventory = new InventoryRequester(InventoryApiTestConstants.INVENTORY_SERVICE_URL)
                .requestInventoryFor(InventoryApiTestConstants.DEFAULT_STORE, InventoryApiTestConstants.DEFAULT_PRODUCT);
        assertThat(inventory, notNullValue());
    }
}