package Market.controller;


import Market.model.Product;
import Market.repo.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddProductController {
    private final ProductRepository productRepository;

    public AddProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/listproduct")
    public String showForm(Model model)
    {
        model.addAttribute("product",new Product());
        return "listproduct/addproduct";
    }

    @PostMapping("/listproduct")
    public String addProduct(@ModelAttribute Product product)
    {
        return "listproduct/result";
    }

}
