package market.service.implementation;

import market.model.messages.Message;
import market.model.userTypes.Admin;
import market.model.userTypes.Buyer;
import market.model.userTypes.Seller;
import market.repo.AdminRepo;
import market.repo.BuyerRepository;
import market.repo.SellerRepository;
import market.service.MessagingService;
import market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessagingServiceImp  implements MessagingService {

    @Autowired
    UserService userService;

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
    public void deleteMessage(String username, Integer messageID) {
        List<Message> messages = this.getUsersMessages(username);
        if(messages != null)
        {
            for(Message m : messages)
            {
                if(m.getId().equals(messageID)) {
                    messages.remove(m);
                    this.userService.simpleSaveUserInRoleRepo(username);
                    return;
                }
            }
        }
    }

    @Override
    public Message getByUsernameAndMessageId(String username, Integer messageId)
    {
        List<Message> messages = this.getUsersMessages(username);
        if(messages != null) {
            for(Message m : messages) {
                if(m.getId().equals(messageId)) { return m; }
            }
        }
        return new Message();
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

    @Override
    public List<Message> getByUserWhereFromIsLike(String username, String fromLike) {
        List<Message>  tempList = getUsersMessages(username);
        List<Message> listFromLike = new ArrayList<>();
        for(Message m : tempList) {
            if(m.getFrom().contains(fromLike)) { listFromLike.add(m); }
        }
        return listFromLike;
    }
}
