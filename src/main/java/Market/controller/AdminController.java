package Market.controller;

import Market.service.ProductService;
import Market.service.UserService;
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
        //find out if the user is a
        model.addAttribute("users",userService.findByUserNames(username));
        return "admin/alterSelectedUser";
    }
}
