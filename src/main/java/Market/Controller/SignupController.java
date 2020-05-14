package Market.Controller;

import Market.Repo.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignupController {

    private final UsersRepository usersRepository;

    public SignupController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/signupview")
    public String signup(Model model) {
        return "signupview/signup";
    }



}
