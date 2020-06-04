package Market.service;

import Market.model.userTypes.Buyer;

public interface BuyerService {
        Buyer findByUsername(String username);
        boolean containsBuyer(String username);
}
