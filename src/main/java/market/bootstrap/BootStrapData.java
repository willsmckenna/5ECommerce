package market.bootstrap;
import market.model.buyerRelated.OrderTracking;
import market.model.buyerRelated.PaymentInfo;
import market.model.products.Product;
import market.model.reviews.Review;
import market.model.buyerRelated.Orders;
import market.model.buyerRelated.ShippingAddress;
import market.model.buyerRelated.ShoppingCart;
import market.model.messages.Message;
import market.model.userTypes.Admin;
import market.model.userTypes.Buyer;
import market.model.userTypes.Seller;
import market.model.userTypes.Users;
import market.repo.*;
import market.repo.AdminRepo;
import market.repo.BuyerRepository;
import market.repo.SellerRepository;
import market.repo.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class BootStrapData implements CommandLineRunner {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;
    private final AdminRepo adminRepo;
    private final ProductRepository productRepository;
    private final ShippingAddressRepository shippingAddressRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepo paymentRepo;
    private final ShoppingCartProductsRepository shoppingCartProductsRepository;

    public BootStrapData(UsersRepository userRepository, PasswordEncoder passwordEncoder, BuyerRepository buyerRepository, SellerRepository sellerRepository, AdminRepo adminRepo, ProductRepository productRepository, ShippingAddressRepository shippingAddressRepository, ShoppingCartRepository shoppingCartRepository, OrderRepository orderRepository, PaymentRepo paymentRepo, ShoppingCartProductsRepository shoppingCartProductsRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.adminRepo = adminRepo;
        this.productRepository = productRepository;
        this.shippingAddressRepository = shippingAddressRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.orderRepository = orderRepository;
        this.paymentRepo = paymentRepo;
        this.shoppingCartProductsRepository = shoppingCartProductsRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        System.out.println("Started in Bootstrap");

        /*Delete all data that may exist in the database, usernames must be unique*/
        userRepository.deleteAll();
        buyerRepository.deleteAll();
        sellerRepository.deleteAll();
        productRepository.deleteAll();
        shippingAddressRepository.deleteAll();
        shoppingCartRepository.deleteAll();
        orderRepository.deleteAll();
        paymentRepo.deleteAll();
        adminRepo.deleteAll();
        shoppingCartProductsRepository.deleteAll();

        /* Make the global users that exist in the System */

        //Any new users need to have password encrypted before db insert
        Users user_1 = new Users("buyer_1", passwordEncoder.encode("peter12"),"BUYER", "none");
        Users user_2 = new Users("seller_1", passwordEncoder.encode("peter12"),"SELLER", "none");
        Users user_3 = new Users("admin_1", passwordEncoder.encode("peter12"),"ADMIN", "");

        userRepository.save(user_1);
        userRepository.save(user_2);
        userRepository.save(user_3);

        /* Make some buyers */
        Buyer buyer_1 = new Buyer("buyer_1", "peter", "lastname1");
        buyerRepository.save(buyer_1);

        /* Make some Sellers */
        Seller seller_1 = new Seller("seller_1", "sellerFirstName", "lastname2");
        sellerRepository.save(seller_1);

        /* Make some Admins */
        Admin admin_1 = new Admin("admin_1", "adminFirstName", "lastname3");
        adminRepo.save(admin_1);

        /*Make some products Associated with Seller_1*/
        Set<Product> demoProducts = new HashSet<Product>();

        Product product_1 = new Product();
        product_1.setSeller(seller_1);
        product_1.setName("A skate board");
        product_1.setPrice(119.99);
        product_1.setQuantity(1);
        product_1.setDescription("Very cool please buy");
        product_1.setSeedImageStr("1200px-Globe_Skateboard.jpg");
        demoProducts.add(product_1);

        Product product_2 = new Product();
        product_2.setSeller(seller_1);
        product_2.setName("Surf Board");
        product_2.setPrice(219.99);
        product_2.setQuantity(1);
        product_2.setDescription("Also pretty cool");
        product_2.setSeedImageStr("img24o.jpg");
        demoProducts.add(product_2);

        Product product_3 = new Product();
        product_3.setSeller(seller_1);
        product_3.setName("Bike");
        product_3.setPrice(109.99);
        product_3.setQuantity(1);
        product_3.setDescription("Lasts literally forever");
        product_3.setSeedImageStr("bike.jpg");
        demoProducts.add(product_3);

        Product product_4 = new Product();
        product_4.setSeller(seller_1);
        product_4.setName("Facemask");
        product_4.setPrice(109.99);
        product_4.setQuantity(1);
        product_4.setDescription("Standard surgical facemask");
        demoProducts.add(product_4);
        product_4.setSeedImageStr("facemask.jpg");
        productRepository.saveAll(demoProducts);

        /*Make shipping address for seller_1*/
        Set<ShippingAddress> demoShippingAddresses = new HashSet<ShippingAddress>();

        ShippingAddress shippingAddress_1 = new ShippingAddress();
        shippingAddress_1.setShippingAddress("Chicago, IL 60614");
        shippingAddress_1.setSeller(seller_1);
        demoShippingAddresses.add(shippingAddress_1);

        ShippingAddress shippingAddress_2 = new ShippingAddress();
        shippingAddress_2.setShippingAddress("1 E Jackson Blvd, Chicago, IL 60604");
        shippingAddress_2.setSeller(seller_1);
        shippingAddress_2.setBuyer(buyer_1);
        demoShippingAddresses.add(shippingAddress_2);

        shippingAddressRepository.saveAll(demoShippingAddresses);

        /*Make some a cart, add products to the cart*/
        ShoppingCart cart_1 = new ShoppingCart();
        cart_1.setBuyer(buyer_1);
        cart_1.addProduct(product_1);
        cart_1.addProduct(product_2);
        cart_1.addProduct(product_3);
        shoppingCartRepository.save(cart_1);
        shoppingCartProductsRepository.saveAll(cart_1.getShoppingCartProducts());
        buyerRepository.save(buyer_1);


        /*Make an order*/
        Orders orders_1 = new Orders();
        buyer_1.getOrders().add(orders_1);
        orders_1.setBuyer(buyer_1);

        double order_1_Price = 0.0;
        for(Product p : demoProducts) {
            orders_1.addProduct(p,p.getQuantity());
            order_1_Price += p.getPrice();
        }
        orders_1.setOrderTotal(order_1_Price);
        OrderTracking tracking = new OrderTracking();
        tracking.setStatus("Ordered, not yet Shipped");
        orders_1.setOrderTrackingContents(tracking);
        orderRepository.save(orders_1);

        /*Make a few payment infos*/
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Set<PaymentInfo> demoPaymentInfo = new HashSet<PaymentInfo>();

        PaymentInfo paymentInfo_1 = new PaymentInfo();
        paymentInfo_1.setBuyer(buyer_1);
        paymentInfo_1.setCreditCardNum(10101202);
        paymentInfo_1.setCvv(210);
        paymentInfo_1.setExpirationDate(date);
        demoPaymentInfo.add(paymentInfo_1);

        PaymentInfo paymentInfo_2 = new PaymentInfo();
        paymentInfo_2.setSeller(seller_1);
        paymentInfo_1.setCreditCardNum(10101202);
        paymentInfo_1.setCvv(210);
        paymentInfo_1.setExpirationDate(date);
        demoPaymentInfo.add(paymentInfo_2);

        paymentRepo.saveAll(demoPaymentInfo);


        // Adding Seller Review
        Review review_1 = new Review();
        review_1.setAuthor(buyer_1.getUsername());
        review_1.setContent("Great seller!!!!");
        review_1.setDate(new Date());
        seller_1.getReviews().add(review_1);
        sellerRepository.save(seller_1);

        //Make some messages: STRESS TEST
        Message message_1 = new Message();
        message_1.setFrom(seller_1.getUsername());
        message_1.setTo(admin_1.getUsername());
        message_1.setSubject("I Hope I am doing this right..");
        message_1.setMessagePayload("Because, If I am not, I am more than likely doomed");
        admin_1.addMessage(message_1);
        adminRepo.save(admin_1);
    }
}
