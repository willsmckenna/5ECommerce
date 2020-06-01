package Market.controller;

import Market.model.Product;
import Market.model.buyerRelated.ShoppingCart;
import Market.model.buyerRelated.ShoppingCartProducts;
import Market.model.userTypes.Buyer;
import Market.repo.ProductRepository;
import Market.repo.ShoppingCartProductsRepository;
import Market.repo.ShoppingCartRepository;
import Market.repo.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("buyer")
public class BuyerController {

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartProductsRepository shoppingCartProductsRepository;

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


    @GetMapping("TOS")
    public String getTOS() {
        return "termsService";
    }

}
