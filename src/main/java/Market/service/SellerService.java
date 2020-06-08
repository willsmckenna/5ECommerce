package Market.service;

import Market.model.messages.Message;
import Market.model.products.Product;
import Market.model.products.SoldProducts;
import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;

import java.util.List;

public interface SellerService {

    Seller findByUsername(String username);
    List<SoldProducts> getAllSoldProducts(String username);
    boolean containsSeller(String username);
    void save(Seller seller);
    void updateSeller(String oldUsername, String newUsername, String newFirstName, String newLastName);
}
