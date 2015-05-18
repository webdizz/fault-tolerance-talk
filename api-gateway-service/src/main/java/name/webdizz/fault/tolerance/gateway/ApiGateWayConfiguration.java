package name.webdizz.fault.tolerance.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import name.webdizz.fault.tolerance.inventory.client.InventoryRequester;

@Configuration
public class ApiGateWayConfiguration {


    @Value("${apigateway.inventory.url}")
    private String inventoryUrl;

    @Bean
    public InventoryRequester inventoryRequester() {
        return new InventoryRequester(inventoryUrl);
    }
}
