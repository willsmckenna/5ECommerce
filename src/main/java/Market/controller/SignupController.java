package Market.controller;

import Market.model.buyerRelated.ShoppingCart;
import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;
import Market.model.userTypes.Users;
import Market.repo.ProductRepository;
import Market.repo.ShoppingCartRepository;
import Market.repo.BuyerRepository;
import Market.repo.SellerRepository;
import Market.repo.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignupController {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private static Users user;

    public SignupController(UsersRepository usersRepository, ShoppingCartRepository shoppingCartRepository, PasswordEncoder passwordEncoder, BuyerRepository buyerRepository, SellerRepository sellerRepository, ProductRepository productRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
    }

    @GetMapping({"/signupview", "/signup"})
    public String signup(Model model) {
        model.addAttribute("newUser",new Users());
        return "signupview/userinfo";
    }

    @RequestMapping(value = "/saveUsers", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("newUser") Users newUser, BindingResult bindingResult,Model model)
    {

        Users nUser = new Users(newUser.getUsername(),passwordEncoder.encode(newUser.getPassword()),newUser.getRoles(),"none");
        user = nUser;
        usersRepository.save(nUser);

        if(newUser.getRoles().equals("BUYER"))
            return "signupview/signup";
        else
            return "signupview/signupseller";
    }

    @RequestMapping(value = "/saveBuyer", method = RequestMethod.POST)
    public String addBuyerSeller(@ModelAttribute("newBuyer") Buyer newBuyer, BindingResult bindingResult, Model model)
    {
        newBuyer.setUsername(user.getUsername());
        user = null;
        buyerRepository.save(newBuyer);
        //add a new shopping cart for the new buyer
        ShoppingCart sc = new ShoppingCart();
        sc.setBuyer(newBuyer);
        shoppingCartRepository.save(sc);
        return "/login";

    }

    @RequestMapping(value = "/saveSeller", method = RequestMethod.POST)
    public String addBuyerSeller(@ModelAttribute("newSeller") Seller newSeller, BindingResult bindingResult, Model model)
    {
        newSeller.setUsername(user.getUsername());
        user = null;
        sellerRepository.save(newSeller);
        return "/login";

    }


}
