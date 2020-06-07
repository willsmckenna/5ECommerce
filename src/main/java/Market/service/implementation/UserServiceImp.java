package Market.service.implementation;

import Market.model.userTypes.Admin;
import Market.model.userTypes.Buyer;
import Market.model.userTypes.Seller;
import Market.model.userTypes.Users;
import Market.repo.AdminRepo;
import Market.repo.BuyerRepository;
import Market.repo.SellerRepository;
import Market.repo.UsersRepository;
import Market.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImp implements UserService {


    private final UsersRepository usersRepository;
    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;
    private final AdminRepo adminRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UsersRepository usersRepository, SellerRepository sellerRepository, BuyerRepository buyerRepository, AdminRepo adminRepo, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.sellerRepository = sellerRepository;
        this.buyerRepository = buyerRepository;
        this.adminRepo = adminRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void simpleSaveUserInRoleRepo(String username)
    {
        if(this.buyerRepository.existsByUsername(username))
        {
            Buyer buyer = buyerRepository.findByUsername(username);
            this.buyerRepository.save(buyer);
        }
        else if(this.sellerRepository.existsByUsername(username))
        {
            Seller seller = this.sellerRepository.findByUsername(username);
            this.sellerRepository.save(seller);
        }
        else if(this.adminRepo.existsByUsername(username))
        {
            Admin admin = this.adminRepo.findByUsername(username);
            this.adminRepo.save(admin);
        }
    }

    @Override
    public Users getByUsername(String username) {
        return this.usersRepository.findByUsername(username);
    }

    public Set<Users>  findByUserNames(String username) {
        return usersRepository.findByUsernameLike("%" + username + "%");
    }

    public void removeUser(String username) {
        Users user = usersRepository.findByUsername(username);
        if(user != null){
            usersRepository.delete(user);
        }
    }

    @Override
    public void updateUser(String oldUsername,String newUsername, String plaintextPassword) {
        Users user = this.usersRepository.findByUsername(oldUsername);
        user.setUsername(newUsername);
        user.setPassword(passwordEncoder.encode(plaintextPassword));
        this.usersRepository.save(user);
    }
}
