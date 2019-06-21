package YapeCoupons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO : Rename endpoints. Use meaningful, coherent names.
@Controller
public class Redirect {
    @RequestMapping("/")
    public String index() { return "login"; }

    @RequestMapping("/restorePassword")
    public String restorePassword() { return "restorePassword"; }

    @RequestMapping("/changePassword")
    public String changePassword() { return "changePassword"; }

    @RequestMapping("/create_coupon")
    public String createCouponHTML() { return "create_coupon"; }

}
