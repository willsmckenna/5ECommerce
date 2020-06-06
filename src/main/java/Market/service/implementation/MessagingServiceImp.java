package Market.service.implementation;

import Market.model.messages.Message;
import Market.model.userTypes.Admin;
import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;
import Market.repo.AdminRepo;
import Market.repo.BuyerRepository;
import Market.repo.SellerRepository;
import Market.service.MessagingService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class MessagingServiceImp  implements MessagingService {

    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;
    private final AdminRepo adminRepo;

    public MessagingServiceImp(SellerRepository sellerRepository, BuyerRepository buyerRepository, AdminRepo adminRepo) {
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
        this.adminRepo = adminRepo;
    }

    @Override
    public void saveMessage(Message message, String from, String to)
    {
        message.setFrom(from);
        message.setTo(to);

        if(this.buyerRepository.existsByUsername(to))
        {
            Buyer buyer = buyerRepository.findByUsername(to);
            buyer.addMessage(message);
            this.buyerRepository.save(buyer);
        }
        else if(this.sellerRepository.existsByUsername(to))
        {
            Seller seller = this.sellerRepository.findByUsername(to);
            seller.addMessage(message);
            this.sellerRepository.save(seller);
        }
        else if(this.adminRepo.existsByUsername(to))
        {
            Admin admin = this.adminRepo.findByUsername(to);
            admin.addMessage(message);
            this.adminRepo.save(admin);
        }
    }

    @Override
    public Map<String, String> convertUserMessageToJSON(String username)
    {
        if(this.buyerRepository.existsByUsername(username))
        {
            Buyer buyer = buyerRepository.findByUsername(username);
            //return buyer.getMessages();
        }

        else if(this.sellerRepository.existsByUsername(username))
        {
            Seller seller = this.sellerRepository.findByUsername(username);
            //return seller.getMessages();
        }

        else if(this.adminRepo.existsByUsername(username))
        {
            Admin admin = this.adminRepo.findByUsername(username);
            //return admin.getMessages();
        }
        return new HashMap<String, String>();

    }
}
