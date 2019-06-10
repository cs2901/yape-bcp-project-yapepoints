package YapeCoupons.middleware;

import YapeCoupons.model.User;
import YapeCoupons.services.UserService;

public class Middleware {

    private UserService users;

    public String CouponFilter(String dni) {
        try {
            if (users.filledBankAccountInformation(dni)) {
            } else{
                return "login2";
            }
            if (users.filledBusinessInformation(dni)) {

            } else{
                return "login3";
            }

        } catch (Exception e) {
            //model.addAttribute("error", e.getMessage());
        }
        return "coupons";
    }
}
