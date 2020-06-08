package Market.service.implementation;

import Market.service.ShoppingCartService;
import Market.model.products.Product;
import Market.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ShoppingCartServiceImp implements ShoppingCartService {

    private final ProductRepository productRepository;

    //store product and its quantity
    private Map<Product, Integer> productsInCart = new HashMap<>();
    @Autowired
    public ShoppingCartServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        //already in cart, increase quantity by 1
        if(productsInCart.containsKey(product)){
            productsInCart.put(product,productsInCart.get(product)+1);
        }else{
            productsInCart.put(product,1);
        }
    }

    @Override
    public void removeProduct(Product product) {

    }

    /**
     * return list of products in cart
     * @return
     */
    @Override
    public Map<Product, Integer> getProducts() {
        return productsInCart;
    }

    @Override
    public void checkout() {

    }

    /**
     * count the total money in cart
     * @return
     */
    @Override
    public double getTotal() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry: productsInCart.entrySet()){
            total = total + entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
}
