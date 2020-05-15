package Market.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BuyerController {

    @RequestMapping({"/CustomerAccount", "/CustomerAccount.html", "/MyBuyerAccount", "MyBuyerAccount.html"})
    public String getBuyerAccount()
    {
        return "BuyersView/BuyerHomePage";
    }
}
