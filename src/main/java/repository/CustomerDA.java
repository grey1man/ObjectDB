package repository;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import entity.Customer;

import java.util.List;
import java.util.Map;

public class CustomerDA {
    // Index Accessors
    PrimaryIndex<Integer, Customer> id;

    public CustomerDA(EntityStore store) throws DatabaseException {

        // Primary key for Customer class
        id = store.getPrimaryIndex(Integer.class, Customer.class);
    }

    public List<Customer> get(){
        return this.id.map().values().stream().toList();
    }

    public Customer save(Customer customer){
        Integer id = this.id.sortedMap().lastKey();
        id = id == null ? 0 : id + 1;
        customer.setId(id);
        return this.id.put(customer);
    }

}
