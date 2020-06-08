package market.service;

import market.model.products.Product;
import market.model.userTypes.Seller;

import java.util.Optional;
import java.util.Set;

public interface ProductService{
    Optional<Product> findById(Long id);
    Set<Product> findBySeller(Seller seller);
    Set<Product> findByName(String productName);
    Product findByNameAndSeller(String name, String sellerUsername);
    void removeProduct(String productName, String  sellerUsername);

}
