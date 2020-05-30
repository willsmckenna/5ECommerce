package Market.service.implementationService;

import Market.model.userTypes.Seller;
import Market.repo.userTypeRepositories.SellerRepository;
import Market.service.ProductService;
import Market.model.Product;
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
    public Set<Product> findByName(String productName) {
        return productRepository.findAllByNameLike("%" + productName + "%");
    }

    @Override
    public void removeProduct(String productName, String  sellerUsername) {

        Seller seller = sellerRepository.findByUsername(sellerUsername);
        Iterable<Product> products= productRepository.findAll();
        if(seller != null) {

            System.out.println("Found Seller with username: " + seller.getUsername());
            for (Product p : seller.getProductsForSale())
            {
                System.out.println("Remove:" +productName + "  Have:" + p.getName() );
                if (p.getName().equals(productName)) {
                    System.out.println("Found product with Name: " + productName);
                    seller.getProductsForSale().remove(p);
                    sellerRepository.save(seller);
                   // productRepository.delete(p);
                    break;
                }
            }
        }
    }
}
