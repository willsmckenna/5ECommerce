package Market.controller;

import Market.model.Product;
import Market.model.buyerRelated.ShoppingCart;
import Market.model.userTypes.Buyer;
import Market.repo.userTypeRepositories.BuyerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("buyer")
public class BuyerController {

    private BuyerRepository buyerRepository;

    @GetMapping("index")
    public String getBuyerAccount() {
        return "buyer/index";
    }

    @GetMapping("cart")
    public String getBuyerCart(Authentication authResult, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String buyerName = auth.getPrincipal().toString();
        Buyer buyer = buyerRepository.findByUsername(buyerName);
        ShoppingCart cart = buyer.getCart();
        Set <Product> items = cart.getProducts();
        model.addAttribute("items", items);
        return "buyer/cart";
    }
}
