package repository;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.*;
import entity.Product;
import entity.Customer;
import entity.Director;
import entity.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class ShopDA {
    // Index Accessors
    PrimaryIndex<Integer, Shop> id;
    SecondaryIndex<Integer, Integer, Shop> customerId;

    public ShopDA(EntityStore store) throws DatabaseException {

        // Primary key for Customer class
        id = store.getPrimaryIndex(Integer.class, Shop.class);
        customerId = store.getSecondaryIndex(id, Integer.class, "customer");
    }
    public EntityCursor<Shop> cursor(){
        return this.id.entities();
    }

    public List<Shop> get(){
        return this.id.map().values().stream().toList();
    }

    public Shop get(Integer id){
        return this.id.get(id);
    }

    public List<Shop> getByCustomerId(Integer customerId){
        EntityJoin<Integer, Shop> join = new EntityJoin<>(this.id);
        join.addCondition(this.customerId, customerId);
        try (ForwardCursor<Shop> entities = join.entities()) {
            return StreamSupport.stream(entities.spliterator(), false).toList();
        }
        catch (DatabaseException exc){
            return new ArrayList<>();
        }
    }

    public Shop save(Shop shop){
        Integer id = this.id.sortedMap().lastKey();
        id = id == null ? 0 : id + 1;
        shop.setId(id);
        return this.id.put(shop);
    }

    public Shop update(Shop shop){
        return this.id.put(shop);
    }

    public boolean delete(Integer id){
        return this.id.delete(id);
    }
}
