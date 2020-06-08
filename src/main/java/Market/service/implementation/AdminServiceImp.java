package market.service.implementation;

import market.model.messages.Message;
import market.model.userTypes.Admin;
import market.repo.AdminRepo;
import market.repo.BuyerRepository;
import market.repo.SellerRepository;
import market.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {

    private final AdminRepo adminRepo;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;

    public AdminServiceImp(AdminRepo adminRepo, BuyerRepository buyerRepository, SellerRepository sellerRepository) {
        this.adminRepo = adminRepo;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
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
    public List<Message> getAdminMessages(String username) {
        Admin admin = this.adminRepo.findByUsername(username);
        return admin.getMessages();
    }

}
