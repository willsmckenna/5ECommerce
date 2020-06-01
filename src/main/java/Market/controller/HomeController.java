package Market.controller;

import Market.model.Product;
import Market.repo.ProductRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Controller
@RequestMapping("/")
public class HomeController {

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

    @GetMapping("terms")
    public String getTerms() {
        return "termsService";
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
}
