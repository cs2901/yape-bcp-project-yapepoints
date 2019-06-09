package YapeCoupons.middleware;

import YapeCoupons.model.User;
import YapeCoupons.services.UserService;

public class Middleware {

    private UserService users;

    public String CouponFilter(Model model) {
        try {
            if (users.filledBankAccountInformation("12345678")) {
            } else{
                return "/login2"
            }
            if (users.filledBusinessInformation("12345678")) {

            } else{
                return "/login3";
            }

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "/coupons";
    }
}
