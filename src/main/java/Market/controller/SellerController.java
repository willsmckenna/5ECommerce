package Market.controller;

import Market.model.Product;
import Market.repo.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("seller")
public class SellerController
{
    private final ProductRepository productRepository;

    public SellerController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("index")
    public String getBuyerAccount()
    {
        return "seller/index";
    }


    @GetMapping("addProduct")
    public String showForm(Model model)
    {
        model.addAttribute("product",new Product());
        return "seller/addProduct";
    }

    @PostMapping("addproduct")
    public String addProduct(@ModelAttribute Product product)
    {
        return "seller/result";

    }

}
