package YapeCoupons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Redirect {
    @RequestMapping("/")
    public String Index() {
        return "login";
    }

    @RequestMapping("/home")
    public String Login() {
        return "home";
    }

    @RequestMapping("/register")
    public String Register() {
        return "register";
    }

    @RequestMapping("/register_local")
    public String RegisterLocal() {
        return "register_local";
    }

    @RequestMapping("/register_bank")
    public String RegisterBank() {
        return "register_bank";
    }

    @RequestMapping("/create_coupon")
    public String CreateCouponHTML() {
        return "create_coupon";
    }
}
