package market.controller;

import market.model.buyerRelated.PaymentInfo;
import market.model.products.Product;
import market.model.buyerRelated.*;
import market.model.messages.Message;
import market.model.products.ShoppingCartProducts;
import market.model.userTypes.Buyer;
import market.model.userTypes.Seller;
import market.model.userTypes.Users;
import market.repo.*;
import market.service.BuyerService;
import market.service.MessagingService;
import market.service.UserService;
import market.service.implementation.ShoppingCartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("buyer")
public class BuyerController {

    @Autowired
    private ShoppingCartServiceImp shoppingCartServiceImp;

    @Autowired
    BuyerService buyerService;

    @Autowired
    UserService userService;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    MessagingService messagingService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SellerRepository sellerRepository;

    static String usernamePlaceHolder ="";

    public BuyerController(){
    }

    @GetMapping("index")
    public String getBuyerAccount() {
        return "buyer/index";
    }

    @GetMapping("cart")
    public String getBuyerCart(HttpServletRequest request, Model model) {
        String user = request.getUserPrincipal().getName();

        Buyer buyer = buyerRepository.findByUsername(user);
        ShoppingCart sc = shoppingCartRepository.findByBuyer(buyer);


        Set<ShoppingCartProducts> shoppingCartProducts = sc.getShoppingCartProducts();
        Set <Product> items = new HashSet<Product>();

        for(ShoppingCartProducts SCP : shoppingCartProducts) {
            Product p = productRepository.getById(SCP.getProductId());
            if(p != null) {
                items.add(p);
            }
        }
        double total = 0;
        for (Product p: items){
            total = total + p.getPrice()*p.getQuantity();
        }

        model.addAttribute("total",total);
        model.addAttribute("items", items);
        return "buyer/cart";
    }

    @RequestMapping(value = "productview/info/add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("product") Product product, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "buyer/cart";
        }
        //get product(s) that was asked for
        Set<Product> productToAdd = productRepository.findAllByNameLike(product.getName());

        //get the first one you can if there are dups
        Product p = productToAdd.iterator().next();

        System.out.println("we want add :" + p.getName());

        //get currently logged in user/buyer
        String user = request.getUserPrincipal().getName();
        Buyer buyer = buyerRepository.findByUsername(user);

        //get buyer's cart
        ShoppingCart cart = shoppingCartRepository.findByBuyer(buyer);

        //Add product to that cart
        cart.addProduct(p);
        shoppingCartRepository.save(cart);

