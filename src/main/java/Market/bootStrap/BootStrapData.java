package Market.bootStrap;
import Market.model.userTypes.Users;
import Market.repo.userTypeRepositories.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public BootStrapData(UsersRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception
    {

        userRepository.deleteAll();
        //Any new users need to have password encrypted before db insert
        Users buyer = new Users("buyerUsername", passwordEncoder.encode("peter12"),"BUYER", "none");
        Users seller = new Users("sellerUsername", passwordEncoder.encode("peter12"),"SELLER", "none");
        Users admin = new Users("adminUsername", passwordEncoder.encode("peter12"),"ADMIN", "");

        userRepository.save(buyer);
        userRepository.save(seller);
        userRepository.save(admin);

        System.out.println("Started in Bootstrap");
    }
}
