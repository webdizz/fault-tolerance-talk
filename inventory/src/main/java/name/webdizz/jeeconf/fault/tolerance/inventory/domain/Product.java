package name.webdizz.jeeconf.fault.tolerance.inventory.domain;

import java.io.Serializable;

import lombok.NonNull;
import lombok.Value;

@Value
public class Product implements Serializable {
    @NonNull
    private String identity;
}
