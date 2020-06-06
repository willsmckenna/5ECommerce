package Market.service;

import Market.model.messages.Message;
import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;

public interface SellerService {

    Seller findByUsername(String username);
    boolean containsSeller(String username);
    void save(Seller seller);
    void updateSeller(String oldUsername, String newUsername, String newFirstName, String newLastName);
}
