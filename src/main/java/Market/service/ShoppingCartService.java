package Market.service;

import Market.model.products.Product;

import java.util.Map;

public interface ShoppingCartService {
    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProducts();

    void checkout() ;

    double getTotal();
}
