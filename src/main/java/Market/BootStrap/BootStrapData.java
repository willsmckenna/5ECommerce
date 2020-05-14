package Market.BootStrap;

import Market.Model.*;
import Market.Repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
    Keep this until we can fully figure out flyway, populates tables
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderRepository orderRepository;
    private final AdminRepo adminRepo;
    public BootStrapData(
                            UsersRepository usersRepository,
                            ProductRepository productRepository,
                            ShoppingCartRepository shoppingCartRepository,
                            OrderRepository orderRepository,
                            AdminRepo adminRepo
                        )
    {

        this.usersRepository = usersRepository;
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.orderRepository = orderRepository;
        this.adminRepo = adminRepo;
    }


    @Override
    public void run(String... args) throws Exception
    {
        System.out.println("Started in Bootstrap");

        //creating user instance, saving it to DB
        Users user_1 = new Users();
        user_1.setName("Peter was here");
        usersRepository.save(user_1);

        //Create an admin test
        Admin admin = new Admin();
        admin.setName("Test Admin");
        this.adminRepo.save(admin);


        //Creating a product
        Product item1 = new Product();
        item1.setName("facemask");
        item1.setPrice(12.99);
        item1.setDescription("this is a standard surgical facemask");
        productRepository.save(item1);

        //create another product
        Product item2 = new Product();
        item2.setName("hand sanitizer");
        item2.setPrice(5.99);
        item2.setDescription("Purell extra-large");
        productRepository.save(item2);


        /*
        //create a shopping cart, put above items in it
        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCartRepository.save(shoppingCart1);
        List<Product> items = new ArrayList<>();
        items.add(item1); items.add(item2);
        shoppingCart1.setItemsInCart(items);
        */

        /*
        //Create an order
        Orders order = new Orders();
        order.getProduct_list().add(item1);
        order.getProduct_list().add(item2);
        orderRepository.save(order);
        */

        System.out.println("Number of USERS: " + usersRepository.count());

    }
}
