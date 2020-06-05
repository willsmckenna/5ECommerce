package Market.controller;

import Market.model.Product;
import Market.model.Review;
import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;
import Market.repo.ProductRepository;
import Market.service.BuyerService;
import Market.service.ProductService;
import Market.service.UserService;
import Market.service.implementation.BuyerServiceImp;
import Market.service.implementation.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    private final ProductRepository productRepository;

    public HomeController(ProductRepository pr){
        this.productRepository = pr;
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
                model.addAttribute("Review", new Review());
                return "buyer/addReview";
            }
        }
        return "login";
    }
    //collect user input of product review
    @RequestMapping(value = "/saveReview", method = RequestMethod.POST)
    public String saveReview(Model model,@ModelAttribute("review") Review review,
                             BindingResult bindingResult, String productName, String sellerUsername)
    {
        System.out.println("review user write is :" + review.getReview());

        Product product =productService.findByNameAndSeller(productName, sellerUsername);
        System.out.println("product name is " + product.getName());

        List<Review> reviewList = product.getReviews();

        if (reviewList == null) {
            reviewList = new ArrayList<Review>();
        }

        Review new_review = new Review();
        new_review.setReview(review.getReview());
        new_review.setDate(new Date());
        new_review.setAuthor(sellerUsername);

        reviewList.add(new_review);

        System.out.println("review list from product:");
        for (Review r: reviewList){
            System.out.println(r.getReview());
            System.out.println(r.getDate());
            System.out.println(r.getAuthor());
        }

        //it looks like it not update in database and every time, it will create new review_list
        productRepository.save(product);

        return "redirect:/browse";
    }

}
