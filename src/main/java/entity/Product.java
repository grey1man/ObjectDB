package entity;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.SecondaryKey;
import com.sleepycat.persist.model.Relationship;

@Entity
public class Product {
    @PrimaryKey(sequence="product_pk")
    private int id;
    private String name;
    private float cost;
    public Product() {
    }

    public Product(int id, String phoneNumber, int cost, String name) {
        this.id = id;
        this.cost = cost;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }
    public float getCost() {
        return cost;
    }
    public void setCost(float cost) { this.cost = cost; }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
