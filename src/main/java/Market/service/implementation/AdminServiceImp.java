package Market.service.implementation;

import Market.model.messages.Message;
import Market.model.userTypes.Admin;
import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;
import Market.repo.AdminRepo;
import Market.repo.BuyerRepository;
import Market.repo.SellerRepository;
import Market.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public Map<String, Map<String, String>> getAdminMessages(String username) {
        Admin admin = this.adminRepo.findByUsername(username);
        return admin.getMessages();
    }

}
