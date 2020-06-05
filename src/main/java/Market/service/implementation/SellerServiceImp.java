package Market.service.implementation;

import Market.model.userTypes.Seller;
import Market.repo.SellerRepository;
import Market.service.SellerService;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImp  implements SellerService {

   private final SellerRepository sellerRepository;

    public SellerServiceImp(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller findByUsername(String username) {
        return null;
    }

    @Override
    public boolean containsSeller(String username) {
        return this.sellerRepository.existsByUsername(username);
    }

    @Override
    public void save(Seller seller) {
        this.sellerRepository.save(seller);
    }
}
