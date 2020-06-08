package Market.service.implementation;

import Market.model.userTypes.Seller;
import Market.repo.SellerRepository;
import Market.service.ProductService;
import Market.model.products.Product;
import Market.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImp implements ProductService {

    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(SellerRepository sellerRepository, ProductRepository productRepository) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Set<Product> findBySeller(Seller seller) {
        return productRepository.findBySeller(seller);
    }

    @Override
    public Set<Product> findByName(String productName) {
        return productRepository.findAllByNameLike("%" + productName + "%");
    }

    @Override
    public Product findByNameAndSeller(String name, String sellerUsername)
    {
        Seller seller = this.sellerRepository.findByUsername(sellerUsername);
        if(seller != null)
        {
           return this.productRepository.findByNameAndSeller(name, seller);

        }
        return null;
    }

    @Override
    public void removeProduct(String productName, String  sellerUsername) {

        Seller seller = sellerRepository.findByUsername(sellerUsername);
        Iterable<Product> products= productRepository.findAll();
        if(seller != null) {
            for (Product p : seller.getProductsForSale()) {
                if (p.getName().equals(productName)) {
                    seller.getProductsForSale().remove(p);
                    sellerRepository.save(seller);
                    break;
                }
            }
        }
    }
}
