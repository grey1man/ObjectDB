import com.sleepycat.persist.EntityCursor;
import config.Database;
import entity.Customer;
import entity.Shop;
import entity.Customer;
import repository.CustomerDA;
import repository.ShopDA;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class app {
    private static final Random random = new Random();

    public static void Lab3(){
        System.out.println("Lab 3 Starting");
        Database.setup();
        ShopDA orderDA = new ShopDA(Database.getStore());
        CustomerDA customerDA = new CustomerDA(Database.getStore());

        Customer customerOne = new Customer(0,
                                            "M",
                                            33,
                                            "Sasha"

        );

        Customer customerTwo = new Customer(1,
                                        "F",
                                        44,
                                       "Masha"

        );

        customerDA.save(customerOne);
        customerDA.save(customerTwo);

        List<Integer> productList = new ArrayList<Integer>();
        productList.add(5);

        Shop one = new Shop(
                0,
                "street 1 house 1",
                "Bakery",
                productList,
                0,
                customerOne.getId()
        );

        Shop two = new Shop(
                1,
                "street 2 house 2",
                "Meat shop",
                productList,
                1,
                customerTwo.getId()
        );

        orderDA.save(one);
        orderDA.save(two);

        List<Shop> shops = orderDA.get();
        System.out.println("Shops: ");
        System.out.println(shops);

        System.out.println("Shop with id = 2: ");
        System.out.println(orderDA.get(2));


        System.out.println("Shop for customer with id = 1: ");
        System.out.println(orderDA.getByCustomerId(1));

        System.out.println(orderDA.delete(2));
        one.setAddress("street 3 house 3");
        System.out.println(orderDA.update(one));

        System.out.println("Shop after deleting and updating: ");
        System.out.println(orderDA.get());
    }

    public static void Lab4() {
        System.out.println("Lab 4 Starting");
        Database.setup();
        ShopDA shopDA = new ShopDA(Database.getStore());
        CustomerDA customerDA = new CustomerDA(Database.getStore());

        Customer customer = new Customer(1,
                "M",
                33,
                "Sasha"

        );
        customerDA.save(customer);

        System.out.println("Customers: ");
        System.out.println(customerDA.get());

        List<Integer> productList = new ArrayList<Integer>();
        productList.add(5);
        Shop one = new Shop(
                0,
                "street 1 house 1",
                "Bakery",
                productList,
                0,
                customer.getId()
        );

        shopDA.save(one);

        int target = one.getId();

        try (EntityCursor<Shop> entityCursor = shopDA.cursor()) {
            for (Shop shop: entityCursor){
                if (shop.getId() == target)
                {
                    System.out.println("Found order with target id: ");
                    System.out.println(shop);
                }
            }
        }

        target = one.getId();

        System.out.println("Shops before updating: ");
        System.out.println(shopDA.get(target));

        try (EntityCursor<Shop> entityCursor = shopDA.cursor()) {
            for (Shop shop: entityCursor){
                if (shop.getId() == 2)
                {
                    shop.setAddress("street 3 house 3");
                    entityCursor.update(shop);
                }
            }
        }

        System.out.println("Shops after updating: ");
        System.out.println(shopDA.get(target));
    }

    public static void main(String[] args) {
        Lab3();
        Lab4();
    }
}
