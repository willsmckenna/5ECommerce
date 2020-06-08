package market.service.implementation;

import market.model.userTypes.Buyer;
import market.repo.BuyerRepository;
import market.service.BuyerService;
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

    @Override
    public void save(Buyer buyer) {
        this.buyerRepository.save(buyer);
    }

    @Override
    public void updateBuyer(String oldUsername, String newUsername, String newFirstName, String newLastName)
    {
        Buyer buyer = this.buyerRepository.findByUsername((oldUsername));
        buyer.setUsername(newUsername);
        buyer.setFirstname(newFirstName);
        buyer.setLastname(newLastName);

        this.buyerRepository.save(buyer);
    }
}
