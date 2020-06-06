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

import java.util.*;

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
            buyer.getMessages().add(message);
            this.buyerRepository.save(buyer);
        }
        else if(this.sellerRepository.existsByUsername(to))
        {
            Seller seller = this.sellerRepository.findByUsername(to);
            seller.getMessages().add(message);
            this.sellerRepository.save(seller);
        }
        else if(this.adminRepo.existsByUsername(to))
        {
            Admin admin = this.adminRepo.findByUsername(to);
            admin.getMessages().add(message);
            this.adminRepo.save(admin);
        }
    }

    @Override
    public List<Message> getUsersMessages(String username) {
        if(this.buyerRepository.existsByUsername(username)) {
           return buyerRepository.findByUsername(username).getMessages();
        }

        else if(this.sellerRepository.existsByUsername(username)) {
           return this.sellerRepository.findByUsername(username).getMessages();
        }

        else if(this.adminRepo.existsByUsername(username)) {
            return this.adminRepo.findByUsername(username).getMessages();
        }

        return new ArrayList<Message>();
    }
}
