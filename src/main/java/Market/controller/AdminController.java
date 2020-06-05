package Market.controller;

import Market.model.userTypes.Admin;
import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;
import Market.model.userTypes.Users;
import Market.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private AdminService adminService;

    static String usernamePlaceHolder ="";

    @GetMapping("index")
    public String getAdminPage() {
        return "admin/index";
    }

    @RequestMapping(value = "searchUser", method = RequestMethod.GET)
    public String getUserSearch(Model model, @RequestParam(defaultValue = "") String username) {
        model.addAttribute("users", userService.findByUserNames(username));
        return "admin/searchUser";
    }

    @RequestMapping(value = "removeUser", method = RequestMethod.GET)
    public String getRemoveUser(Model model, String username) {
        userService.removeUser(username);
        model.addAttribute("users", userService.findByUserNames(""));
        return "admin/searchUser";
    }


    @RequestMapping(value = "adminSearchProduct", method = RequestMethod.GET)
    public String getProductSearch(Model model, @RequestParam(defaultValue = "") String productname) {
        model.addAttribute("products", productService.findByName(productname));
        return "admin/adminSearchProduct";
    }

    @RequestMapping(value = "removeProduct", method = RequestMethod.GET)
    public String getRemoveProduct(Model model, String productName, String sellerUsername) {
        productService.removeProduct(productName, sellerUsername);
        model.addAttribute("products", productService.findByName(""));
        return "admin/adminSearchProduct";
    }


    @RequestMapping(value = "adminAlterUserSearch", method = RequestMethod.GET)
    public String getAlterUserSearch(Model model, @RequestParam(defaultValue = "") String username)
    {
        model.addAttribute("users",userService.findByUserNames(username));
        return "admin/adminSearchUsersToAlter";
    }


    @RequestMapping(value = "adminAlterSelectedUser", method = RequestMethod.GET)
    public String getAlterSelectedUser(Model model, @RequestParam(defaultValue = "") String username)
    {
        usernamePlaceHolder = username;
        model.addAttribute("user",userService.getByUsername(username));
        return "admin/alterSelectedUser";
    }

    @RequestMapping(value = "adminAlterUserDone", method = RequestMethod.POST)
    public String getAlterDone(@ModelAttribute("user")Users user)
    {
        this.userService.updateUser(usernamePlaceHolder,user.getUsername(), user.getPassword());
        //find out if the user was a buyer or a seller and update their username
        if(this.buyerService.containsBuyer(usernamePlaceHolder))
        {
            //the person was a buyer
            Buyer buyer = this.buyerService.findByUsername(usernamePlaceHolder);
            buyer.setUsername(user.getUsername());
            this.buyerService.save(buyer);
        }
        else if(this.sellerService.containsSeller(usernamePlaceHolder))
        {
            Seller seller = this.sellerService.findByUsername(usernamePlaceHolder);
            seller.setUsername(user.getUsername());
            this.sellerService.save(seller);
        }
        else if(this.adminService.containsAdmin(usernamePlaceHolder))
        {
            Admin admin = this.adminService.findByUsername(usernamePlaceHolder);
            admin.setUsername(user.getUsername());
            this.adminService.save(admin);
        }

        return "admin/index";
    }

    @GetMapping("messagingPortal")
    public String getMessagingPortal() {
        return "messaging/messagingPortal";
    }
}
