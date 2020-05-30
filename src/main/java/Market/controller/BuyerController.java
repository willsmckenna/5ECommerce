package Market.controller;

import Market.model.Product;
import Market.model.buyerRelated.ShoppingCart;
import Market.model.userTypes.Buyer;
import Market.repo.userTypeRepositories.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
@RequestMapping("buyer")
public class BuyerController {

    @Autowired
    private BuyerRepository buyerRepository;

    public BuyerController(){}

    @GetMapping("index")
    public String getBuyerAccount() {
        return "buyer/index";
    }

    @GetMapping("cart")
    public String getBuyerCart(HttpServletRequest request, Model model) {
        String user = request.getUserPrincipal().getName();

        Buyer buyer = buyerRepository.findByUsername(user);
        ShoppingCart cart = buyer.getCart();
        Set <Product> items = cart.getProducts();
        model.addAttribute("items", items);
        return "buyer/cart";
    }

    @PostMapping("productview/info")
    public String addProduct(@ModelAttribute("product") Product product, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "buyer/cart";
        }

        String user = request.getUserPrincipal().getName();
        Buyer buyer = buyerRepository.findByUsername(user);
        ShoppingCart cart = buyer.getCart();
        cart.addItemToCart(product);

        return "redirect:/browse";
    }

}
