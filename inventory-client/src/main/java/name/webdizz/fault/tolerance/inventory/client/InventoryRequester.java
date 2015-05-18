package name.webdizz.fault.tolerance.inventory.client;

import feign.Feign;
import feign.Param;
import feign.Request;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import name.webdizz.fault.tolerance.inventory.domain.Inventory;
import name.webdizz.fault.tolerance.inventory.domain.Product;
import name.webdizz.fault.tolerance.inventory.domain.Store;

public class InventoryRequester {
    public interface InventoryApi {
        @RequestLine("GET /inventories/{storeId}/{productId}")
        Inventory requestInventory(@Param("storeId") String storeId, @Param("productId") String productId);
    }

    private final InventoryApi inventoryRequest;

    public InventoryRequester(final String inventoryServiceUrl) {
        inventoryRequest = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(1000, 1000))
                .target(InventoryApi.class, inventoryServiceUrl);
    }

    public Inventory requestInventoryFor(final Store store, final Product product) {
        return inventoryRequest.requestInventory(store.getIdentity(), product.getIdentity());
    }
}
