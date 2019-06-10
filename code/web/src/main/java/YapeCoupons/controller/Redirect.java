package YapeCoupons.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class Redirect {
    @RequestMapping("/")
    public String Index() {
        return "home";
    }

    @RequestMapping("/login")
    public String Login() {
        return "login";
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
