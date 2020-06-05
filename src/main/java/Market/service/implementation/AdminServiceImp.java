package Market.service.implementation;

import Market.model.userTypes.Admin;
import Market.repo.AdminRepo;
import Market.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService {

    private final AdminRepo adminRepo;

    public AdminServiceImp(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
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
}
