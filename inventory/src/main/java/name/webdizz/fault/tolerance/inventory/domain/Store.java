package name.webdizz.fault.tolerance.inventory.domain;

import java.io.Serializable;

import lombok.NonNull;
import lombok.Value;

@Value
public class Store implements Serializable {
    @NonNull
    private String identity;
}
