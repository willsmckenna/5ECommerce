package Market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("buyer")
public class BuyerController {

    @GetMapping("index")
    public String getBuyerAccount() {
        return "buyer/index";
    }
}
