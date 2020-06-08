package market.controller;

import market.model.products.Product;
import market.model.messages.Message;
import market.model.userTypes.Seller;
import market.model.userTypes.Users;
import market.repo.ProductRepository;
import market.repo.SellerRepository;
import market.service.*;
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

    @Autowired
    MessagingService messagingService;

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

    @GetMapping("composeMessage")
    public String getComposeMessage(Model model, String username)
    {
        usernamePlaceHolder = username;
        model.addAttribute("to", username);
        model.addAttribute("message", new Message());
        return "messaging/composeMessage";
    }

    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    public String getMessageSent(Principal principal, @ModelAttribute("message")Message message)
    {
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

    @GetMapping(value = "soldItems")
    public String getSoldItems(Model model, Principal principal) {
        model.addAttribute("soldProducts",this.sellerService.getAllSoldProducts(principal.getName()));
        return "seller/soldItems";
    }

}
