package Market.service.implementation;

import Market.model.userTypes.Seller;
import Market.service.SellerService;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImp  implements SellerService {
    @Override
    public Seller findByUsername(String username) {
        return null;
    }

    @Override
    public boolean containsSeller(String username) {
        return false;
    }

    @Override
    public void save(Seller seller) {

    }
}
