package Market.service.implementation;

import Market.model.userTypes.Buyer;
import Market.repo.BuyerRepository;
import Market.service.BuyerService;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImp implements BuyerService {

    private final BuyerRepository buyerRepository;

    public BuyerServiceImp(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public Buyer findByUsername(String username) {
        return this.buyerRepository.findByUsername(username);
    }

    @Override
    public boolean containsBuyer(String username) {
       if(this.buyerRepository.findByUsername(username) != null)
       {
           return true;
       }
       return false;
    }
}
