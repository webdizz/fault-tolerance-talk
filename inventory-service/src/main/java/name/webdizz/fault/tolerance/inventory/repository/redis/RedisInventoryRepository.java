package name.webdizz.fault.tolerance.inventory.repository.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import name.webdizz.fault.tolerance.inventory.domain.Inventory;
import name.webdizz.fault.tolerance.inventory.domain.Product;
import name.webdizz.fault.tolerance.inventory.domain.Store;
import name.webdizz.fault.tolerance.inventory.repository.InventoryRepository;

@Repository
class RedisInventoryRepository implements InventoryRepository {

    private final ValueOperations<String, Inventory> inventoryValueOps;

    @Autowired
    public RedisInventoryRepository(final RedisTemplate<String, Inventory> redisTemplate) {
        inventoryValueOps = redisTemplate.opsForValue();
    }

    @Override
    public void save(final Inventory inventory) {
        inventoryValueOps.set(inventory.getKey(), inventory);
    }

    @Override
    public Inventory read(final Product product, final Store store) {
        return inventoryValueOps.get(Inventory.toKey(product, store));
    }
}
