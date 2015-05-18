package name.webdizz.fault.tolerance.inventory.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @NonNull
    private String identity;
}
