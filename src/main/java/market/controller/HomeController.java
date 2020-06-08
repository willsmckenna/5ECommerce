package market.controller;

import market.model.products.Product;
import market.model.reviews.Review;
import market.model.userTypes.Buyer;
import market.model.userTypes.Seller;
import market.repo.*;
import market.service.BuyerService;
import market.service.ProductService;
import market.service.UserService;
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
import java.util.*;

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


    @Autowired
    private  SellerRepository sellerRepository;

    public HomeController(ProductRepository pr){
        this.productRepository = pr;
    }

    @GetMapping("index")
    public String showHome(){
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
    //collect user input of product productReviewContent
    @RequestMapping(value = "/saveReview", method = RequestMethod.POST)
    public String saveReview(Principal principal,Model model,@ModelAttribute("review") Review review,
                             BindingResult bindingResult, String productName, String sellerUsername)
    {
        System.out.println("review user write is :" + review.getContent());

        Product product =productService.findByNameAndSeller(productName, sellerUsername);
        System.out.println("product name is " + product.getName());
        //get buyer name
        String username = principal.getName();
        Buyer buyer = buyerService.findByUsername(username);
        String buyerName = buyer.getUsername();

        List<Review> reviewList = product.getReviews();

        if (reviewList == null) {
            reviewList = new ArrayList<Review>();
        }

        Review new_review = new Review();
        new_review.setContent(review.getContent());
        new_review.setDate(new Date());
        new_review.setAuthor(buyerName);

        reviewList.add(new_review);

        System.out.println("review list from product:");
        for (Review r: reviewList){
            System.out.println(r.getContent());
            System.out.println(r.getDate());
            System.out.println(r.getAuthor());
        }

        product.setReviews(reviewList);

        productRepository.save(product);

        return "redirect:/browse";
    }

    @GetMapping(value = "/showProductReview")
    public String showProductReview(Model model, String productName, String sellerUsername){
        Product product =productService.findByNameAndSeller(productName, sellerUsername);
        System.out.println(productName + " " +sellerUsername );
        System.out.println(product.getReviews());
        model.addAttribute("reviews",product.getReviews());
        return "productview/seeReviews";
    }

    @GetMapping("viewSellerReviews")
    public String viewSellerReviews(@RequestParam String sellerUsername,Model model)
    {
        Seller seller = sellerRepository.findByUsername(sellerUsername);
        System.out.println(seller.getReviews());
        model.addAttribute("reviews",seller.getReviews());
        return "productview/sellerreviewsview";
    }

    @GetMapping("reviewSeller")
    public String redirectToReviewForm(@RequestParam String sellerUsername,Model model)
    {
        model.addAttribute("sellerReview",new Review());
        model.addAttribute("seller",sellerUsername);
        System.out.println("hello");
        System.out.println(sellerUsername);
        return "seller/reviewSeller";
    }

    @PostMapping("addSellerReview")
    public String saveSellerReview(@ModelAttribute("sellerReview") Review sellerReview, String seller, Model model,Principal principal)
    {
        //model.addAttribute("reviews",reviews);
        if(principal==null)
            return "/login";

        sellerReview.setAuthor(principal.getName());
        sellerReview.setDate(new Date());

        System.out.println(sellerReview);
        Seller seller1 = sellerRepository.findByUsername(seller);
        seller1.getReviews().add(sellerReview);
        sellerRepository.save(seller1);

        return "redirect:/browse";
    }

}
