package Market.service;

import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;

public interface SellerService {

    Seller findByUsername(String username);
    boolean containsSeller(String username);
    void save(Seller seller);
}
