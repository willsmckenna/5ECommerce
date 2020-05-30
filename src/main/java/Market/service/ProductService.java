package Market.service;

import Market.model.Product;
import Market.model.userTypes.Seller;

import java.util.Optional;
import java.util.Set;

public interface ProductService{
    Optional<Product> findById(Long id);

    Set<Product> findByName(String productName);
    void removeProduct(String productName, String  sellerUsername);
}
