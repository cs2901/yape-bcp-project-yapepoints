package YapeCoupons.controller;

import YapeCoupons.model.Coupon;
import YapeCoupons.services.CouponService;
import YapeCoupons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Board {
    @Autowired
    private UserService users;

    @Autowired
    private CouponService coupons;

    @RequestMapping("/board-example")
    public String example(Model model) {
        try {
            coupons.deleteAll();
            coupons.createCoupon("12345678", "Super promocion", "Valido en UTEC", "imagen.jpg");
            for (Coupon coupon: coupons.getActiveCoupons()) {
                System.out.println(coupon);
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "greeting";
    }
}
