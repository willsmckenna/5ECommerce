package Market.controller;

import Market.model.Product;
import Market.model.userTypes.Seller;
import Market.repo.ProductRepository;
import Market.repo.SellerRepository;
import Market.service.implementation.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("seller")
public class SellerController
{
    @Autowired
    private ProductServiceImp productServiceImp;

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
        //System.out.println(newProduct);
        //productRepository.save(newProduct);
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

}
