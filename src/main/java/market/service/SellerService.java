package market.service;

import market.model.products.SoldProducts;
import market.model.userTypes.Seller;

import java.util.List;

public interface SellerService {

    Seller findByUsername(String username);
    List<SoldProducts> getAllSoldProducts(String username);
    boolean containsSeller(String username);
    void save(Seller seller);
    void updateSeller(String oldUsername, String newUsername, String newFirstName, String newLastName);
}
