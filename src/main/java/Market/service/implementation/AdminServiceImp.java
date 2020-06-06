package Market.service.implementation;

import Market.model.messages.Message;
import Market.model.userTypes.Admin;
import Market.model.userTypes.Buyer;
import Market.repo.AdminRepo;
import Market.repo.BuyerRepository;
import Market.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService {

    private final AdminRepo adminRepo;
    private final BuyerRepository buyerRepository;

    public AdminServiceImp(AdminRepo adminRepo, BuyerRepository buyerRepository) {
        this.adminRepo = adminRepo;
        this.buyerRepository = buyerRepository;
    }

    @Override
    public Admin findByUsername(String username) {
        return this.adminRepo.findByUsername(username);
    }

    @Override
    public boolean containsAdmin(String username) {
        return this.adminRepo.existsByUsername(username);
    }

    @Override
    public void save(Admin admin) {
        this.adminRepo.save(admin);
    }

    @Override
    public void saveMessage(Message message, String from, String to)
    {
        message.setFromUsername(from);
        message.setToUsername(to);

        if(this.buyerRepository.existsByUsername(to))
        {
            Buyer buyer = buyerRepository.findByUsername(to);
            buyer.addMessage(message);
            this.buyerRepository.save(buyer);
        }

        if(this.adminRepo.existsByUsername(to))
        {
            Admin admin = this.adminRepo.findByUsername(to);
            admin.addMessage(message);
            this.adminRepo.save(admin);
        }


        //Find out who it was sent to:

    }
}
