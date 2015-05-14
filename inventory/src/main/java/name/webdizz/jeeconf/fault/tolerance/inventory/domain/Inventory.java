package name.webdizz.jeeconf.fault.tolerance.inventory.domain;

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
}
