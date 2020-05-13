package Market.BootStrap;

import Market.Model.*;
import Market.Repo.ProductRepository;
import Market.Repo.ShoppingCartRepository;
import Market.Repo.UserTypeRepository;
import Market.Repo.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*
    Keep this until we can fully figure out flyway, populates tables
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final UserTypeRepository userTypeRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public BootStrapData(
                            UsersRepository usersRepository,
                            UserTypeRepository userTypeRepository,
                            ProductRepository productRepository,
                            ShoppingCartRepository shoppingCartRepository
                        )
    {
        this.usersRepository = usersRepository;
        this.userTypeRepository = userTypeRepository;
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    @Override
    public void run(String... args) throws Exception
    {
        System.out.println("Started in Bootstrap");
        //creating user instance, saving it to DB
        Users user_1 = new Users();
        user_1.setName("Peter was here");
        usersRepository.save(user_1);
        //create a shopping cart
        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCartRepository.save(shoppingCart1);
        //Creating a product
        Product item1 = new Product();
        item1.setName("facemask");
        item1.setPrice(12.99);
        item1.setDescription("this is a standard surgical facemask");
        item1.setShoppingCart(shoppingCart1);
        productRepository.save(item1);
        //create another product
        Product item2 = new Product();
        item2.setName("hand sanitizer");
        item2.setPrice(5.99);
        item2.setDescription("Purell extra-large");
        item2.setShoppingCart(shoppingCart1);
        productRepository.save(item2);
        //create a shopping cart, put above items in it
        List<Product> items = new ArrayList<>();
        items.add(item1); items.add(item2);
        shoppingCart1.setItemsInCart(items);


        System.out.println("Number of USERS: " + usersRepository.count());

    }
}
