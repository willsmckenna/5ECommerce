package Market.Controller;

import Market.Repo.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProductRepository productRepository;

    public HomeController(ProductRepository pr){
        this.productRepository = pr;
    }

    @GetMapping({"/", "/welcome", "/home"})
    public String showHome(Model model){
        return "userview/home";
    }
}
