package Market.Service;

import Market.model.Product;

import java.util.Map;
import java.util.Optional;

public interface ShoppingCartService {
    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProducts();

    void checkout() ;

    double getTotal();
}
