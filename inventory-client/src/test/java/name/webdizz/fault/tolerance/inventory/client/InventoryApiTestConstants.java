package name.webdizz.fault.tolerance.inventory.client;

import name.webdizz.fault.tolerance.inventory.domain.Product;
import name.webdizz.fault.tolerance.inventory.domain.Store;

public class InventoryApiTestConstants {
    public static final String INVENTORY_SERVICE_URL = "http://ft.dev:8080";
    public static final Product DEFAULT_PRODUCT = new Product("1225");
    public static final Store DEFAULT_STORE = new Store("23434");
}
