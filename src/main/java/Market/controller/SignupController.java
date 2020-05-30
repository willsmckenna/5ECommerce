package Market.controller;

import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;
import Market.model.userTypes.Users;
import Market.repo.userTypeRepositories.BuyerRepository;
import Market.repo.userTypeRepositories.SellerRepository;
import Market.repo.userTypeRepositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

@Controller
public class SignupController {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;
    private static Users user;

    public SignupController(UsersRepository usersRepository, PasswordEncoder passwordEncoder, BuyerRepository buyerRepository, SellerRepository sellerRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
    }

    @GetMapping({"/signupview", "/signup"})
    public String signup(Model model) {
        model.addAttribute("newUser",new Users());
        return "signupview/userinfo";
    }

    @RequestMapping(value = "/saveUsers", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("newUser") Users newUser, BindingResult bindingResult,Model model)
    {
        //model.addAttribute("newUser",new Users());
        //new
        //System.out.println(newUser);
        //newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        System.out.println(newUser);
        //model.addAttribute("newUser",newUser);
        Users nUser = new Users(newUser.getUsername(),passwordEncoder.encode(newUser.getPassword()),newUser.getRoles(),"none");
        user = nUser;
       // newUser.setPermissions("none");
       // newUser.setActive(1);
        System.out.println(nUser);
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
        System.out.println(newBuyer);
        buyerRepository.save(newBuyer);
        return "/login";

    }

    

/*
    @PostMapping("/addUser")
    public String addBuye(@ModelAttribute Users newUser){
        return "signupview/signup";
    }
*/


}
