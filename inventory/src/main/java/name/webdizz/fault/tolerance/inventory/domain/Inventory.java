package name.webdizz.fault.tolerance.inventory.domain;

import java.io.Serializable;

import lombok.NonNull;
import lombok.Value;

@Value
public class Inventory implements Serializable {
    @NonNull
    private Store store;
    @NonNull
    private Product product;
    private long amount;

    public String getKey() {
        return product.getIdentity() + "@" + store.getIdentity();
    }

    public static String toKey(final @NonNull Product product, final @NonNull Store store) {
        return product.getIdentity() + "@" + store.getIdentity();
    }
}
