package YapeCoupons.controller;

import YapeCoupons.model.Coupon;
import YapeCoupons.services.CouponService;
import YapeCoupons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

// Why this class?
// Is for an example, then we should delete it

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

    @RequestMapping(value = "/sid", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImage() throws IOException {
        ClassPathResource imgFile = new ClassPathResource("static/img/uploads/money.jpg");
        System.out.println(imgFile.getPath());
        return ResponseEntity.ok()
                             .contentType(MediaType.IMAGE_JPEG)
                             .body(new InputStreamResource(((ClassPathResource) imgFile).getInputStream()));
    }
}
