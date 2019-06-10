package YapeCoupons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
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

    // TODO : Distribute Mappings into lower level controller classes

    @PostMapping("/coupon")
    @ResponseBody
    public String CreateCoupon() {
        return "TODO : Create new coupon\n";
    }

    @GetMapping("/coupon")
    @ResponseBody
    public String GetCoupons() {
        return "TODO : Return list of coupons\n";
    }

    @GetMapping("/coupon/{id}")
    @ResponseBody
    public String GetCoupon(@PathVariable String id) {
        return "TODO : Return coupon " + id + "\n";
    }

    @PutMapping("/coupon/{id}")
    @ResponseBody
    public String UpdateCoupon(@PathVariable String id) {
        return "TODO : Update coupon " + id + "\n";
    }

    @DeleteMapping("/coupon/{id}")
    @ResponseBody
    public String DeleteCoupon(@PathVariable String id) {
        return "TODO : Delete coupon " + id + "\n";
    }

}
