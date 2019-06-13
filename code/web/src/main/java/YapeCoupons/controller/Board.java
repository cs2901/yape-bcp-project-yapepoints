package YapeCoupons.controller;

import YapeCoupons.model.Coupon;
import YapeCoupons.services.CouponService;
import YapeCoupons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// Why this class?

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
            coupons.createCoupon("12345678", "Super promocion 2", "UTEC", "imagen2.jpg");
            coupons.createCoupon("179", "Super promocion 2", "UTEC", "imagen2.jpg");
            //for (Coupon coupon: coupons.getActiveCoupons("12345678")) {
            //    System.out.println(coupon);
            //}
            coupons.getAllActiveCoupons();
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "greeting";
    }
}
