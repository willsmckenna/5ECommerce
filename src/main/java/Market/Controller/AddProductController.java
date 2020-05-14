package Market.Controller;


import Market.Repo.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddProductController {
    private final ProductRepository productRepository;

    public AddProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/listproduct")
    public String addProduct(Model model)
    {
        return "listproduct/addproduct";
    }

}
