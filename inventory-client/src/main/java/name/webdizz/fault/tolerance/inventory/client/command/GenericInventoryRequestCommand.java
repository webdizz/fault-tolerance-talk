package name.webdizz.fault.tolerance.inventory.client.command;

import name.webdizz.fault.tolerance.inventory.client.InventoryRequester;
import name.webdizz.fault.tolerance.inventory.domain.Inventory;
import name.webdizz.fault.tolerance.inventory.domain.Product;
import name.webdizz.fault.tolerance.inventory.domain.Store;

import com.netflix.hystrix.HystrixCommand;

public abstract class GenericInventoryRequestCommand extends HystrixCommand<Inventory> {

    protected final InventoryRequester inventoryRequester;
    protected final Store store;
    protected final Product product;

    public GenericInventoryRequestCommand(final Setter setter, final InventoryRequester inventoryRequester, final Store store, final Product product) {
        super(setter);
        this.inventoryRequester = inventoryRequester;
        this.store = store;
        this.product = product;
    }

    @Override
    protected Inventory getFallback() {
        return new Inventory(new Store("fallback store"), new Product("fallback product"), 0L);
    }
}
