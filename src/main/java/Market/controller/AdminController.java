package Market.controller;

import Market.model.buyerRelated.OrderTracking;
import Market.model.buyerRelated.Orders;
import Market.model.messages.Message;
import Market.model.userTypes.Admin;
import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;
import Market.model.userTypes.Users;
import Market.repo.OrderRepository;
import Market.service.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private Market.service.UserService userService;

    @Autowired
    private Market.service.ProductService productService;

    @Autowired
    private Market.service.BuyerService buyerService;

    @Autowired
    private Market.service.SellerService sellerService;

    @Autowired
    private Market.service.AdminService adminService;

    @Autowired
    MessagingService messagingService;

    @Autowired
    private OrderRepository orderRepository;

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
    public String getAlterUserSearch(Model model, @RequestParam(defaultValue = "") String username) {
        model.addAttribute("users",userService.findByUserNames(username));
        return "admin/adminSearchUsersToAlter";
    }

    @RequestMapping(value = "adminAlterSelectedUser", method = RequestMethod.GET)
    public String getAlterSelectedUser(Model model, @RequestParam(defaultValue = "") String username) {
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
    @RequestMapping(value = "searchOrders", method = RequestMethod.GET)
    public String getSearchOrders(Model model){
        List<Orders> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);

        return "admin/searchOrders";

    }

    @RequestMapping(value = "updateTracking", method = RequestMethod.GET)
    public String updateTracking(Model model, long orderId){
        OrderTracking tracking = new OrderTracking();
        tracking.setOrderID(orderId);
        model.addAttribute("tracking", tracking);
        return "admin/updateTracking";
    }

    //collect user input of product productReviewContent
    @RequestMapping(value = "/saveTracking", method = RequestMethod.POST)
    public String saveTracking(@ModelAttribute("tracking") OrderTracking orderTracking,
                               BindingResult bindingResult, long orderId) {

        Orders order = orderRepository.findById(orderId).orElse(null);
        order.setOrderTrackingContents(orderTracking);
        orderRepository.save(order);

        return "redirect:/admin/searchOrders";
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
    public String getComposeMessage(Model model, String username) {
        usernamePlaceHolder = username;
        model.addAttribute("to", username);
        model.addAttribute("message", new Message());
        return "messaging/composeMessage";
    }

    @RequestMapping(value = "sendMessage", method = RequestMethod.POST)
    public String getMessageSent(Principal principal, @ModelAttribute("message")Message message) {
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
}
