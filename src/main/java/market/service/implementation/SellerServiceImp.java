package market.service.implementation;

import market.model.products.SoldProducts;
import market.model.userTypes.Seller;
import market.repo.SellerRepository;
import market.service.SellerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImp  implements SellerService {

    private final SellerRepository sellerRepository;

    public SellerServiceImp(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller findByUsername(String username) {
        return this.sellerRepository.findByUsername(username);
    }

    @Override
    public List<SoldProducts> getAllSoldProducts(String username)
    {
        if(this.sellerRepository.existsByUsername(username))
        {
            Seller seller = this.sellerRepository.findByUsername(username);
            return  seller.getSoldProducts();
        }
        return new ArrayList<SoldProducts>();
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
