package entity;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.SecondaryKey;
import com.sleepycat.persist.model.Relationship;

import java.util.List;
@Entity
public class Shop {
    @PrimaryKey(sequence="shop_pk")
    private int id;
    private String address;
    private String specification;
    //@SecondaryKey(relate = Relationship.MANY_TO_MANY, relatedEntity = Product.class, name = "product")
    private List<Integer> productId;
    //@SecondaryKey(relate = Relationship.ONE_TO_ONE, relatedEntity = Director.class, name = "director")
    private int directorId;
    @SecondaryKey(relate = Relationship.MANY_TO_ONE, relatedEntity = Customer.class, name = "customer")
    private int customerId;
    public Shop() {
    }

    public Shop(int id, String address,
                String specification, List<Integer> productId,
                int directorId, int customerId) {
        this.id = id;
        this.address = address;
        this.specification = specification;
        this.productId = productId;
        this.directorId = directorId;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) { this.address = address; }
    public String getSpecification() {
        return specification;
    }
    public void setSpecification(String specification) { this.specification = specification; }
    public List<Integer> getProductId() {
        return productId;
    }
    public void setProductId(List<Integer> productId) {
        this.productId = productId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }



    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", specification='" + specification + '\'' +
                ", productId='" + productId + '\'' +
                ", directorId='" + directorId + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
