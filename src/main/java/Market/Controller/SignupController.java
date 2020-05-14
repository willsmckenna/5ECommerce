package Market.Controller;

import Market.Model.Users;
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

    @GetMapping({"/signupview", "/signup"})
    public String signup(Model model) {
        model.addAttribute("newuser",new Users());
        return "signupview/signup";
    }



}
