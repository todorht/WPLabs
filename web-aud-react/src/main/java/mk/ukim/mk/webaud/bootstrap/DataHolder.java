package mk.ukim.mk.webaud.bootstrap;

import mk.ukim.mk.webaud.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component  //se kreira nova istancira
public class DataHolder {
    public static List<Category> categories = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();

//    @PostConstruct  //cim se instancira, se starta init metodot
//    public void init(){
//        categories.add(new Category("Software","Software Category"));
//        categories.add(new Category("Books", "Books Category"));
//
//        users.add( new User("todorht", "tode", "Hristijan", "Todorovski"));
//
//        Manufacturer manufacturer = new Manufacturer("Nike", "NY NY");
//        manufacturers.add(manufacturer);
//
//        Category category = new Category("Sport", "Sport category");
//        categories.add(category);
//        products.add(new Product("Ball 1",235.8,8,category,manufacturer));
//        products.add(new Product("Ball 2",200.8,8,category,manufacturer));
//        products.add(new Product("Ball 3",135.8,8,category,manufacturer));
//        products.add(new Product("Ball 4",305.8,8,category,manufacturer));
//    }
}
