package YapeCoupons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Coupon {
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
