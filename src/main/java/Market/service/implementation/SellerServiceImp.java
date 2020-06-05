package Market.service.implementation;

import Market.model.userTypes.Seller;
import Market.repo.ProductRepository;
import Market.repo.SellerRepository;
import Market.service.SellerService;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImp  implements SellerService {

    private final SellerRepository sellerRepository;

    public SellerServiceImp(SellerRepository sellerRepository, ProductRepository productRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller findByUsername(String username) {
        return this.sellerRepository.findByUsername(username);
    }

    @Override
    public boolean containsSeller(String username) {
        return this.sellerRepository.existsByUsername(username);
    }

    @Override
    public void save(Seller seller) {
        this.sellerRepository.save(seller);
    }

    @Override
    public void updateSeller(String oldUsername, String newUsername, String newFirstName, String newLastName)
    {
        Seller seller = this.sellerRepository.findByUsername((oldUsername));
        seller.setUsername(newUsername);
        seller.setFirstname(newFirstName);
        seller.setLastname(newLastName);

        //Find all products by old username and update their reference to seller username


        this.sellerRepository.save(seller);
    }
}
