package Market.bootStrap;
import Market.model.OrderTrackingContent;
import Market.model.PaymentInfo;
import Market.model.Product;
import Market.model.Review;
import Market.model.buyerRelated.Orders;
import Market.model.buyerRelated.ShippingAddress;
import Market.model.buyerRelated.ShoppingCart;
import Market.model.messages.Message;
import Market.model.userTypes.Admin;
import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;
import Market.model.userTypes.Users;
import Market.repo.*;
import Market.repo.AdminRepo;
import Market.repo.BuyerRepository;
import Market.repo.SellerRepository;
import Market.repo.UsersRepository;
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
        demoProducts.add(product_1);

        Product product_2 = new Product();
        product_2.setSeller(seller_1);
        product_2.setName("Surf Board");
        product_2.setPrice(219.99);
        product_2.setQuantity(1);
        product_2.setDescription("Also pretty cool");
        demoProducts.add(product_2);

        Product product_3 = new Product();
        product_3.setSeller(seller_1);
        product_3.setName("Bike");
        product_3.setPrice(109.99);
        product_3.setQuantity(1);
        product_3.setDescription("Lasts literally forever");
        demoProducts.add(product_3);

        Product product_4 = new Product();
        product_4.setSeller(seller_1);
        product_4.setName("Facemask");
        product_4.setPrice(109.99);
        product_4.setQuantity(1);
        product_4.setDescription("Standard surgical facemask");
        demoProducts.add(product_4);
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
        OrderTrackingContent tracking = new OrderTrackingContent();
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


        /* Make a seller review: */
        Review review_1 = new Review();
        review_1.setAuthor(buyer_1.getUsername());
        review_1.setContent("Because, If I am not, I am more than likely doomed");
        review_1.setSellerUN(seller_1.getUsername());
        review_1.setDate(date);
        seller_1.getReviews().put("wut", review_1);
        sellerRepository.save(seller_1);

        //Get the review:
        System.out.println(seller_1.getReviews().containsKey("wut"));


        //Make some messages: STRESS TEST
        Message message_1 = new Message();
        message_1.setFromUsername(seller_1.getUsername());
        message_1.setToUsername(admin_1.getUsername());
        message_1.setSubject("I Hope I am doing this right..");
        message_1.setMessagePayload("Because, If I am not, I am more than likely doomed");
        admin_1.addMessage(message_1);
        adminRepo.save(admin_1);
    /*
        Message messageContent_2 = new Message();
        messageContent_2.setFromUsername(buyer_1.getUsername());
        messageContent_2.setToUsername(seller_1.getUsername());
        messageContent_2.setSubject("Wild!");
        messageContent_2.setMessagePayload("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" + "\n");
        messageHeader_1.getMessageContents().add(messageContent_2);
        this.messageRepository.save(messageHeader_1);


        Message messageContent_3 = new Message();
        messageContent_3.setFromUsername(admin_1.getUsername());
        messageContent_3.setToUsername(seller_1.getUsername());
        messageContent_3.setSubject("Word!");
        messageContent_3.setMessagePayload("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).");
        messageHeader_1.getMessageContents().add(messageContent_3);
        this.messageRepository.save(messageHeader_1);

        Message messageContent_4 = new Message();
        messageContent_4.setFromUsername(admin_1.getUsername());
        messageContent_4.setToUsername(seller_1.getUsername());
        messageContent_4.setSubject("Word!");
        messageContent_4.setMessagePayload("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.");
        messageHeader_1.getMessageContents().add(messageContent_4);
        this.messageRepository.save(messageHeader_1);

         */

    }
}
