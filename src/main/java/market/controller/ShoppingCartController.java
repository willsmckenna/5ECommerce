package market.controller;

import market.service.ProductService;
import market.service.ShoppingCartService;
import market.repo.ProductRepository;
import market.repo.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }


//    @RequestMapping
//    public String getUsers(Model model)
//    {
//        Product item1 = new Product();
//        item1.setName("facemask");
//        item1.setPrice(10.99);
//        item1.setDescription("a standard surgical facemask");
//        Product item2 = new Product();
//        item2.setName("hand santizer");
//        item2.setPrice(8.99);
//        item2.setDescription("Purell brand, extra large");
//        List<Product> items = new ArrayList<>();
//        items.add(item1); items.add(item2);
//        Product item3 = new Product();
//        item3.setName("Alcohol");
//        item3.setPrice(9.00);
//        item3.setDescription("75% alcohol");
//        items.add(item3);
//        model.addAttribute("items", items);
//
//        return "buyer/cart";
//    }

    @GetMapping
    public ModelAndView showProduct(){
        ModelAndView modelAndView = new ModelAndView("buyer/cart");
        modelAndView.addObject("products",shoppingCartService.getProducts());
        modelAndView.addObject("total",shoppingCartService.getTotal());
        return modelAndView;
    }

    //will do remove and check out later if add product works
    @GetMapping(params = "add")
    public ModelAndView addProduct(@RequestParam Long productId){
        productService.findById(productId).ifPresent(shoppingCartService::addProduct);
        return showProduct();
    }


}
