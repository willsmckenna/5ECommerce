package Market.service;

import Market.model.Product;

import java.util.Optional;

public interface ProductService{
    Optional<Product> findById(Long id);
}
