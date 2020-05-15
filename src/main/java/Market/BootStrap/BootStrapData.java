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
        Users user_1 = new Users("peter", "password");
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

        //create a buyer, put above items in its cart
        Buyer buyer = (Buyer) user_1.getUT();
        buyer.getCart().addItemToCart(item1);
        buyer.getCart().addItemToCart(item2);
        shoppingCartRepository.save(buyer.getCart());

        //Print out all data in the shopping cart
        for(Product p : buyer.getCart().getProducts())
        {
            System.out.println("\nProduct Name:" + p.getName());
            System.out.println("Product Price:" + p.getPrice());
            System.out.println("Product Quantity:" + p.getQuantity() + "\n");
        }

        //Create an order
        Orders order = new Orders();
        order.getProducts().add(item1);
        order.getProducts().add(item2);
        order.setOrderTotal(678.39);
        orderRepository.save(order);


        System.out.println("Number of USERS: " + usersRepository.count());

    }
}
