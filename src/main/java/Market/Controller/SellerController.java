package Market.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SellerController {
    @RequestMapping({"/MerchantAccount", "/MerchantAccount.html", "/MySellerAccount", "MySellerAccount.html"})
    public String getBuyerAccount()
    {
        return "SellersView/SellerHomePage";
    }

}
