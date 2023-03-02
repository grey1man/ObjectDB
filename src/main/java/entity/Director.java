package entity;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;


@Entity
public class Director {
    @PrimaryKey(sequence="director_pk")
    private int id;
    private String phoneNumber;
    private String name;
    private int age;
    public Director() {
    }

    public Director(int id, String phoneNumber, int age, String name) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.age = age;
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
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String name) { this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
