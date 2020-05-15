package Market.Controller;

import Market.Model.Product;
import Market.Repo.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ShoppingCartController {
    @Autowired
    ShoppingCartRepository rep;

    @RequestMapping("/cart")
    public String getUsers(Model model)
    {
        Product item1 = new Product();
        item1.setName("facemask");
        item1.setPrice(10.99);
        item1.setDescription("a standard surgical facemask");
        Product item2 = new Product();
        item2.setName("hand santizer");
        item2.setPrice(8.99);
        item2.setDescription("Purell brand, extra large");
        List<Product> items = new ArrayList<>();
        items.add(item1); items.add(item2);
        Product item3 = new Product();
        item3.setName("Alcohol");
        item3.setPrice(9.00);
        item3.setDescription("75% alcohol");
        items.add(item3);
        model.addAttribute("items", items);

        return "ShoppingCartView/mycart";
    }
}
