package Market.controller;

import Market.model.Product;
import Market.model.userTypes.Seller;
import Market.model.userTypes.Users;
import Market.repo.ProductRepository;
import Market.repo.SellerRepository;
import Market.service.ProductService;
import Market.service.SellerService;
import Market.service.UserService;
import Market.service.implementation.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("seller")
public class SellerController
{
    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private UserService userService;

    static String usernamePlaceHolder ="";

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    public SellerController(ProductRepository productRepository, SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    @GetMapping("index")
    public String getSellerAccount()
    {
        return "seller/index";
    }


    @GetMapping("viewProducts")
    public String showProducts(Model model, HttpServletRequest request)
    {
        //get currently logged in user/buyer
        String user = request.getUserPrincipal().getName();
        Seller seller = sellerRepository.findByUsername(user);

        model.addAttribute("products",productRepository.findBySeller(seller));
        return "seller/viewProducts";
    }

    @GetMapping("addProduct")
    public String showForm(Model model) {
        model.addAttribute("product",new Product());
        return "seller/addProduct";
    }

    @PostMapping("list")
    public String addProduct(@ModelAttribute("product") Product newProduct)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Seller currentSeller = sellerRepository.findByUsername(authentication.getName());
        newProduct.setSeller(currentSeller);
        productRepository.save(newProduct);
        return "productview/browse";

    }
    @GetMapping("TOS")
    public String getTOS() {
        return "termsService";
    }

    @GetMapping("/")
    public String getLandingPage() {
        return "index";
    }

    @RequestMapping(value = "removeProduct", method = RequestMethod.GET)
    public String getRemoveProduct(Model model, String productName, String sellerUsername) {
        productService.removeProduct(productName, sellerUsername);
        model.addAttribute("products", productService.findByName(""));
        return "seller/viewProducts";
    }

    @RequestMapping(value = "alterSellerInfo", method = RequestMethod.GET)
    public String getAlterSelectedUser(Model model, Principal principal)
    {
        usernamePlaceHolder = principal.getName();
        model.addAttribute("user", userService.getByUsername(usernamePlaceHolder));
        model.addAttribute("seller", sellerService.findByUsername(usernamePlaceHolder));
        return "seller/alterSellerInfo";
    }

    @RequestMapping(value = "alterSellerInfoDone", method = RequestMethod.POST)
    public String getAlterSellerDone(@ModelAttribute("seller") Seller seller, @ModelAttribute("user")Users user )
    {
        this.userService.updateUser(usernamePlaceHolder, seller.getUsername(), user.getPassword());
        this.sellerService.updateSeller(usernamePlaceHolder, seller.getUsername(), seller.getFirstname(), seller.getLastname());
        return "/login";
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
}
