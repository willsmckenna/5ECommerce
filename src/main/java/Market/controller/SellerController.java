package Market.controller;

import Market.model.Product;
import Market.repo.ProductRepository;
import Market.service.ProductService;
import Market.service.implementationService.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("seller")
public class SellerController
{
    @Autowired
    private ProductServiceImp productServiceImp;

    private final ProductRepository productRepository;

    public SellerController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("index")
    public String getSellerAccount()
    {
        return "seller/index";
    }


    @GetMapping("addProduct")
    public String showForm(Model model)
    {
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

}
