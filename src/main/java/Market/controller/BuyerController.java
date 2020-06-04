package Market.controller;

import Market.model.PaymentInfo;
import Market.model.Product;
import Market.model.buyerRelated.*;
import Market.model.userTypes.Buyer;
import Market.repo.*;
import Market.service.implementation.ShoppingCartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("buyer")
public class BuyerController {

    @Autowired
    private ShoppingCartServiceImp shoppingCartServiceImp;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartProductsRepository shoppingCartProductsRepository;

    @Autowired
    private OrderRepository orderRepository;

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
        return "buyer/checkout";
    }

    @GetMapping("orders")
    public String getOrders(HttpServletRequest request, Model model) {
        String user = request.getUserPrincipal().getName();
        Buyer buyer = buyerRepository.findByUsername(user);
        Set<Orders>orders = buyer.getOrders();


        model.addAttribute("orders", orders);
        return "buyer/orders";
    }

    @GetMapping("shippingInfo")
    public String getShippingInfo(HttpServletRequest request, Model model) {
        String user = request.getUserPrincipal().getName();
        Buyer buyer = buyerRepository.findByUsername(user);
        Set<ShippingAddress>sa = buyer.getShippingAddress();
        model.addAttribute("shippingInfo", sa);
        return "buyer/shippingInfo";
    }

    @GetMapping("paymentInfo")
    public String getPaymentInfo(HttpServletRequest request, Model model) {
        String user = request.getUserPrincipal().getName();
        Buyer buyer = buyerRepository.findByUsername(user);
        Set<PaymentInfo>pa = buyer.getPaymentInfo();
        model.addAttribute("paymentInfo", pa);
        return "buyer/paymentInfo";
    }

    @GetMapping("TOS")
    public String getTOS() {
        return "termsService";
    }

    @GetMapping("/")
    public String getLandingPage() {
        return "index";
    }

}
