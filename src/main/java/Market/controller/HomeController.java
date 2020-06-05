package Market.controller;

import Market.model.Product;
import Market.model.buyerRelated.Orders;
import Market.model.OrderTrackingContent;
import Market.model.reviewTypes.ProductReviewsHeading;
import Market.model.reviewTypes.ProductReviewContent;
import Market.repo.OrderRepository;
import Market.repo.ProductRepository;
import Market.repo.ProductReviewRepository;
import Market.service.BuyerService;
import Market.service.ProductService;
import Market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    BuyerService buyerService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final ProductReviewRepository productReviewRepository;

    public HomeController(ProductRepository pr, ProductReviewRepository productReviewRepository){
        this.productRepository = pr;
        this.productReviewRepository = productReviewRepository;
    }

    @GetMapping("index")
    public String showHome(Model model){
        return "index";
    }

    @GetMapping("login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("errorPage")
    public String getError() {
        return "error";
    }

    @RequestMapping("success")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {

        String role =  authResult.getAuthorities().toString();
        if(role.contains("ROLE_ADMIN")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin/index"));
        }
        else if(role.contains("ROLE_BUYER")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/buyer/index"));
        }
        else if(role.contains("ROLE_SELLER")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/seller/index"));
        }
    }
    @GetMapping({"/productview", "/browse"})
    public String listAllProducts(Model model){
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);

        return "productview/browse";

    }

    @GetMapping(path = "/productview", params = "info")
    public String viewProduct(@RequestParam String name, Model model)
    {
        Set<Product> items = productRepository.findAllByNameLike(name);
        model.addAttribute("p", items.toArray()[0]);
        return "productview/info";
    }

    @GetMapping("addReview")
    public String getRedirectToLogin(Principal principal, Model model, String productName, String sellerUsername)
    {
        if(principal == null || productName ==null){ return "/login"; }

        String username = principal.getName();
        if(username !=null)
        {
            Product product =productService.findByNameAndSeller(productName, sellerUsername);
            if(product == null) {return "productview/browse";}

            //Find out who is trying to write the review
            if(buyerService.containsBuyer(username))
            {
                //is a buyer
                model.addAttribute("product", product);
                model.addAttribute("buyer", buyerService.findByUsername(username));
                model.addAttribute("Review", new ProductReviewContent());
                return "buyer/addReview";
            }
        }
        return "login";
    }
    //collect user input of product productReviewContent
    @RequestMapping(value = "/saveReview", method = RequestMethod.POST)
    public String saveReview(Principal principal, @ModelAttribute("review") ProductReviewContent productReviewContent,
                             String productName, String sellerUsername)
    {
        productReviewContent.setAuthor(principal.getName());
        productReviewContent.setProductName(productName);
        productReviewContent.setSellerUsername(sellerUsername);

        /*
        System.out.println("Product Name:" + productName);
        System.out.println("Seller Name:" + sellerUsername);
        System.out.println("ProductReviewContent" + productReviewContent.getReviewMessage());
         */

        ProductReviewsHeading productReviewsHeading = new ProductReviewsHeading();
        productReviewsHeading.getProductReviewContents().add(productReviewContent);

        productReviewRepository.save(productReviewsHeading);
        return "redirect:/browse";
    }


}
