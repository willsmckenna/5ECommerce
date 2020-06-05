package Market.controller;

import Market.model.userTypes.Admin;
import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;
import Market.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("users",userService.getByUsername(username));
        return "admin/alterSelectedUser";
    }

    @RequestMapping(value = "adminAlterUserDone", method = RequestMethod.GET)
    public String getAlterDone(Model model, String newUsername, String newPassword)
    {
        this.userService.updatedPassword(newUsername, newPassword);
        //find out if the user was a buyer or a seller and update their username
        if(this.buyerService.containsBuyer(usernamePlaceHolder))
        {
            //the person was a buyer
            Buyer buyer = this.buyerService.findByUsername(usernamePlaceHolder);
            buyer.setUsername(newUsername);
            this.buyerService.save(buyer);
        }
        else if(this.sellerService.containsSeller(usernamePlaceHolder))
        {
            Seller seller = this.sellerService.findByUsername(usernamePlaceHolder);
            seller.setUsername(newUsername);
            this.sellerService.save(seller);
        }
        else if(this.adminService.containsAdmin(usernamePlaceHolder))
        {
            Admin admin = this.adminService.findByUsername(usernamePlaceHolder);
            admin.setUsername(newUsername);
            this.adminService.save(admin);
        }

        return "admin/index";
    }
}
