package Market.Controller;

import Market.Model.Product;
import Market.Repo.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        //products first row
        Product item1 = new Product();
        item1.setName("facemask");
        item1.setPrice(10.99);
        item1.setDescription("a standard surgical facemask");
        Product item2 = new Product();
        item2.setName("hand santizer");
        item2.setPrice(8.99);
        item2.setDescription("Purell brand, extra large");
        Product item3 = new Product();
        item3.setName("clorox wipes");
        item3.setPrice(9.99);
        item3.setDescription("for cleaning your home");
        List<Product> products = new ArrayList<>();
        products.add(item1); products.add(item2); products.add(item3);
        model.addAttribute("products", products);
        //products second row
        Product item4 = new Product();
        item4.setName("Iphone");
        item4.setPrice(499.99);
        item4.setDescription("newest edition, comes with headphones");
        Product item5 = new Product();
        item5.setName("Laptop");
        item5.setPrice(1000);
        item5.setDescription("Dell XPS 13");
        Product item6 = new Product();
        item6.setName("acoustic guitar");
        item6.setPrice(100.99);
        item6.setDescription("Fender student model, used");
        List<Product> forYouProducts = new ArrayList<>();
        forYouProducts.add(item4); forYouProducts.add(item5); forYouProducts.add(item6);
        model.addAttribute("forYouProducts", forYouProducts);

        return "productview/browse";

    }

    @GetMapping(path = "/productview", params = "info")
    public String viewProduct(@RequestParam String name, Model model)
    {
        Product item1 = new Product();
        item1.setName(name);
        item1.setPrice(10.99);
        item1.setDescription("a standard surgical facemask");
        model.addAttribute("p",item1);
        return "productview/info";
    }
}