        return "redirect:/browse";
    }

    @RequestMapping(value = "buyer/cart/checkout", method = RequestMethod.POST)
    public String checkOut(HttpServletRequest request) {
        //get currently logged in user/buyer
        String user = request.getUserPrincipal().getName();
        Buyer buyer = buyerRepository.findByUsername(user);
        //get shopping cart products
        Set<ShoppingCartProducts>products = shoppingCartRepository.findByBuyer(buyer).getShoppingCartProducts();
        //create a new order
        Orders order = new Orders();
        double total = 0;

        for (ShoppingCartProducts p: products){
            Product product = productRepository.findById(p.getProductId()).orElse(null);
            Seller seller = product.getSeller();
            seller.addSoldProduct(product, buyer.getUsername());
            sellerRepository.save(seller);

            total += product.getPrice();
            order.addProduct(product, 1);
        }

        order.setBuyer(buyer);
        order.setOrderTotal(total);
        orderRepository.save(order);
        //also empty current items in shopping cart since they were checked out
        ShoppingCart cart = shoppingCartRepository.findByBuyer(buyer);

        cart.getShoppingCartProducts().removeAll(products);
        shoppingCartRepository.save(cart);

        /*
            Inject sold items here
         */

        return "buyer/checkout";
    }

    @GetMapping("orders")
    public String getOrders(HttpServletRequest request, Model model) {
        String user = request.getUserPrincipal().getName();
        Buyer buyer = buyerRepository.findByUsername(user);
        Set<Orders> orders = buyer.getOrders();
        model.addAttribute("orders", orders);
        return "buyer/orders";
    }

    @GetMapping("shippingInfo")
    public String getShippingInfo(HttpServletRequest request, Model model) {
        String user = request.getUserPrincipal().getName();
        Buyer buyer = buyerRepository.findByUsername(user);
        Set<ShippingAddress> sa = buyer.getShippingAddress();
        model.addAttribute("shippingInfo", sa);
        return "buyer/shippingInfo";
    }

    @GetMapping("paymentInfo")
    public String getPaymentInfo(HttpServletRequest request, Model model) {
        String user = request.getUserPrincipal().getName();
        Buyer buyer = buyerRepository.findByUsername(user);
        Set<PaymentInfo> pay = buyer.getPaymentInfo();
        model.addAttribute("paymentInfo", pay);
        return "buyer/paymentInfo";
    }

    @RequestMapping(value = "alterBuyerInfo", method = RequestMethod.GET)
    public String getAlterSelectedUser(Model model, Principal principal)
    {
        usernamePlaceHolder = principal.getName();
        model.addAttribute("user", userService.getByUsername(usernamePlaceHolder));
        model.addAttribute("buyer", buyerService.findByUsername(usernamePlaceHolder));
        return "buyer/alterBuyerInfo";
    }

    @RequestMapping(value = "alterBuyerInfoDone", method = RequestMethod.POST)
    public String getAlterSellerDone(@ModelAttribute("seller") Seller seller, @ModelAttribute("user") Users user ) {
        this.userService.updateUser(usernamePlaceHolder, seller.getUsername(), user.getPassword());
        this.buyerService.updateBuyer(usernamePlaceHolder, seller.getUsername(), seller.getFirstname(), seller.getLastname());
        return "/login";
    }

    @GetMapping("TOS")
    public String getTOS() {
        return "termsService";
    }

    @GetMapping("/")
    public String getLandingPage() {
        return "index";
    }

    @GetMapping("messagingPortal")
    public String getMessagingPortal() {
        return "messaging/messagingPortal";
    }

    @GetMapping("searchUserToMessage")
    public String getSearchUserToMessage(Model model, @RequestParam(defaultValue = "") String username) {
        //could probably remove yourself
        model.addAttribute("users",userService.findByUserNames(username));
        return "messaging/searchUserToMessage";
    }

    @GetMapping("composeMessage")
    public String getComposeMessage(Model model, String username) {
        usernamePlaceHolder = username;
        model.addAttribute("to", username);
        model.addAttribute("message", new Message());
        return "messaging/composeMessage";
    }

    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    public String getMessageSent(Principal principal, @ModelAttribute("message")Message message) {
        this.messagingService.saveMessage(message,principal.getName(), usernamePlaceHolder);
        return "messaging/messagingPortal";
    }

    @GetMapping(value = "inbox")
    public String getInbox(Model model, @RequestParam(defaultValue = "") String username, Principal principal) {
        model.addAttribute("messages",this.messagingService.getByUserWhereFromIsLike(principal.getName(), username));
        return "messaging/messageInbox";
    }

    @GetMapping(value = "viewMessage")
    public String getViewMessage(Model model, Principal principal, Integer messageId) {
        model.addAttribute("message",this.messagingService.getByUsernameAndMessageId(principal.getName(), messageId));
        return "messaging/viewMessage";
    }

    @GetMapping(value = "deleteMessage")
    public String getDeleteMessage(Model model, Principal principal, @RequestParam(defaultValue = "") String username, Integer messageId) {
        this.messagingService.deleteMessage(principal.getName(), messageId);
        model.addAttribute("messages",this.messagingService.getByUserWhereFromIsLike(principal.getName(), username));
        return "messaging/messageInbox";
    }
}
