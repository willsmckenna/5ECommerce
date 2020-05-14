package Market.Controller;

import Market.Model.Product;
import Market.Repo.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BrowseProductsController {
    private final ProductRepository productRepository;

    public BrowseProductsController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping("/productview")
    public String listAllProducts(Model model){
        //Iterable<Product> products = productRepository.findAll();
        Product item1 = new Product();
        item1.setName("facemask");
        item1.setPrice(10.99);
        item1.setDescription("a standard surgical facemask");
        Product item2 = new Product();
        item2.setName("hand santizer");
        item2.setPrice(8.99);
        item2.setDescription("Purell brand, extra large");
        List<Product> products = new ArrayList<>();
        products.add(item1); products.add(item2);
        model.addAttribute("products", products);

        return "productview/browse";

    }
    

}
